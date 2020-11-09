package com.astontech.console;

import com.astontech.bo.Directory;
import com.astontech.bo.File;
import com.astontech.dao.DirectoryDAO;
import com.astontech.dao.FileDAO;
import common.helpers.MathHelper;
import common.helpers.StringHelper;
import mysql.DirectoryDAOImpl;
import mysql.FileDAOImpl;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    interact();
        //TestDirectoryDAO();

    }

    public static void interact() {
        // 2. Create a new project in IntelliJ, structure should be similar to OOP project
        // 5. Create a menu driven console application that allow the following:
        boolean flag1 = true;
        boolean flag2 = true;

        while(flag1) {
            //a. Prompts the user for a starting directory
            Scanner reader = new Scanner(System.in);
            System.out.println("Please select a starting directory: ");
            String dir = reader.nextLine();

            //b. The application would then recursively collect the following and store them in appropriate db entitity
            System.out.println("One moment while information is being transferred to the database.");
            recursionFiles(new java.io.File(dir));
            System.out.println("\n Information Uploaded successfully! \n");

            while (flag2) {
                //Menu
                System.out.println("---Menu---");
                System.out.println("1: Display Directory with Most Files");
                System.out.println("2: Display Biggest Directory");
                System.out.println("3: Display Top 5 Biggest Files");
                System.out.println("4: Display Files Based on File Type");
                System.out.println("5: Clear Database and Start Over");
                System.out.println("6: Exit Program");

                //Choose from Menu
                System.out.println("Choose from the Menu: ");
                int choice = reader.nextInt();

                DirectoryDAOImpl directoryDAO = new DirectoryDAOImpl();
                Directory directory = null;
                //Switch
                switch (choice) {
                    case 1:
                        //Display Directory with Most Files
                        directory = directoryDAO.getDirectoryMostFiles();
                        System.out.println(
                                "ID: " + directory.getDirectoryID() +
                                        " Directory Name: " + directory.getDirectoryName() +
                                        " Files: " + directory.getNumberOfFiles() + "\n"
                        );
                        break;
                    case 2:
                        //Display Biggest Directory
                        directory = directoryDAO.getBiggestDirectory();
                        System.out.println(
                                "ID: " + directory.getDirectoryID() +
                                        " Directory Name: " + directory.getDirectoryName() +
                                        " Directory Size: " + directory.getDirectorySize() + "\n"
                        );
                        break;
                    case 3:
                        System.out.println("Wednesday");
                        break;
                    case 4:
                        System.out.println("Thursday");
                        break;
                    case 5:
                        //Clear Database and Start Over
                        flag2 = false;
                        directoryDAO.ClearDB();
                        interact();
                        break;
                    case 6:
                        // Exit Program
                        flag2 = false;
                        flag1 = false;
                        break;
                    default:
                        System.out.println("Please choose a number from 1 to 6");
                }
            }// innner while loop flag2

        } // outer while loop flag1


        System.out.println("Exiting Program");
    }

    public static void recursionFiles(java.io.File dir) {
        try{
            java.io.File[] files = dir.listFiles();
            System.out.print(".");
            for(java.io.File file : files) {
                if(file.isDirectory()){
                    // Recursion happens here
                    //Values to put into Directory Object
                    //DirectoryName, DirectorySize, NumberOfFiles, DirectoryPath
                    Directory directory = new Directory(0, file.getName(), MathHelper.getFolderSize(file), file.listFiles().length, file.getCanonicalPath());

                    DirectoryDAO directoryDAO = new DirectoryDAOImpl();
                    int id = directoryDAO.insertDirectory(directory);
                    recursionFiles(file);
                } else {
                    //Values to put into File Object
                    //FileName, FileType, FileSize, FilePath
                    File fileObj = new File(0, file.getName(), StringHelper.getExtension(file.getName()), file.length(), file.getCanonicalPath());

                    FileDAO fileDAO = new FileDAOImpl();
                    int id = fileDAO.insertFile(fileObj);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void TestDirectoryDAO() {
        //region Test Get Collection Directories
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();
        List<Directory> directoryList = directoryDAO.getDirectoryList();

        System.out.println("===============================");
        System.out.println("---Directories Details---");
        for(Directory directory: directoryList) {
            System.out.println(directory.getDirectoryID() + ": " + directory.getDirectoryName() + " " + directory.getDirectoryPath());
        }
        System.out.println("===============================");
        //endregion

        //region Test Get Directory By ID
        Directory directory = directoryDAO.getDirectoryById(3);
        System.out.println("---Directory Details---");
        System.out.println("Directory Name: " + directory.getDirectoryName());
        System.out.println("===============================");
        //endregion


        //region Test Insert Directory
        //(int directoryID,String directoryName, int directorySize, int numberOfFiles, String directoryPath)
        Directory directory1 = new Directory(0, "TopSecretFiles", 200, 2, "/home/user1/Desktop");

        DirectoryDAO directoryDAO1 = new DirectoryDAOImpl();
        int id = directoryDAO1.insertDirectory(directory1);

        System.out.println("New Directory Record Inserted. ID: " + id );
        //endregion

        /*
        //region Test Update Directory
        //Create instance of Directory Data Access Object
        DirectoryDAO directoryDAO2 = new DirectoryDAOImpl();

        //Create a directory object then use get directory by id
        Directory directory2 = directoryDAO2.getDirectoryById(1);
        directory2.setDirectoryName("Prime_Academy");

        if(directoryDAO2.updateDirectory(directory2)) {
            System.out.println("Directory Update Success!");
        } else {
            System.out.println("Directory Update Fail!");
        }
        //endregion
        */
    /*
        //region Test Delete Directory
        DirectoryDAO directoryDAO3 = new DirectoryDAOImpl();

        if(directoryDAO3.deleteDirectory(8)) {
            System.out.println("Successfully removed Directory from Database!");
        } else {
            System.out.println("Failed to remove Directory from Database!");
        }
        //endregion

     */
    }

    public static void TestFileDAO() {
        //region Test Get Collection Files
        FileDAO fileDAO = new FileDAOImpl();
        List<File> fileList = fileDAO.getFileList();

        System.out.println("===============================");
        System.out.println("---Directories Details---");
        for(File file: fileList) {
            System.out.println(file.getFileID() + ": " + file.getFileName() + " " + file.getFilePath());
        }
        System.out.println("===============================");
        //endregion

        //region Test Get File By ID
        File file = fileDAO.getFileById(1);
        System.out.println("---Directory Details---");
        System.out.println("Directory Name: " + file.getFileName());
        System.out.println("===============================");
        //endregion

        /*
        //region Test Insert File
        //(int fileID, String fileName, String fileType, int fileSize, String filePath, int directoryID)
        File file1 = new File(0, "Operation_Gum_Drop", ".txt", 20, "/home/user1/Desktop/Operation_Gum_Drop.txt");

        FileDAO fileDAO1 = new FileDAOImpl();//??? what is this
        int id = fileDAO1.insertFile(file1);

        System.out.println("New File Record Inserted. ID: " + id );
        //endregion
        */

        /*
        //region Test Update File
        //Create instance of File Data Access Object
        FileDAO fileDAO2 = new FileDAOImpl();

        //Create a file object then use get file by id
        File file2 = fileDAO2.getFileById(1);
        file2.setFileName("script.js");
        file2.setFileType(".js");
        file2.setFilePath("/home/user1/Desktop/Prime_Academy/script.js");

        if(fileDAO2.updateFile(file2)) {
            System.out.println("File Update Success!");
        } else {
            System.out.println("File Update Fail!");
        }
        //endregion
        */

        //region Test Delete Directory
        FileDAO fileDAO3 = new FileDAOImpl();

        if(fileDAO3.deleteFile(5)) {
            System.out.println("Successfully removed File from Database!");
        } else {
            System.out.println("Failed to remove File from Database!");
        }
        //endregion
    }
}

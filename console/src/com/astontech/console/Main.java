package com.astontech.console;

import com.astontech.bo.Directory;
import com.astontech.bo.File;
import com.astontech.dao.DirectoryDAO;
import com.astontech.dao.FileDAO;
import mysql.DirectoryDAOImpl;
import mysql.FileDAOImpl;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// 2. Create a new project in IntelliJ, structure should be similar to OOP project
        System.out.println("Testing Main!");
        //TestDirectoryDAO();
        TestFileDAO();
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

        /*
        //region Test Insert Directory
        //(int directoryID,String directoryName, int directorySize, int numberOfFiles, String directoryPath)
        Directory directory1 = new Directory(0, "TopSecretFiles", 200, 2, "/home/user1/Desktop");

        DirectoryDAO directoryDAO1 = new DirectoryDAOImpl();//??? what is this
        int id = directoryDAO1.insertDirectory(directory1);

        System.out.println("New Directory Record Inserted. ID: " + id );
        //endregion
        */
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

        //region Test Delete Directory
        DirectoryDAO directoryDAO3 = new DirectoryDAOImpl();

        if(directoryDAO3.deleteDirectory(8)) {
            System.out.println("Successfully removed Directory from Database!");
        } else {
            System.out.println("Failed to remove Directory from Database!");
        }
        //endregion
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
        File file1 = new File(0, "Operation_Gum_Drop", ".txt", 20, "/home/user1/Desktop/Operation_Gum_Drop.txt", 6);

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

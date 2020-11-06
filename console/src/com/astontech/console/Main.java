package com.astontech.console;

import com.astontech.bo.Directory;
import com.astontech.dao.DirectoryDAO;
import mysql.DirectoryDAOImpl;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// 2. Create a new project in IntelliJ, structure should be similar to OOP project
        System.out.println("Testing Main!");
        //TestDirectoryDAO();
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

    }
}

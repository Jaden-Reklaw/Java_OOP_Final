package com.astontech.bo;

// 3. Create Objects to represent the 2 db entities
public class Directory {
    // region Properties
    private int DirectoryID;
    private String DirectoryName;
    private int DirectorySize;
    private int NumberOfFiles;
    private String DirectoryPath;
    // endregion

    // region Constructors
    public Directory() {} //Default Constructor
    public Directory(int directoryID ,String directoryName, int directorySize, int numberOfFiles, String directoryPath) {
        this.setDirectoryID(directoryID);
        this.setDirectoryName(directoryName);
        this.setDirectorySize(directorySize);
        this.setNumberOfFiles(numberOfFiles);
        this.setDirectoryPath(directoryPath);
    }
    // endregion

    // region Setters and Getters
    public int getDirectoryID() {
        return DirectoryID;
    }

    public void setDirectoryID(int directoryID) {
        DirectoryID = directoryID;
    }

    public String getDirectoryName() {
        return DirectoryName;
    }

    public void setDirectoryName(String directoryName) {
        DirectoryName = directoryName;
    }

    public int getDirectorySize() {
        return DirectorySize;
    }

    public void setDirectorySize(int directorySize) {
        DirectorySize = directorySize;
    }

    public int getNumberOfFiles() {
        return NumberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        NumberOfFiles = numberOfFiles;
    }

    public String getDirectoryPath() {
        return DirectoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        DirectoryPath = directoryPath;
    }
    // endregion

    // region Methods

    // endregion
}

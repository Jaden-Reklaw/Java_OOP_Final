package com.astontech.bo;

// 3. Create Objects to represent the 2 db entities
public class File extends Directory{
    // region Properties
    private int FileID;
    private String FileName;
    private String FileType;
    private int FileSize;
    private String FilePath;
    // Don't need DirectoryID since it inherits from Directory Class
    // endregion

    // region Constructors
    public File() {} //Default Constructor
    public File(int fileID, String fileName, String fileType, int fileSize, String filePath, int directoryID) {
        this.setFileID(fileID);
        this.setFileName(fileName);
        this.setFileType(fileType);
        this.setFileSize(fileSize);
        this.setFilePath(filePath);
        this.setDirectoryID(directoryID);
    }
    // endregion

    // region Setters and Getters
    public int getFileID() {
        return FileID;
    }

    public void setFileID(int fileID) {
        FileID = fileID;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public int getFileSize() {
        return FileSize;
    }

    public void setFileSize(int fileSize) {
        FileSize = fileSize;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }
    // endregion

    // region Methods

    // endregion
}

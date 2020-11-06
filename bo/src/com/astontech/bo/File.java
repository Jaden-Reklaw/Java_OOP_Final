package com.astontech.bo;

// 3. Create Objects to represent the 2 db entities
public class File {
    // region Properties
    private int FileID;
    private String FileName;
    private String FileType;
    private long FileSize;
    private String FilePath;
    // Don't need DirectoryID since it inherits from Directory Class
    // endregion

    // region Constructors
    public File() {} //Default Constructor
    public File(int fileID, String fileName, String fileType, long fileSize, String filePath) {
        this.setFileID(fileID);
        this.setFileName(fileName);
        this.setFileType(fileType);
        this.setFileSize(fileSize);
        this.setFilePath(filePath);
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

    public long getFileSize() {
        return FileSize;
    }

    public void setFileSize(long fileSize) {
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

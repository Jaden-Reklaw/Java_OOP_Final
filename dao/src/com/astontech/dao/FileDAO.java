package com.astontech.dao;

import com.astontech.bo.File;

import java.util.List;

public interface FileDAO {
    //GET Methods
    public File getFileById(int fileID);
    public List<File> getFileList();

    //EXECUTE Methods
    public int insertFile(File file);
    public boolean updateFile(File file);
    public boolean deleteFile(int fileID);
}

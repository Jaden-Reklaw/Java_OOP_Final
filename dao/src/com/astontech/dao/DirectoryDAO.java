package com.astontech.dao;

import com.astontech.bo.Directory;

import java.util.List;

public interface DirectoryDAO {
    //GET Methods
    public Directory getDirectoryById(int directoryID);
    public List<Directory> getDirectoryList();

    //EXECUTE Methods
    public int insertDirectory(Directory directory);
    public boolean updateDirectory(Directory directory);
    public boolean deleteDirectory(int directoryID);
}

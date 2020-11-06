package mysql;

import com.astontech.bo.File;
import com.astontech.dao.FileDAO;

import java.util.List;

public class FileDAOImpl extends MySQL implements FileDAO {
    @Override
    public File getFileById(int fileID) {
        return null;
    }

    @Override
    public List<File> getFileList() {
        return null;
    }

    @Override
    public int insertFile(File file) {
        return 0;
    }

    @Override
    public boolean updateFile(File file) {
        return false;
    }

    @Override
    public boolean deleteFile(int fileID) {
        return false;
    }
}

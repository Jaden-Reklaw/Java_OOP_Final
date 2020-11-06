package mysql;

import com.astontech.bo.Directory;
import com.astontech.bo.File;
import com.astontech.dao.FileDAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDAOImpl extends MySQL implements FileDAO {
    @Override
    public File getFileById(int fileID) {
        //connect to database from MySQL class using connect method
        Connect();
        //not instantiated since if no records returned don't use memory
        File file = null;

        try {
            String storeProcedure = "{CALL USP_GetFile(?, ?)}";
            CallableStatement cStmt = connection.prepareCall(storeProcedure);

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, fileID);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                file = HydrateObject(rs);
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }

        return file;
    }

    @Override
    public List<File> getFileList() {
        //connect to database from MySQL class using connect method
        Connect();
        //not instantiated since if no records returned don't use memory
        List<File> fileList = new ArrayList<File>();

        try {
            String storeProcedure = "{CALL USP_GetFile(?, ?)}";
            CallableStatement cStmt = connection.prepareCall(storeProcedure);

            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                fileList.add(HydrateObject(rs));
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }

        return fileList;
    }

    @Override
    public int insertFile(File file) {
        //Connect to database
        Connect();
        int id = 0; //thing to change on return

        try {
            //CALL USP_ExecFile(QueryID, FileID, FileName, FileType, FileSize, FilePath, DirectoryID)
            String storeProcedure = "{CALL USP_ExecFile(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(storeProcedure);

            statement.setInt(1, INSERT);
            statement.setInt(2, 0);
            statement.setString(3, file.getFileName());
            statement.setString(4, file.getFileType());
            statement.setInt(5, file.getFileSize());
            statement.setString(6, file.getFilePath());
            statement.setInt(7, file.getDirectoryID());

            //Execute query and get last id created
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id;
    }

    @Override
    public boolean updateFile(File file) {
        return false;
    }

    @Override
    public boolean deleteFile(int fileID) {
        return false;
    }

    private static File HydrateObject(ResultSet rs) throws SQLException {
        //See what rs object is hydrating the File object
        //Create File Object Use Constructor to Hydrate the object
        //(int fileID, String fileName, String fileType, int fileSize, String filePath, int directoryID)
        File file = new File(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getString(5),
                rs.getInt(6)
        );

        return file;
    }
}

package mysql;

import com.astontech.bo.Directory;
import com.astontech.dao.DirectoryDAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

//4.Create DAO layer for full CRUD on the 2 entities
public class DirectoryDAOImpl extends MySQL implements DirectoryDAO {
    @Override
    public Directory getDirectoryById(int directoryID) {
        //connect to database from MySQL class using connect method
        Connect();
        //not instantiated since if no records returned don't use memory
        Directory directory = null;

        try {
            String storeProcedure = "{CALL USP_GetDirectory(?, ?)}";
            CallableStatement cStmt = connection.prepareCall(storeProcedure);

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, directoryID);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateObject(rs);
            }
            connection.close();
        } catch (SQLException ex) {
            logger.error(ex);

        }
        return directory;
    }

    @Override
    public List<Directory> getDirectoryList() {
        //connect to database from MySQL class using connect method
        Connect();
        //not instantiated since if no records returned don't use memory
        List<Directory> directoryList = new ArrayList<Directory>();

        try {
            String storeProcedure = "{CALL USP_GetDirectory(?, ?)}";
            CallableStatement cStmt = connection.prepareCall(storeProcedure);

            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                directoryList.add(HydrateObject(rs));
            }
            connection.close();
        } catch (SQLException ex) {
            logger.error(ex);

        }
        return directoryList;
    }

    @Override
    public int insertDirectory(Directory directory) {
        //Connect to database
        Connect();
        int id = 0; //thing to change on return

        try {
            //CALL USP_ExecDirectory(QueryID, DirectoryID, DirectoryName, DirectorySize, NumberOfFiles, DirectoryPath);
            String storeProcedure = "{CALL USP_ExecDirectory(?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(storeProcedure);

            statement.setInt(1, INSERT);
            statement.setInt(2, 0);
            statement.setString(3, directory.getDirectoryName());
            statement.setLong(4, directory.getDirectorySize());
            statement.setInt(5, directory.getNumberOfFiles());
            statement.setString(6, directory.getDirectoryPath());

            //Execute query and get last id created
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);

            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return id;
    }

    @Override
    public boolean updateDirectory(Directory directory) {
        //Connect to database
        Connect();
        int id = 0; //thing to change on return

        try {
            //CALL USP_ExecDirectory(QueryID, DirectoryID, DirectoryName, DirectorySize, NumberOfFiles, DirectoryPath);
            String storeProcedure = "{CALL USP_ExecDirectory(?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(storeProcedure);

            statement.setInt(1, UPDATE);
            statement.setInt(2, directory.getDirectoryID());
            statement.setString(3, directory.getDirectoryName());
            statement.setLong(4, directory.getDirectorySize());
            statement.setInt(5, directory.getNumberOfFiles());
            statement.setString(6, directory.getDirectoryPath());

            //Execute query and get last id created
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id > 0;
    }

    @Override
    public boolean deleteDirectory(int directoryID) {
        //Connect to database
        Connect();
        int id = 0; //thing to change on return

        try {
            //CALL USP_ExecDirectory(QueryID, DirectoryID, DirectoryName, DirectorySize, NumberOfFiles, DirectoryPath);
            String storeProcedure = "{CALL USP_ExecDirectory(?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(storeProcedure);

            statement.setInt(1, DELETE);
            statement.setInt(2, directoryID);
            statement.setString(3, "");
            statement.setInt(4, 0);
            statement.setInt(5, 0);
            statement.setString(6, "");

            //Execute query and get last id created
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id > 0;
    }

    //Display Directory with Most Files
    public Directory getDirectoryMostFiles() {
        //connect to database from MySQL class using connect method
        Connect();
        //not instantiated since if no records returned don't use memory
        Directory directory = null;

        try {
            String storeProcedure = "{CALL USP_GetDirectory(?, ?)}";
            CallableStatement cStmt = connection.prepareCall(storeProcedure);

            cStmt.setInt(1, 30);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateObject(rs);
            }
            connection.close();
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return directory;
    }

    //Display Biggest Directory
    public Directory getBiggestDirectory() {
        //connect to database from MySQL class using connect method
        Connect();
        //not instantiated since if no records returned don't use memory
        Directory directory = null;

        try {
            String storeProcedure = "{CALL USP_GetDirectory(?, ?)}";
            CallableStatement cStmt = connection.prepareCall(storeProcedure);

            cStmt.setInt(1, 40);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateObject(rs);
            }
            connection.close();
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return directory;
    }

    public static void ClearDB() {
        //Connect to database
        Connect();

        try {
            //CALL USP_ExecDirectory(QueryID, DirectoryID, DirectoryName, DirectorySize, NumberOfFiles, DirectoryPath);
            String storeProcedure = "{CALL USP_ExecDirectory(?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(storeProcedure);

            statement.setInt(1, 40);
            statement.setInt(2, 0);
            statement.setString(3, "");
            statement.setInt(4, 0);
            statement.setInt(5, 0);
            statement.setString(6, "");

            //Execute query
            statement.executeQuery();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("Database Cleared");
    }

    private static Directory HydrateObject(ResultSet rs) throws SQLException {
        //See what rs object is hydrating the Directory object with
        //Create Directory Object Use Constructor to Hydrate the object
        //(int directoryID,String directoryName, int directorySize, int numberOfFiles, String directoryPath)
        Directory directory = new Directory(
                rs.getInt(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getString(5)
        );

        return directory;
    }
}

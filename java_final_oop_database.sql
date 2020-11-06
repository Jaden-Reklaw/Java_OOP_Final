/*
	Java OOP Final Schema
	1. Create a seperate schema in your database along with two tables, and 
    appropriate CRUD procedures.
	a. Table 1: Name=File, Fields={fileName, fileType, fileSize, path}
	b. Table 2: Name=Directory, Fields={dirName, dirSize, numberOfFiles, path}
	c. (These two tables should be related)
*/

-- Table 1: Name=File, Fields={fileName, fileType, fileSize, path}
CREATE TABLE File(
	FileID INT NOT NULL AUTO_INCREMENT,
    FileName VARCHAR(50) DEFAULT NULL,
    FileType VARCHAR(5) DEFAULT NULL,
    FileSize BIGINT DEFAULT NULL,
    FilePath VARCHAR(500) DEFAULT NULL,
    DirectoryID INT DEFAULT NULL,
    PRIMARY KEY(FileID),
    FOREIGN KEY (DirectoryID) REFERENCES Directory(DirectoryID)
);

-- Table 2: Name=Directory, Fields={dirName, dirSize, numberOfFiles, path}
CREATE TABLE Directory(
	DirectoryID INT NOT NULL AUTO_INCREMENT,
    DirectoryName VARCHAR(50) DEFAULT NULL,
    DirectorySize BIGINT DEFAULT NULL,
    NumberOfFiles INT DEFAULT 0,
    DirectoryPath VARCHAR(500) DEFAULT NULL,
    PRIMARY KEY(DirectoryID)
);

-- CRUD STORED PROCEDURE FOR FILE TABLE
-- GET FILE
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `USP_GetFile`(IN QueryID INT, IN FileID INT)
BEGIN
	-- START GET_FILE_BY_ID
    IF(QueryID = 10) THEN
		BEGIN
			SELECT a.*
            FROM File a
            WHERE a.FileID = FileID;
        END;
    -- FINISH GET_FILE_BY_ID
    
    -- START GET_FILE_COLLECTION
    ELSEIF(QueryID = 20) THEN
		BEGIN
			SELECT a.*
            FROM File a;
        END;
    -- FINISH GET_FILE_COLLECTION
    
    -- TERMINATE IF STATEMENTS
    END IF;
END$$
DELIMITER ;

-- INSERT, UPDATE & DELETE FILE
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `USP_ExecFile`(
	IN QueryID INT,
    IN FileID INT,
    IN FileName VARCHAR(50),
    IN FileType VARCHAR(5),
    IN FileSize BIGINT,
    IN FilePath VARCHAR(500),
    IN DirectoryID INT
)
BEGIN
	-- START INSERT
	IF(QueryID = 10) THEN
		BEGIN
			INSERT INTO File(FileName, FileType, FileSize, FilePath, DirectoryID)
            VALUES(FileName, FileType, FileSize, FilePath, DirectoryID);
            SELECT LAST_INSERT_ID();
        END;
    -- FINISH INSERT
    
    -- START UPDATE
    ELSEIF(QueryID = 20) THEN
		BEGIN
			UPDATE File a SET
            a.FileName = IFNULL(FileName, a.FileName),
            a.FileType = IFNULL(FileType, a.FileType),
            a.FileSize = IFNULL(FileSize, a.FileSize),
            a.FilePath = IFNULL(FilePath, a.FilePath),
            a.DirectoryID = IFNULL(DirectoryID, a.DirectoryID)
            WHERE a.FileID = FileID;
            SELECT ROW_COUNT();
        END;
    -- FINISH UDPATE
    
    -- START DELETE
	ELSEIF(QueryID = 30) THEN
		BEGIN
			DELETE FROM File a
            WHERE a.FileID = FileID;
            SELECT ROW_COUNT();
        END;
    -- FINISH DELETE
    
    -- TERMINATE IF STATEMENTS
    END IF;
END$$
DELIMITER ;

-- CRUD STORED PROCEDURE FOR DIRECTORY TABLE
-- GET DIRECTORY
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `USP_GetDirectory`(IN QueryID INT, IN DirectoryID INT)
BEGIN

	-- START GET_DIRECTORY_BY_ID
    IF(QueryID = 10)THEN
		BEGIN
			SELECT a.*
            FROM Directory a
            WHERE a.DirectoryID = DirectoryID;
        END;
    -- FINISH GET_DIRECTORY_BY_ID
    
    -- START GET_DIRECTORY_COLLECTION
    ELSEIF(QueryID = 20) THEN
		BEGIN
			SELECT a.*
            FROM Directory a;
        END;
    -- FINISH GET_DIRECTORY_COLLECTION
    
    -- TERMINATE IF STATEMENTS
    END IF;
END$$
DELIMITER ;

-- INSERT, UPDATE & DELETE DIRECTORY
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `USP_ExecDirectory`(
	IN QueryID INT,
	IN DirectoryID INT,
    IN DirectoryName VARCHAR(50),
    IN DirectorySize BIGINT,
    IN NumberOfFiles INT,
    IN DirectoryPath VARCHAR(500)
)
BEGIN
	-- START INSERT
    IF(QueryID = 10) THEN
		BEGIN
			INSERT INTO Directory(DirectoryName, DirectorySize,  NumberOfFiles, DirectoryPath)
            VALUES(DirectoryName, DirectorySize,  NumberOfFiles, DirectoryPath);
            SELECT LAST_INSERT_ID();
        END;
    -- END INSERT
    
    -- START UPDATE
    ELSEIF(QueryID = 20) THEN
		BEGIN
			UPDATE Directory a SET
            a.DirectoryName = IFNULL(DirectoryName, a.DirectoryName),
            a.DirectorySize = IFNULL(DirectorySize, a.DirectorySize),
            a.NumberOfFiles = IFNULL(NumberOfFiles, a.NumberOfFiles),
            a.DirectoryPath = IFNULL(DirectoryPath, a.DirectoryPath)
            WHERE a.DirectoryID = DirectoryID;
            SELECT ROW_COUNT();
        END;
    -- END UPDATE
    
    -- START DELETE
    ELSEIF(QueryID = 30) THEN
		BEGIN
			DELETE FROM Directory a
            WHERE a.DirectoryID = DirectoryID;
            SELECT ROW_COUNT();
        END;
    -- END DELETE
	
    -- TERMINATE IF STATEMENTS
    END IF;
END$$
DELIMITER ;

-- INSERT SOME DIRECTORIES USING STORED PROCEDURE
CALL `final_oop`.`USP_ExecDirectory`(10, 0, 'Prime_Academy', 20, 3, '/home/user1/Desktop');
CALL `final_oop`.`USP_ExecDirectory`(10, 0, 'Projects', 5, 1, '/home/user1/Desktop/Prime_Academy');
CALL `final_oop`.`USP_ExecDirectory`(10, 0, 'Aston_Technologies', 25, 4, '/home/user1/Desktop');
CALL `final_oop`.`USP_ExecDirectory`(10, 0, 'Developer_Software', 25, 4, '/home/user1/Desktop/Aston_Technologies');
CALL `final_oop`.`USP_ExecDirectory`(10, 0, 'Hawaii_Pacific_University', 35, 6, '/home/user1/Desktop');
CALL `final_oop`.`USP_ExecDirectory`(10, 0, 'Test', 1, 0, '/home/user1/Desktop');

-- INSERT SOME FILES USING STORED PROCEDURES
CALL `final_oop`.`USP_ExecFile`(10, 0, 'BookDB.sql', '.sql', 5, '/home/user1/Desktop/Prime_Academy/BookDB.sql', 1);
CALL `final_oop`.`USP_ExecFile`(10, 0, 'index.html', '.html', 13, '/home/user1/Desktop/Prime_Academy/index.html', 1);
CALL `final_oop`.`USP_ExecFile`(10, 0, 'style.css', '.css', 7, '/home/user1/Desktop/Prime_Academy/style.css', 1);

-- UPDATE INFORMATION USING STORED PROCEDURE
CALL `final_oop`.`USP_ExecDirectory`(20, 6, 'SecretsOfUniverse', 1000000, 1, NULL);
CALL `final_oop`.`USP_ExecFile`(20, 2, NULL, NULL, 13, NULL, NULL);
CALL `final_oop`.`USP_ExecFile`(20, 3, NULL, NULL, 7, NULL, NULL);

-- DELETE INFORMATION USING STORED PROCEDURES
CALL `final_oop`.`USP_ExecFile`(30, 4, NULL, NULL, NULL, NULL, NULL);
CALL `final_oop`.`USP_ExecDirectory`(30, 7, NULL, NULL, NULL, NULL);


-- GET INFORMATION BACK USING STORE PROCEDURES
CALL `final_oop`.`USP_GetDirectory`(20, 0);
CALL `final_oop`.`USP_GetDirectory`(10, 3);
CALL `final_oop`.`USP_GetFile`(20, 0);
CALL `final_oop`.`USP_GetFile`(10, 2);

-- Clear All tables data
DELETE FROM File;
DELETE FROM Directory;

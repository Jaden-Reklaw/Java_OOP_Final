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
    FileSize VARCHAR(50) DEFAULT NULL,
    FilePath VARCHAR(500) DEFAULT NULL,
    DirectoryID INT DEFAULT NULL,
    PRIMARY KEY(FileID),
    FOREIGN KEY (DirectoryID) REFERENCES Directory(DirectoryID)
);

-- Table 2: Name=Directory, Fields={dirName, dirSize, numberOfFiles, path}
CREATE TABLE Directory(
	DirectoryID INT NOT NULL AUTO_INCREMENT,
    DirectoryName VARCHAR(50) DEFAULT NULL,
    DirectorySize VARCHAR(50) DEFAULT NULL,
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
            FROM FILE a
            WHERE a.FileID = FileID;
        END;
    -- FINISH GET_FILE_BY_ID
    
    -- START GET_FILE_COLLECTION
    ELSEIF(QueryID = 20) THEN
		BEGIN
			SELECT a.*
            FROM FILE a;
        END;
    -- FINISH GET_FILE_COLLECTION
    
    -- TERMINATE IF STATEMENTS
    END IF;
END$$
DELIMITER ;

-- INSERT, UPDATE & DELETE FILE


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



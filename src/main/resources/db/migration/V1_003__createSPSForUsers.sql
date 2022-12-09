DELIMITER $$
DROP PROCEDURE IF EXISTS getAllUsersProc $$
CREATE PROCEDURE getAllUsersProc(in pageView int, in pageCount int)
BEGIN
	SELECT id, email, phone_number, last_name, first_name FROM user
    ORDER BY id ASC
	LIMIT pageCount OFFSET pageView;
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS deleteMyAccountProc $$
CREATE PROCEDURE deleteMyAccountProc(in userEmail varchar(300))
BEGIN
    delete from user where email = userEmail;
END $$

DELIMITER $$
DROP PROCEDURE IF EXISTS getUserDetailsProc $$
CREATE PROCEDURE getUserDetailsProc(in userEmail varchar(255))
BEGIN
    select id, email, phone_number, last_name, first_name from user where email = userEmail;
END $$

DELIMITER ;

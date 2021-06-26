--Author: Justin Wallace
--Date: 6/22/2021

--inserting dummy data for project 0

--setting Schema
SET search_path TO P0;

--payPeriod
INSERT INTO payPeriods(payPeriod, months)
VALUES(1, '[2021-01-01, 2021-01-31]'),
	(2, '[2021-02-01, 2021-02-28]'),
	(3, '[2021-03-01, 2021-03-31]'),
	(4, '[2021-04-01, 2021-04-30]'),
	(5, '[2021-05-01, 2021-05-31]'),
	(6, '[2021-06-01, 2021-06-30]'),
	(7, '[2021-07-01, 2021-07-31]'),
	(8, '[2021-08-01, 2021-08-31]'),
	(9, '[2021-09-01, 2021-09-30]'),
	(10, '[2021-10-01, 2021-10-31]'),
	(11, '[2021-11-01, 2021-11-30]'),
	(12, '[2021-12-01, 2021-12-31]')
RETURNING *;

--userAuth
INSERT INTO userAuth(userName, userPass)
VALUES('username', 'password'),
	('qwerty', '12345'),
	('asdfg', '67890')
RETURNING *;

--users
INSERT INTO users(userType, firstName, lastName, currentWagePerHour)
VALUES('manager', 'Tirhza', 'Riley', 14.50),
	('employee', 'Caitlynn', 'Cox', 12.00),
	('employee', 'Arron', 'Ratshuber', 9.50)
RETURNING *;

--timeSheets
INSERT INTO timeSheets(userID, periodYear)
VALUES(1, 2021),
	(2, 2021),
	(2, 2022),
	(3, 2021)
RETURNING *;

--timePeriodEntries
INSERT INTO timePeriodEntries(userID, timeSheetID, payPeriod, wagePerHour)
VALUES(2, 2, 3, 10.00)
RETURNING *;

--weeklyEntries
INSERT INTO weeklyEntries(entryID, sunday, monday, tuesday, wednesday, thursday, friday, saturday)
VALUES(1, 0, 8, 8, 8, 0, 8, 8)
RETURNING *;

--for testing cascading delete
--DELETE FROM userAuth WHERE userID = 2;

--SELECT payPeriod, months
--FROM payperiods
--WHERE months @> DATE '2021-06-23';

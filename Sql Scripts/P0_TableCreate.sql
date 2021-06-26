--Author: Justin Wallace
--Date: 6/22/2021

--Schema CREATE 
CREATE SCHEMA P0;

--Set SCHEMA 
SET search_path TO P0;

--Create tables for project 0

--user authentication
CREATE TABLE userAuth(
	userID SMALLSERIAL PRIMARY KEY,
	userName TEXT NOT NULL,
	userPass TEXT NOT NULL
);

--employees and managers
CREATE TABLE users(
	userID SMALLSERIAL PRIMARY KEY,
	userType VARCHAR(10) NOT NULL,
	firstName TEXT NOT NULL,
	lastName TEXT NOT NULL,
	currentWagePerHour NUMERIC(4, 2) NOT NULL,
	CONSTRAINT fk_user_identity FOREIGN KEY(userID)
	REFERENCES userAuth(userID) ON DELETE CASCADE
);

--timesheets
CREATE TABLE timeSheets(
	userID INT2 NOT NULL,
	timeSheetID SERIAL UNIQUE NOT NULL,
	periodYear SMALLINT NOT NULL,
	CONSTRAINT pk_time_user_id PRIMARY KEY(userID, timeSheetID),
	CONSTRAINT fk_user_identity FOREIGN KEY(userID)
	REFERENCES users(userID) ON DELETE CASCADE
	
);

--payPeriods
CREATE TABLE payPeriods(
	payPeriod INT PRIMARY KEY NOT NULL,
	months DATERANGE NOT NULL
);

--time period entries
CREATE TABLE timePeriodEntries(
	userID INT2 NOT NULL,
	timeSheetID INT NOT NULL,
	payPeriod INT NOT NULL,
	entryID SERIAL PRIMARY KEY,
	wagePerHour NUMERIC(4, 2) NOT NULL,
	CONSTRAINT fk_period_id FOREIGN KEY(payPeriod)
	REFERENCES payPeriods(payPeriod),
	CONSTRAINT fk_user_timesheet_id FOREIGN KEY(userID, timeSheetID)
	REFERENCES timeSheets(userID, timeSheetID) ON DELETE CASCADE
);

--weeklyEntries
CREATE TABLE weeklyEntries(
	entryID INT NOT NULL,
	sunday DECIMAL(4, 2),
	monday DECIMAL(4, 2),
	tuesday DECIMAL(4, 2),
	wednesday DECIMAL(4, 2),
	thursday DECIMAL(4, 2),
	friday DECIMAL(4, 2),
	saturday DECIMAL(4, 2),
	CONSTRAINT fk_entry_id FOREIGN KEY(entryID)
	REFERENCES timePeriodEntries(entryID) ON DELETE CASCADE
);

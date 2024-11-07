import observer.eventsmanagement.SMSSupportListener;
import RefactringGURU.Editor;
import RefactringGURU.LogOpenListener; 
import RefactringGURU.EmailNotificationListener; 
public class ObserverPatternDemo {
    public static void main(String[] args) {
        try {
            
            Editor editor = new Editor();
            
            
            editor.events.subscribe("open", new LogOpenListener("log.txt"));
            editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));
            
            
            SMSSupportListener smsListener = new SMSSupportListener("+1234567890", "This is a test SMS.");
            editor.events.subscribe("save", smsListener);
            
            
            editor.openFile("test.txt");
            editor.saveFile();

            smsListener.setDefaultSMS("This is a very long SMS that exceeds the 160-character limit and will therefore trigger a warning to ensure that the message is shortened.");
            editor.saveFile();  
            
        } catch (Exception e) {
            
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}





create Database  lab06
USE lab06;
GO

CREATE TABLE Branch (
    branchNo VARCHAR(20) NOT NULL PRIMARY KEY, 
    street VARCHAR(100) NOT NULL, 
    city VARCHAR(50) NOT NULL, 
    postcode VARCHAR(20) NOT NULL
);

CREATE TABLE Staff (
    staffNo VARCHAR(20) NOT NULL PRIMARY KEY, 
    fName VARCHAR(50) NOT NULL, 
    lName VARCHAR(50) NOT NULL, 
    position VARCHAR(50) NOT NULL, 
    sex VARCHAR(1) NOT NULL, 
    DOB DateTime NOT NULL, 
    salary DECIMAL(10, 2) NOT NULL, 
    branchNo VARCHAR(20) NOT NULL REFERENCES Branch(branchNo)
);

CREATE TABLE Client (
    clientNo VARCHAR(20) NOT NULL PRIMARY KEY, 
    fName VARCHAR(50) NOT NULL, 
    lName VARCHAR(50) NOT NULL, 
    telNo VARCHAR(20) NOT NULL, 
    prefType VARCHAR(50) NOT NULL, 
    maxRent DECIMAL(10, 2) NOT NULL
);

CREATE TABLE PrivateOwner (
    ownerNo VARCHAR(20) NOT NULL PRIMARY KEY, 
    fName VARCHAR(50) NOT NULL, 
    lName VARCHAR(50) NOT NULL, 
    address VARCHAR(50) NOT NULL, 
    telNo VARCHAR(20) NOT NULL
);

CREATE TABLE PropertyForRent (
    propertyNo VARCHAR(20) NOT NULL PRIMARY KEY, 
    street VARCHAR(100) NOT NULL, 
    city VARCHAR(50) NOT NULL, 
    postcode VARCHAR(20) NOT NULL, 
    type VARCHAR(10) NOT NULL, 
    rooms INT NOT NULL, 
    rent DECIMAL(10, 2) NOT NULL, 
    ownerNo VARCHAR(20) REFERENCES PrivateOwner(ownerNo), 
    staffNo VARCHAR(20) NOT NULL REFERENCES Staff(staffNo), 
    branchNo VARCHAR(20) NOT NULL REFERENCES Branch(branchNo)
);

CREATE TABLE Viewing (
    clientNo VARCHAR(20) NOT NULL REFERENCES Client(clientNo), 
    propertyNo VARCHAR(20) NOT NULL REFERENCES PropertyForRent(propertyNo),  
    viewDate DateTime NOT NULL,  
    comment VARCHAR(200)NOT  NULL
);

CREATE TABLE Registration (
    clientNo VARCHAR(20) NOT NULL REFERENCES Client(clientNo),
    branchNo VARCHAR(20) NOT NULL REFERENCES Branch(branchNo),
    staffNo VARCHAR(20) NOT NULL REFERENCES Staff(staffNo), 
    dateJoined DateTime NOT NULL
);

INSERT INTO Branch (branchNo, street, city, postcode) 
VALUES 
    ('B001', 'NH#79 I-10/2', 'Peshawar', 'N52000'),
    ('B002', 'NH#78 Hayatabad', 'Mardan', 'N53000'),
    ('B003', 'NH#79 GT Road', 'Peshawar', 'N52000'), 
    ('B004', 'NH#78 Saddar', 'Mardan', 'N53000');

INSERT INTO Staff (staffNo, fName, lName, position, sex, DOB, salary, branchNo) 
VALUES 
    ('NSA85', 'Zainab', 'Khan', 'Assistant', 'F', '1970-01-01', 9000.00, 'B001'),
    ('NSA86', 'Asfandyar', 'Shinwari', 'Supervisor', 'M', '1980-01-01', 10000.00, 'B002'),
    ('NSA87', 'Hameed', 'Afridi', 'Admin', 'M', '1990-01-01', 11000.00, 'B003'),
    ('NSA88', 'Ayesha', 'Yousafzai', 'HR', 'F', '2000-01-01', 12000.00, 'B004');

INSERT INTO Client (clientNo, fName, lName, telNo, prefType, maxRent) 
VALUES 
    ('C001', 'Hassan', 'Bangash', '1234567890', 'Apartment', 1500.00),
    ('C002', 'Rabia', 'Orakzai', '0987654321', 'House', 2000.00);

INSERT INTO PrivateOwner (ownerNo, fName, lName, address, telNo) 
VALUES 
    ('O001', 'Naseem', 'Khattak', '123 Main St', '1112223333'),
    ('O002', 'Shahid', 'Momand', '456 Oak Ave', '4445556666');

INSERT INTO PropertyForRent (propertyNo, street, city, postcode, type, rooms, rent, ownerNo, staffNo, branchNo) 
VALUES 
    ('P001', 'University Rd', 'Peshawar', 'N52000', 'House', 3, 1000.00, 'O001', 'NSA85', 'B001'),
    ('P002', 'Ring Rd', 'Mardan', 'N53000', 'Apartment', 2, 800.00, 'O002', 'NSA86', 'B002');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment) 
VALUES 
    ('C001', 'P001', '2024-10-01', 'Liked the property'),
    ('C002', 'P002', '2024-10-05', 'Needs some repairs');

INSERT INTO Registration (clientNo, branchNo, staffNo, dateJoined) 
VALUES 
    ('C001', 'B001', 'NSA85', '2024-09-01'),
    ('C002', 'B002', 'NSA86', '2024-09-10');

SELECT * FROM Branch;
SELECT * FROM Staff;
SELECT * FROM Client;
SELECT * FROM PrivateOwner;
SELECT * FROM PropertyForRent;
SELECT * FROM Viewing;
SELECT * FROM Registration;

UPDATE Branch 
SET city = 'Swat' 
WHERE branchNo = 'B001';

DELETE FROM Staff 
WHERE staffNo = 'NSA85';

ALTER TABLE Branch 
DROP COLUMN postcode;

ALTER TABLE Branch 
ADD country VARCHAR(50);

SELECT * FROM Staff;

SELECT * FROM Branch, Staff WHERE Branch.branchNo = Staff.branchNo;

SELECT * FROM Branch WHERE branchNo = 'B003';
UPDATE Branch 
SET street = 'NH#80 Ring Road' 
WHERE branchNo = 'B002';

UPDATE Branch 
SET city = 'Swabi' 
WHERE branchNo = 'B003';

UPDATE Client 
SET maxRent = 1600.00 
WHERE clientNo = 'C001';

UPDATE Client 
SET telNo = '0111222333' 
WHERE clientNo = 'C002';

Select city from Branch 
Select distinct(city) from
Branch
Select city As MyCity from
	Branch
	
	Select staffNo as ID, fName as
	FirstName, lName as [Last
	Name], position as Position,
	sex as Gender, DOB as [Date
	of Birth], 
	salary as Income
	from Staff

	Select (fName +' '+lName) as
	[Name], position as Position
	from Staff

	SELECT staffNo, fName, lName,position, salary FROM Staff WHERE salary >10000;

	SELECT * FROM Branch WHERE city = 'Mardan' or city= 'Peshawar'

	SELECT staffNo, fName, lName,position, salary FROM Staff WHERE salary BETWEEN 9000 AND 11000;

	SELECT staffNo, fName, lName,position, salary FROM Staff WHERE salary >= 9000 AND salary	<=11000;

	SELECT staffNo, fName, lName,position FROM Staff WHERE position IN ('Manager','Supervisor');

SELECT staffNo, fName, lName,position FROM Staff WHERE position = 'Supervisor'OR position ='Manager' ;

	SELECT  fName, lName FROM PrivateOwner WHERE address LIKE '%Main%';

SELECT  fName, lName FROM PrivateOwner WHERE address LIKE '%Main%';

SELECT staffNo, fName ,lName, salary From Staff WHERE fName LIKE '%Z_____';

SELECT staffNo, fName ,lName, salary From Staff WHERE fName LIKE '%_____b';

SELECT clientNo, viewDate FROM Viewing WHERE propertyNo = 'P001' AND comment IS NULL;

--> Home Work

SELECT DISTINCT ownerNo FROM PropertyForRent;

SELECT DISTINCT fName FROM Staff;

SELECT 
    staffNo AS StaffID, 
    fName AS FirstName, 
    lName AS LastName, 
    position AS JobPosition, 
    sex AS Gender, 
    DOB AS DateOfBirth, 
    salary AS SalaryAmount, 
    branchNo AS BranchID 
FROM Staff;

SELECT 
    clientNo AS ClientID, 
    fName AS FirstName, 
    lName AS LastName, 
    telNo AS Telephone, 
    prefType AS PreferredType, 
    maxRent AS MaximumRent 
FROM Client;

SELECT staffNo, fName, lName, position, salary 
FROM Staff 
WHERE salary > 10000;

SELECT staffNo, fName, lName, position 
FROM Staff 
WHERE position IN ('Manager', 'Supervisor');



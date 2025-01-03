Create database MovieTheater
USe MovieTheater
Go

CREATE TABLE Movies (
    MovieID INT PRIMARY KEY,
    Title VARCHAR(100),
    Genre VARCHAR(50),
    Language VARCHAR(50),
    Duration INT,
    ReleaseDate DATE,
    Rating FLOAT
);

INSERT INTO Movies VALUES 
(1, 'Pathaan', 'Action', 'Hindi', 146, '2023-01-25', 8.2),
(2, 'The Legend of Maula Jatt', 'Action', 'Punjabi', 153, '2022-10-13', 8.8),
(3, 'Avengers: Endgame', 'Action', 'English', 181, '2019-04-26', 8.4),
(4, 'Dangal', 'Sports', 'Hindi', 161, '2016-12-23', 8.3),
(5, 'Iron Man', 'Action', 'English', 126, '2008-05-02', 7.9);

CREATE TABLE Theaters (
    TheaterID INT PRIMARY KEY,
    Name VARCHAR(100),
    Location VARCHAR(100),
    TotalScreens INT
);

INSERT INTO Theaters VALUES 
(1, 'PVR Cinemas', 'Mumbai', 10),
(2, 'Cinepax', 'Lahore', 5),
(3, 'AMC Theaters', 'New York', 15),
(4, 'INOX', 'Bangalore', 8),
(5, 'Atrium Cinemas', 'Karachi', 6);

CREATE TABLE Screens (
    ScreenID INT PRIMARY KEY,
    TheaterID INT,
    ScreenNumber INT,
    SeatingCapacity INT,
    FOREIGN KEY (TheaterID) REFERENCES Theaters(TheaterID)
);

INSERT INTO Screens VALUES 
(1, 1, 1, 200),
(2, 2, 1, 150),
(3, 3, 1, 300),
(4, 4, 2, 250),
(5, 5, 3, 180);

CREATE TABLE Seats (
    SeatID INT PRIMARY KEY,
    ScreenID INT,
    SeatNumber INT,
    SeatType VARCHAR(50),
    IsOccupied INT,
    FOREIGN KEY (ScreenID) REFERENCES Screens(ScreenID)
);

  --> 0 For False and 1 Is For True

INSERT INTO Seats (SeatID, ScreenID, SeatNumber, SeatType, IsOccupied) VALUES 
(1, 1, 1, 'Premium', 0),
(2, 1, 2, 'Economy', 0),
(3, 2, 3, 'Premium', 1),
(4, 3, 4, 'Economy', 0),
(5, 4, 5, 'Premium', 1);

CREATE TABLE Showtimes (
    ShowtimeID INT PRIMARY KEY,
    MovieID INT,
    ScreenID INT,
    ShowDate DATE,
    ShowTime TIME,
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID),
    FOREIGN KEY (ScreenID) REFERENCES Screens(ScreenID)
);

INSERT INTO Showtimes VALUES 
(1, 1, 1, '2024-01-15', '18:00:00'),
(2, 2, 2, '2024-01-15', '20:00:00'),
(3, 3, 3, '2024-01-15', '21:00:00'),
(4, 4, 4, '2024-01-16', '17:00:00'),
(5, 5, 5, '2024-01-16', '19:30:00');

CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100),
    PhoneNumber VARCHAR(15),
    Address VARCHAR(255)
);

INSERT INTO Customers VALUES 
(1, 'Ali', 'Khan', 'ali.khan@gmail.com', '923001234567', 'Karachi, Pakistan'),
(2, 'Ravi', 'Sharma', 'ravi.sharma@gmail.com', '919876543210', 'Delhi, India'),
(3, 'Steve', 'Rogers', 'steve.rogers@avengers.com', '1234567890', 'Brooklyn, USA'),
(4, 'Natasha', 'Romanoff', 'natasha.romanoff@avengers.com', '1122334455', 'Moscow, Russia'),
(5, 'Mehwish', 'Hayat', 'mehwish.hayat@gmail.com', '923458765432', 'Lahore, Pakistan');

CREATE TABLE Tickets (
    TicketID INT PRIMARY KEY,
    ShowtimeID INT,
    SeatID INT,
    CustomerID INT,
    Price FLOAT,
    BookingDate DATE,
    PaymentStatus VARCHAR(50),
    FOREIGN KEY (ShowtimeID) REFERENCES Showtimes(ShowtimeID),
    FOREIGN KEY (SeatID) REFERENCES Seats(SeatID),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Tickets VALUES 
(1, 1, 1, 1, 500.0, '2024-01-10', 'Paid'),
(2, 2, 2, 2, 700.0, '2024-01-10', 'Paid'),
(3, 3, 3, 3, 1000.0, '2024-01-10', 'Unpaid'),
(4, 4, 4, 4, 1200.0, '2024-01-11', 'Paid'),
(5, 5, 5, 5, 1500.0, '2024-01-12', 'Paid');

CREATE TABLE Staff (
    StaffID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Role VARCHAR(50),
    PhoneNumber VARCHAR(15),
    Email VARCHAR(100),
    TheaterID INT,
    FOREIGN KEY (TheaterID) REFERENCES Theaters(TheaterID)
);

INSERT INTO Staff VALUES 
(1, 'Imran', 'Ali', 'Manager', '923004567890', 'imran.ali@cinepax.com', 2),
(2, 'Rahul', 'Verma', 'Technician', '919843216789', 'rahul.verma@pvr.com', 1),
(3, 'Sara', 'Connor', 'Cashier', '9191122334455', 'sara.connor@inox.com', 4),
(4, 'Bruce', 'Banner', 'Projectionist', '1234567891', 'bruce.banner@amc.com', 3),
(5, 'Meera', 'Rajput', 'Attendant', '919765432109', 'meera.rajput@atrium.com', 5);

CREATE TABLE Concessions (
    ConcessionID INT PRIMARY KEY,
    ItemName VARCHAR(100),
    Price FLOAT,
    StockQuantity INT,
    TheaterID INT,
    FOREIGN KEY (TheaterID) REFERENCES Theaters(TheaterID)
);

INSERT INTO Concessions VALUES 
(1, 'Popcorn', 200.0, 50, 1),
(2, 'Coke', 150.0, 100, 1),
(3, 'Nachos', 250.0, 30, 2),
(4, 'Samosa', 100.0, 40, 4),
(5, 'Ice Cream', 300.0, 25, 5);

CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    ConcessionID INT,
    Quantity INT,
    TotalPrice FLOAT,
    OrderDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (ConcessionID) REFERENCES Concessions(ConcessionID)
);

INSERT INTO Orders VALUES 
(1, 1, 1, 2, 400.0, '2024-01-10'),
(2, 2, 2, 1, 150.0, '2024-01-10'),
(3, 3, 3, 3, 750.0, '2024-01-11'),
(4, 4, 4, 4, 400.0, '2024-01-11'),
(5, 5, 5, 2, 600.0, '2024-01-12');

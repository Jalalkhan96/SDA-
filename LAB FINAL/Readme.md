Smart Home Energy Saving System - Control Smart Light

This project is a simple implementation of a Smart Home Energy Saving System. The main use case demonstrated is "Control Smart Light". The system is implemented in Java, following a class diagram structure that defines specific roles for each class.

Project Overview

This system allows users to:

Authenticate themselves.

Connect to a Smart Home Hub.

Establish a connection with sensors.

Control a Smart Light via a Mobile App.

The control functionalities include:

Turning the light ON/OFF.

Setting a timer for the light.

Adjusting the light's intensity.

Changing the light's color.

Class Structure

1. User

Handles user authentication.

Attributes:

name: String - Name of the user.

password: String - User's password.

Methods:

authenticate(inputName: String, inputPassword: String): boolean - Validates user credentials.

2. SmartHomeHub

Manages the connection to the smart home hub.

Attributes:

connectionEstablished: boolean - Tracks if the connection is active.

Methods:

establishConnection() - Establishes a connection with the hub.

isConnectionEstablished(): boolean - Checks if the connection is established.

3. Connection

Represents the connection to a specific sensor.

Attributes:

sensorName: String - Name of the sensor.

sensorID: String - Unique ID of the sensor.

Methods:

connectToSensor() - Connects to the specified sensor.

4. SmartLight

Handles all operations related to the smart light.

Methods:

setTimer() - Sets a timer for the light.

changeState(isOn: boolean) - Turns the light ON/OFF.

setIntensityLevel(level: int) - Adjusts the light's intensity.

setColor(color: String) - Changes the light's color.

showAllFeatures() - Displays all the available features.

turnOn() - Turns the light ON.

turnOff() - Turns the light OFF.

5. MobileApp

Provides an interface to control the smart light via the app.

Methods:

setTimer() - Sets a timer for the light.

setState(isOn: boolean) - Turns the light ON/OFF.

setIntensityLevel(level: int) - Adjusts the light's intensity.

setColor(color: String) - Changes the light's color.

6. Main

The entry point of the application. Demonstrates the following workflow:

User authentication.

Connecting to the Smart Home Hub.

Establishing a sensor connection.

Controlling the Smart Light using the Mobile App.

Features

User Authentication:

Ensures secure access to the system.

Users must provide valid credentials.

Smart Home Hub:

Manages the connection to the system's hub.

Smart Light Control:

Turn ON/OFF the light.

Adjust the intensity level.

Change the light's color.

Set a timer for automatic control.

Mobile App Interface:

Provides user-friendly access to smart light controls.

How to Run the Project

Clone the Repository:

git clone <repository-url>

Navigate to the Project Directory:

cd SmartHomeEnergySavingSystem

Compile the Code:

javac Main.java

Run the Application:

java Main

Follow the Console Instructions:

Authenticate using a username and password.

Control the smart light via the simulated Mobile App.

Example Output

User authenticated successfully.
Connection with Smart Home Hub established.
Connected to sensor: Living Room Light (ID: Sensor123)
Timer for Smart Light set.
Smart Light turned ON.
Smart Light intensity set to level: 5.
Smart Light color set to: Blue.
Smart Light Features: Timer, Change State, Set Intensity, Set Color.
Smart Light turned OFF.

Project Dependencies

Java Development Kit (JDK) 8 or higher.

A text editor or IDE for Java (e.g., IntelliJ IDEA, Eclipse, or VS Code).

Author
 
 Name : Jalal Khan
RegNO:  FA22-BSE-093

Project for Smart Home Energy Saving System.

License

This project is licensed under the MIT License. Feel free to use and modify it for educational purposes.

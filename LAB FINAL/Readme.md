# Smart Home Energy Saving System - Control Smart Light

This project demonstrates a simple implementation of a **Smart Home Energy Saving System** with a focus on controlling smart lights. The system is implemented in **Java** and allows users to authenticate, connect to a smart home hub, and control smart light features via a mobile app interface.

---

## Project Overview

The system enables users to:

- **Authenticate** securely using their credentials.
- **Connect** to the Smart Home Hub.
- Establish a connection with **smart light sensors**.
- **Control a Smart Light** via a **Mobile App** with the following functionalities:
  - Turn the light **ON/OFF**.
  - Set a **timer** for the light.
  - Adjust the **intensity** of the light.
  - Change the **color** of the light.

---

## Features

### **User Authentication**
- Securely authenticate users using a valid username and password.

### **Smart Home Hub**
- Manage the connection to the smart home hub to control the light.

### **Smart Light Control**
- **Turn ON/OFF** the light.
- **Set intensity** to adjust the light's brightness.
- **Change color** for customized lighting.
- **Set a timer** for automatic control of the light.

### **Mobile App Interface**
- Offers a user-friendly interface to control the light features.

---

## Class Structure

### **User**
- Handles user authentication.

  **Attributes:**
  - `name` (String): User's name.
  - `password` (String): User's password.

  **Methods:**
  - `authenticate(inputName: String, inputPassword: String): boolean`: Validates user credentials.

---

### **SmartHomeHub**
- Manages connection to the smart home hub.

  **Attributes:**
  - `connectionEstablished` (boolean): Tracks the connection status.

  **Methods:**
  - `establishConnection()`: Establishes a connection to the hub.
  - `isConnectionEstablished()`: Checks if the connection is active.

---

### **Connection**
- Represents a connection to a specific sensor.

  **Attributes:**
  - `sensorName` (String): Name of the sensor.
  - `sensorID` (String): Unique sensor ID.

  **Methods:**
  - `connectToSensor()`: Connects to the specified sensor.

---

### **SmartLight**
- Handles operations related to the smart light.

  **Methods:**
  - `setTimer()`: Sets a timer for the light.
  - `changeState(isOn: boolean)`: Turns the light ON/OFF.
  - `setIntensityLevel(level: int)`: Adjusts the intensity level.
  - `setColor(color: String)`: Changes the color of the light.
  - `showAllFeatures()`: Displays all available features of the smart light.
  - `turnOn()`: Turns the light ON.
  - `turnOff()`: Turns the light OFF.

---

### **MobileApp**
- Provides an interface to control the smart light via the mobile app.

  **Methods:**
  - `setTimer()`: Sets a timer for the light.
  - `setState(isOn: boolean)`: Turns the light ON/OFF.
  - `setIntensityLevel(level: int)`: Adjusts the intensity level.
  - `setColor(color: String)`: Changes the color of the light.

---

### **Main**
- The entry point of the application.
  - User authentication.
  - Connecting to the Smart Home Hub.
  - Establishing sensor connection.
  - Controlling the Smart Light using the Mobile App.

---

## How to Run the Project

1. **Clone the Repository**  
   Clone the project to your local machine:
   ```bash
   git clone https://github.com/Jalalkhan96/SDA-/tree/main/LAB%20FINAL
Enter username: 
jalalkhan

Enter password: 
111

Authentication successful.
Connection to Smart Home Hub established.

Connected to sensor: Living Room Light (ID: Sensor123)
---
Please select an action:
1. Turn ON/OFF light
2. Set Timer
3. Adjust Intensity
4. Change Color
---
Enter your choice: 
1
Enter the light state (true for ON, false for OFF): 
true
Light turned ON.
---
Please select another action:
1. Turn ON/OFF light
2. Set Timer
3. Adjust Intensity
4. Change Color

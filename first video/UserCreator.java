/*  Install dependencies
 npm install express redis axios */

const express = require('express');
const axios = require('axios');
const redis = require('redis');

const redisClient = redis.createClient({
    host: 'localhost',
    port: 6379
});

const app = express();
const PORT = process.env.PORT || 3000;

const checkCache = (req, res, next) => {
    const { userId } = req.params;
    
    redisClient.get(userId, (err, data) => {
        if (err) throw err;

        if (data != null) {
            console.log('Cache hit');
            res.send(JSON.parse(data));
        } else {
            next();
        }
    });
};

const fetchUserData = async (userId) => {
    const response = await axios.get(`https://jsonplaceholder.typicode.com/users/${userId}`);
    return response.data;
};

app.get('/user/:userId', checkCache, async (req, res) => {
    const { userId } = req.params;

    try {
        const userData = await fetchUserData(userId);
        
        redisClient.setex(userId, 3600, JSON.stringify(userData));

        res.json(userData);
    } catch (error) {
        console.error('Error fetching data:', error);
        res.status(500).json({ error: 'An error occurred' });
    }
});

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});









// violating srp

// A single class that handles both user creation and user validation
public class UserManager {

    // This method handles creating a user
    public void createUser(String name, String email) {
        // Simulate storing the user in the database
        System.out.println("User created: " + name + ", " + email);
    }

    // This method handles validating the user's email
    public boolean validateEmail(String email) {
        // Basic email validation logic
        return email.contains("@") && email.endsWith(".com");
    }

    // This method sends a welcome email after creating the user
    public void sendWelcomeEmail(String email) {
        // Simulate sending an email
        System.out.println("Welcome email sent to: " + email);
    }
}





// not voilating srp

// Separate class for handling user creation
public class UserCreator {

    public void createUser(String name, String email) {
        // Simulate storing the user in the database
        System.out.println("User created: " + name + ", " + email);
    }
}

// Separate class for handling email validation
public class EmailValidator {

    public boolean validateEmail(String email) {
        // Basic email validation logic
        return email.contains("@") && email.endsWith(".com");
    }
}

// Separate class for sending welcome emails
public class WelcomeEmailSender {

    public void sendWelcomeEmail(String email) {
        // Simulate sending an email
        System.out.println("Welcome email sent to: " + email);
    }
}

// Main class to use the refactored code
public class UserManager {

    private UserCreator userCreator = new UserCreator();
    private EmailValidator emailValidator = new EmailValidator();
    private WelcomeEmailSender welcomeEmailSender = new WelcomeEmailSender();

    public void processUser(String name, String email) {
        // Validate email
        if (emailValidator.validateEmail(email)) {
            // Create user
            userCreator.createUser(name, email);
            // Send welcome email
            welcomeEmailSender.sendWelcomeEmail(email);
        } else {
            System.out.println("Invalid email: " + email);
        }
    }
}

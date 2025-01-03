# Software Architecture Solutions with Code

This repository provides solutions to common software architecture problems with practical code examples. These solutions help manage scalability, complexity, fault tolerance, data consistency, and security challenges in distributed systems, particularly in microservices-based architectures.

---

## Table of Contents
1. [Scalability Issues](#scalability-issues)
2. [Complexity in Microservices Communication](#complexity-in-microservices-communication)
3. [Fault Tolerance and Resilience](#fault-tolerance-and-resilience)
4. [Data Consistency Across Services](#data-consistency-across-services)
5. [Security Challenges](#security-challenges)

---

## Scalability Issues

**Problem:**  
As the system grows, it becomes harder to manage and scale individual components.  

**Examples:**
- Overloaded servers due to sudden traffic spikes (e.g., during a popular show launch).
- Database bottlenecks when handling millions of simultaneous read/write operations.

**Solution:**  
Adopt horizontal scaling, use distributed databases, and implement load balancers.  

### Code Example: Load Balancer using NGINX

Create an NGINX configuration to distribute traffic among multiple backend servers.

```nginx
# nginx.conf
http {
    upstream backend_servers {
        server backend1.example.com;
        server backend2.example.com;
        server backend3.example.com;
    }

    server {
        listen 80;

        location / {
            proxy_pass http://backend_servers;
        }
    }
}

## 2.Microservices Communication: Circuit Breaker with Resilience4j in Java

## Problem: Complexity in Microservices Communication
In a microservices architecture, communication between services can introduce significant complexities. Common issues include:

- Increased latency due to multiple service-to-service calls.
- Cascading failures when one service fails and affects others.

### Solution:
To address these complexities, we can implement the **Circuit Breaker Pattern**, which ensures the system's resilience and prevents cascading failures. This pattern helps by temporarily blocking calls to a failing service and allowing the system to recover gracefully.

In this example, we will use **Resilience4j**, a lightweight library for fault tolerance, to implement a circuit breaker.

---

## Code Example: Circuit Breaker with Resilience4j in Java

```java
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

import java.time.Duration;
import java.util.function.Supplier;

public class CircuitBreakerExample {
    public static void main(String[] args) {
        // Configure the CircuitBreaker with custom settings
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50) // 50% failure rate
                .waitDurationInOpenState(Duration.ofSeconds(10)) // 10 seconds in open state
                .build();

        // Create a CircuitBreaker with the provided configuration
        CircuitBreaker circuitBreaker = CircuitBreaker.of("backendService", config);

        // Decorate the service call with the CircuitBreaker
        Supplier<String> supplier = CircuitBreaker.decorateSupplier(circuitBreaker, CircuitBreakerExample::callService);

        // Simulate 10 service calls
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(supplier.get());
            } catch (Exception e) {
                System.out.println("Request failed: " + e.getMessage());
            }
        }
    }

    // Simulated service call that always fails
    private static String callService() {
        // Simulate failure
        throw new RuntimeException("Service failed");
    }
}

## 3. Fault Tolerance and Resilience

### Problem
Ensuring the system stays operational despite failures in individual components. This challenge arises when a single service failure causes cascading issues across the entire application, or when handling unexpected server outages.

### Solution
To address this challenge, we can:
- **Use Redundancy**: Deploy multiple instances of critical services to ensure availability.
- **Chaos Engineering**: Actively test how services fail to ensure resilience (e.g., using tools like Chaos Monkey).
- **Graceful Degradation**: Implement fallback mechanisms to provide a limited service when the primary service is unavailable.

### Code Example: Graceful Degradation with Fallback

```java
import io.github.resilience4j.fallback.Fallback;

public class FallbackExample {

    public static void main(String[] args) {
        String response = callServiceWithFallback();
        System.out.println(response);
    }

    @Fallback(fallbackMethod = "fallback")
    public static String callServiceWithFallback() {
        // Simulating a failure
        throw new RuntimeException("Service unavailable");
    }

    public static String fallback(Exception e) {
        return "Fallback response: Default data";
    }
}

## 4. Data Consistency Across Services

### **Problem:**
Maintaining data consistency in a distributed system can be challenging, especially when multiple microservices are involved. Different microservices may have outdated or conflicting data, leading to synchronization issues during real-time operations. This can cause system instability and unreliable data across services.

### **Solution:**
To solve these challenges, we use the following approaches:
1. **Event Sourcing**: Store the state of a system as a sequence of events, ensuring that all changes are captured.
2. **CQRS (Command Query Responsibility Segregation)**: Separate the reading and writing of data, allowing for more efficient handling of complex queries and commands.
3. **Message Queues (e.g., Kafka)**: Use a distributed messaging system to ensure communication between services, maintaining eventual consistency and allowing for asynchronous processing.

### **Code Example: Event Sourcing with Kafka**

In this example, we use **Apache Kafka** as a message broker to demonstrate event sourcing. The producer sends an event (e.g., `order_created`) to a Kafka topic, and this event can be consumed by other microservices to update their state.

```java
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class EventProducer {
    public static void main(String[] args) {
        // Setting Kafka properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Kafka server
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Creating KafkaProducer instance
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Defining event details
        String topic = "event_topic"; // Kafka topic
        String key = "order_created"; // Event key
        String value = "OrderID: 12345"; // Event value

        // Sending the event to Kafka topic
        producer.send(new ProducerRecord<>(topic, key, value));
        producer.close();

        // Log message
        System.out.println("Event sent to Kafka topic: " + topic);
    }
}

## 5. Security Challenges

**Problem:** Securing a distributed architecture is complex.

**Examples:**
- Unauthorized access to microservices due to improper authentication.
- Data breaches or man-in-the-middle attacks during service-to-service communication.

### **Solution:**
Implement OAuth, Token-Based Authentication, and Encryption to secure communication and protect sensitive data.

---

### **Code Example: OAuth2 Authentication with Spring Boot**

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
@EnableResourceServer
public class OAuth2Server extends ResourceServerConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2Server.class, args);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/public").permitAll()  // Allow public access
                .anyRequest().authenticated();     // Secure all other requests
    }
}

# OAuth2 Authentication Implementation

## Steps to Implement OAuth2 Authentication:

### 1. Add Spring Security and OAuth dependencies to your project:
- Add the necessary dependencies for Spring Security and OAuth2 to your `pom.xml` (for Maven) or `build.gradle` (for Gradle).

#### Maven (pom.xml)
```xml
<dependencies>
    <!-- Spring Security OAuth2 -->
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-oauth2</artifactId>
        <version>2.4.0</version>
    </dependency>
  
</dependencies>

dependencies {
    // Spring Security OAuth2
    implementation 'org.springframework.security:spring-security-oauth2:2.4.0'
    // Add other dependencies as necessary
}


This structure provides a clear guide on how to implement OAuth2 authentication in a Spring Boot project, with Maven and Gradle configurations included, along with code examples. We can customize it further based on your project's requirements!

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

import java.time.Duration;
import java.util.function.Supplier;

public class CircuitBreakerExample {
    public static void main(String[] args) {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50) // 50% failure rate
                .waitDurationInOpenState(Duration.ofSeconds(10))
                .build();

        CircuitBreaker circuitBreaker = CircuitBreaker.of("backendService", config);

        Supplier<String> supplier = CircuitBreaker.decorateSupplier(circuitBreaker, CircuitBreakerExample::callService);

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(supplier.get());
            } catch (Exception e) {
                System.out.println("Request failed: " + e.getMessage());
            }
        }
    }

    private static String callService() {
        // Simulate failure
        throw new RuntimeException("Service failed");
    }
}

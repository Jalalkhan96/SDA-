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

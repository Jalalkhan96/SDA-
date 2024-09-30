import java.util.concurrent.*;
import java.util.HashMap;
import java.util.Map;

public class ScalableService {
    
    private Map<Integer, Long> cache = new HashMap<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    
    public long calculateFactorial(int n) throws InterruptedException, ExecutionException {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        Future<Long> result = executorService.submit(() -> factorial(n));
        long computedValue = result.get();
        cache.put(n, computedValue);
        
        return computedValue;
    }
    
    private long factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScalableService service = new ScalableService();

        for (int i = 0; i < 20; i++) {
            int number = i;
            new Thread(() -> {
                try {
                    long result = service.calculateFactorial(number);
                    System.out.println("Factorial of " + number + " is: " + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        service.executorService.shutdown();
    }
}

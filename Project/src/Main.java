import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    // Constructor for the Main class
    public Main()  {
        // Create an array to hold 1000 thread objects
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        List<Future<Integer>> futures = new ArrayList<>();

        // Record the start time for performance measurement
        long startTime = System.currentTimeMillis();


        // Loop to initialize and start 1000 threads
        for (int x = 0; x < 1000; x++)
        {
            futures.add(executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int x = 1;x <= 1000000; x++)
                    {
                        sum+=1;
                    }
                    return sum;
                }
            }));
        }

        int sum = 0;
        for (Future<Integer> f : futures)
        {
            try {
                sum += f.get();
            }
            catch (Exception e)
            {
                throw new RuntimeException();
            }
        }

        executorService.shutdown();

        System.out.println(sum);

        // Print the time taken to execute the threads
        System.out.println((System.currentTimeMillis() - startTime) + " ms");
    }

    // Main method to run the application
    public static void main(String[] args)
    {
        // Create an instance of Main, which starts the thread execution
        Main app = new Main();
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public Main()  {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        List<Future<Integer>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();

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
                System.out.println(e);
            }
        }

        executorService.shutdown();

        System.out.println(sum);

        System.out.println((System.currentTimeMillis() - startTime) + " ms");
    }

    public static void main(String[] args)
    {
        Main app = new Main();
    }
}

import java.util.ArrayList;

class Scratch
{
    public static void main(String[] args)
    {
        ArrayList<Integer> times = new ArrayList<Integer>();
        int target = 1000;
    
        long start = 0;
        long stop = 0;
        int x = 1;
        int a = 0;
        int b = 0;
        int c = 0;
        int mod = 0;
        int sum = 0;
    
        for (int i = 0; i < 1000; i++)
        {
            x = 1;
            start = System.nanoTime();
    
            while (true)
            {
                x++;
                a = (2 * x);
                b = (x * x - 1);
                c = (x * x + 1);
                sum = a + b + c;
    
                if (target % sum == 0)
                {
                    mod = target / sum;
                    a = a * mod;
                    b = b * mod;
                    c = c * mod;
                    break;
                }
            }
    
            stop = System.nanoTime();
            times.add(Integer.parseInt(Long.toString(stop - start)));
            try
            {
                Thread.sleep(25);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    
        System.out.printf("a: %d  b: %d  c: %d  sum: %d  answer: %d\n", a, b, c, (a + b + c), (a * b * c));
        sum = 0;
        for (Integer e : times)
            sum += e;
        System.out.println(sum/times.size());
    }
    
}

class Scratch
{
    public static void main(String[] args)
    {
        long start = System.nanoTime();
        
        int target = 1000;
        
        int x = 1;
        int a = 0;
        int b = 0;
        int c = 0;
        int mod = 0;
        int sum = 0;
        
        while (true)
        {
            x++;
            a = (2*x);
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
        
        System.out.println("Time in ns: " + (System.nanoTime() - start));
    
        System.out.printf("a: %d  b: %d  c: %d  sum: %d  answer: %d\n", a, b, c, (a + b + c), (a * b * c));
    }
    
}

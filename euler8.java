class Scratch
{
    public static void main(String[] args)
    {
        String largeNum = "7316717653133062491922511967442657474235534919493"  +
                "496983520312774506326239578318016984801869478851843858615607" +
                "891129494954595017379583319528532088055111254069874715852386" +
                "305071569329096329522744304355766896648950445244523161731856" +
                "403098711121722383113622298934233803081353362766142828064444" +
                "866452387493035890729629049156044077239071381051585930796086" +
                "670172427121883998797908792274921901699720888093776657273330" +
                "010533678812202354218097512545405947522435258490771167055601" +
                "360483958644670632441572215539753697817977846174064955149290" +
                "862569321978468622482839722413756570560574902614079729686524" +
                "145351004748216637048440319989000889524345065854122758866688" +
                "116427171479924442928230863465674813919123162824586178664583" +
                "591245665294765456828489128831426076900422421902267105562632" +
                "111110937054421750694165896040807198403850962455444362981230" +
                "987879927244284909188845801561660979191338754992005240636899" +
                "125607176060588611646710940507754100225698315520005593572972" +
                "571636269561882670428252483600823257530420752963450";
        
        long start = System.nanoTime();
        System.out.println(do_products(largeNum));
        System.out.println("Time in ns: " + (System.nanoTime() - start));
        start = System.nanoTime();
        System.out.println(one_loop(largeNum));
        System.out.println("Time in ns: " + (System.nanoTime() - start));
        start = System.nanoTime();
        System.out.println(one_touch(largeNum));
        System.out.println("Time in ns: " + (System.nanoTime() - start));
    }
    
    public static long do_products(String largeNum)
    {
        char[] largeArr = largeNum.toCharArray();
        char[] temp = new char[13];
        long largest = 0;
        
        for (int i = 0; i < largeArr.length - 12; i++)
        {
            System.arraycopy(largeArr, i, temp, 0, 13);
            long curr = get_product(temp);
            if (curr > largest)
                largest = curr;
        }
        
        return largest;
    }
    
    public static long get_product(char[] nums)
    {
        long product = 1;
        for (char e : nums)
            product = product * (e - '0');
        
        return product;
    }
    
    public static long one_loop(String largeNum)
    {
        char[] largeArr = largeNum.toCharArray();
        char[] temp = new char[13];
        System.arraycopy(largeArr, 0, temp, 0, 13);
        long largest = 0;
        long curr = get_product(temp);
    
        for (int i = 1; i < largeArr.length - 12; i++)
        {
            if (largeArr[i - 1] != '0')
            {
                curr = curr / (largeArr[i - 1] - '0') * (largeArr[i + 12] - '0');
            } else {
                System.arraycopy(largeArr, i, temp, 0, 13);
                curr = get_product(temp);
            }
            
            if (curr > largest)
                largest = curr;
        }
        
        return largest;
    }
    
    public static long one_touch(String largeNum)
    {
        char[] largeArr = largeNum.toCharArray();
        char[] temp = new char[13];
        System.arraycopy(largeArr, 0, temp, 0, 13);
        long largest = 0;
        long curr = get_product(temp);
        
        for (int i = 1; i < largeArr.length - 12; i++)
        {
            int prev = largeArr[i -  1] - '0';
            int next = largeArr[i + 12] - '0';
            if (next == 0)
            {
                i += 13;
                curr = 1;
                for(int j = i; j < i + 13; j++)
                {
                    if (largeArr[j] == '0')
                    {
                        i = j + 1;
                        j = i;
                        curr = 1;
                    }
                    if (i > largeArr.length - 12)
                    {
                        curr = 1;
                        break;
                    }
                    curr = curr * (largeArr[j] - '0');
                }
                
            } else {
                curr = curr / prev * next;
            }
            
            if (curr > largest)
                largest = curr;
        }
        
        return largest;
    }
    
}

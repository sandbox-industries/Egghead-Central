import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Scratch
{
    private static int hold_data = 0;
    
    public static void main(String[] args)
    {
        long start = System.nanoTime();
        
        int[][] data = new int[9][9];
        
        File data_file = new File("C:\\Users\\clint\\Downloads\\p096_sudoku.txt");
        Scanner fs = null;
        char values[];
        int lineCount = 0;
        
        try
        {
            fs = new Scanner(data_file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found exception for file " + e);
            System.exit(1);
        }
    
        while (fs.hasNextLine())
        {
            if (lineCount % 10 == 0)
            {
                lineCount++;
                fs.nextLine();
            }
            else
            {
                for (int i = 0; i < 9; i++)
                {
                    lineCount++;
                    values = fs.nextLine().toCharArray();
                    for (int j = 0; j < 9; j++)
                        data[i][j] = (int)values[j] - (int)'0';
                }
                solve(data);
                data = new int[9][9];
            }
        }
        
        long end = System.nanoTime();
        System.out.printf("\nCompute Duration (s): %.4f", (end - start) / 1000000000.0);
        
        System.out.println("\n");
        System.out.println(hold_data);
    }
    
    public static void solve(int[][] data)
    {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (data[i][j] == 0)
                {
                    for (int k = 1; k < 10; k++)
                        if (viable(i, j, k, data))
                        {
                            data[i][j] = k;
                            solve(data);
                            data[i][j] = 0;
                        }
                    return;
                }
        int temp = 100 * data[0][0] + 10 * data[0][1] + data[0][2];
        hold_data = hold_data + temp;
    }
    
    private static boolean viable(int x, int y, int n, int[][] data)
    {
        // row
        for (int e : data[x])
            if (e == n)
                return false;

        // column
        for (int[] e : data)
            if (e[y] == n)
                return false;
        
        // box
        int x0 = x / 3;
        x0 = x0 * 3;
        int x1 = x0 + 3;
        int y0 = y / 3;
        y0 = y0 * 3;
        int y1 = y0 + 3;
        for (int i = x0; i < x1; i++)
            for (int j = y0; j < y1; j++)
                if (n == data[i][j])
                    return false;
        
        return true;
    }
    
}

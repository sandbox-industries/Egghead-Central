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
            // skip over the title lines...
            if (lineCount % 10 == 0)
            {
                lineCount++;
                fs.nextLine();
            }
            else
            {
                // each row in the sudoku
                for (int i = 0; i < 9; i++)
                {
                    lineCount++;
                    values = fs.nextLine().toCharArray();
                    // each element in the row
                    // make sure to convert the characters into integer values
                    for (int j = 0; j < 9; j++)
                        data[i][j] = (int)values[j] - (int)'0';
                }
                // call the solver function on the constructed sudoku data array
                solve(data);
                // clear the array to avoid possible data bugs
                data = new int[9][9];
            }
        }
        
        long end = System.nanoTime();
        System.out.printf("\nCompute Duration(s): %.4f", (end - start) / 1000000000.0);
        
        System.out.println("\n");
        System.out.println(hold_data);
    }
    
    /**
     * Recursively solve the sudoku. When a non-solution is found, return to the calling
     * method and try a different value
     *
     * @param data reference to the sudoku data array
     */
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
    
    /**
     * Determine if the given value will fit in the space according to sudoku rules
     *
     * @param x column number
     * @param y row number
     * @param n value to check
     * @param data reference to sudoku data array
     * @return
     */
    private static boolean viable(int x, int y, int n, int[][] data)
    {
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
       
        // column
        for (int[] e : data)
            if (e[y] == n)
                return false;
                
        // row
        for (int e : data[x])
            if (e == n)
                return false;
            
        return true;
    }
    
}

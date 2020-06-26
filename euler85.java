package ProjectEuler67;

public class euler85
{
    public static void main(String args[])
    {
        int row = 10;
        int col = 9;
        int count = 0;
    
        int target = 2000000;
        
        boolean flag = true;
    
        do
        {
            col++;
            if (col > 100)
            {
                col = 10;
                row++;
            }
            
            if (row > 100)
                flag = false;
            
            count = 0;
            
            for (int i = 1; i <= row; i++)
            {
                int r = 0;
                for (int ii = 1; ii <= row; ii++)
                    if (ii + i <= row + 1)
                        r++;
                for (int j = 1; j <= col; j++)
                {
                    int c = 0;
                    for (int jj = 1; jj <= col; jj++)
                        if (jj + j <= col + 1)
                            c++;
                    count = count + r*c;
                }
            }
            
            if (target - count == 2)
                flag = false;
            
        } while(flag);
    
        System.out.printf("count: %d\nrow: %d\ncol: %d\narea: %d\n", count, row, col, row*col);
    }
}

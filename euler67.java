package ProjectEuler67;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class euler67
{
    private static String path = "C:\\Users\\clint\\OneDrive\\Documents\\IDEAProjects\\CS310Egnor\\src\\ProjectEuler67\\euler67.txt";
    private static File data_file = new File(path);
    
    private static elementNode head = null;
    private static elementNode prevNodes[] = null;
    
    /**
     * The Main function.... kinda self explanatory.
     *
     * @param args arguments from the command line
     */
    public static void main(String args[])
    {
        long start = System.currentTimeMillis();
        
        processFile();
    
        for (elementNode e : prevNodes)
        {
            e.setNodeSum(e.getValue());
            findPathSum(e);
        }
        
        long end = System.currentTimeMillis();
        
        System.out.println("Duration in ms: " + (end - start));
        System.out.println(head);
    }
    
    /**
     * Opens and processes the data file
     */
    private static void processFile()
    {
        int line = 0;
        Scanner fs = null;
        String lineValues[];
        
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
            lineValues = fs.nextLine().split(" ");
            buildTree(lineValues, line);
            line++;
        }
    }
    
    /**
     * Builds a binary tree from the individual text file lines
     *
     * @param lineValues the values of the current line
     * @param line the current line of the file
     */
    private static void buildTree(String lineValues[], int line)
    {
        elementNode[] currNodes = new elementNode[lineValues.length];
    
        for (int i = 0; i < lineValues.length; i++)
        {
            elementNode curr = null;
            int v = Integer.parseInt(lineValues[i]);
            
            if (prevNodes == null)
            {
                curr = new elementNode(line, i, v, null, null);
                currNodes[i] = curr;
                head = curr;
            }
            else
            {
                if (i == 0)
                {
                    curr = new elementNode(line, i, v, null, prevNodes[i]);
                    prevNodes[i].setLeft(curr);
                }
                else if (i == lineValues.length - 1)
                {
                    curr = new elementNode(line, i, v, prevNodes[i - 1], null);
                    prevNodes[i - 1].setRight(curr);
                }
                else
                {
                    curr = new elementNode(line, i, v, prevNodes[i - 1], prevNodes[i]);
                    prevNodes[i].setLeft(curr);
                    prevNodes[i - 1].setRight(curr);
                }
            
                currNodes[i] = curr;
            }
        }
        prevNodes = currNodes;
    }
    
    /**
     * Recursively builds the cumulative sum on a per element basis. This will not continue if the
     * cumulative sum is less than the value already calculated for the node. This will skip over
     * the paths which are not valid and less than another which has already "passed" through.
     *
     * @param step elementNode, the current node the function is processing
     */
    private static void findPathSum(elementNode step)
    {
        int left = 0;
        int right = 0;

        if (step.getParentLeft() != null)
        {
            left = step.getParentLeft().getValue() + step.getNodeSum();
            if (left > step.getParentLeft().getNodeSum())
            {
                step.getParentLeft().setNodeSum(left);
                findPathSum(step.getParentLeft());
            }
        }

        if (step.getParentRight() != null)
        {
            right = step.getParentRight().getValue() + step.getNodeSum();
            if (right > step.getParentRight().getNodeSum())
            {
                step.getParentRight().setNodeSum(right);
                findPathSum(step.getParentRight());
            }
        }
    }
    
}

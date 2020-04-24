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
        // log the program run time start
        long start = System.currentTimeMillis();
        
        // handle opening the file and building the binary tree
        processFile();
    
        // build the path sum for each leaf of the binary tree. 
        // The algorithm starts at the bottom and works towards the top
        for (elementNode e : prevNodes)
        {
            // set the current path sum at the node to the value of the node
            // this is only done for the leaves in the binary tree
            e.setNodeSum(e.getValue());
            // calculate the path sums for the given leaf
            findPathSum(e);
        }
        
        // log the program run time end
        long end = System.currentTimeMillis();
        
        // calculate and log the duration of the program run
        System.out.println("Duration in ms: " + (end - start));
        // print the results
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
        
        // open the file
        try
        {
            fs = new Scanner(data_file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found exception for file " + e);
            System.exit(1);
        }
        
        // read the file while there are lines left in the file
        while (fs.hasNextLine())
        {
            // split the line into an array of strings with the space as the delimiter
            lineValues = fs.nextLine().split(" ");
            // build the row in the data structure
            buildTree(lineValues, line);
            // iterate the line counter, used for tracking the grid location of the element
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
            
            // this is the first node
            if (prevNodes == null)
            {
                curr = new elementNode(line, i, v, null, null);
                currNodes[i] = curr;
                head = curr;
            }
            else
            {
                // there is no left parent
                if (i == 0)
                {
                    curr = new elementNode(line, i, v, null, prevNodes[i]);
                    prevNodes[i].setLeft(curr);
                }
                // there is no right parent
                else if (i == lineValues.length - 1)
                {
                    curr = new elementNode(line, i, v, prevNodes[i - 1], null);
                    prevNodes[i - 1].setRight(curr);
                }
                // there is both a left and right parent
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
     * Recursively builds the cumulative sum on a per element basis from the bottom to the top. This 
     * will not continue if the cumulative sum is less than the value already calculated for the 
     * node. This will skip over the paths which are not valid and less than another which has 
     * already "passed" through.
     *
     * @param step elementNode, the current node the function is processing
     */
    private static void findPathSum(elementNode step)
    {
        // initialize left and right path sum values
        int left = 0;
        int right = 0;

        // do not continue left if there is nothing to the left
        if (step.getParentLeft() != null)
        {
            // calculate the path sum for the left parent
            left = step.getParentLeft().getValue() + step.getNodeSum();
            // if the path sum is less than the already set value, do not continue recursion
            if (left > step.getParentLeft().getNodeSum())
            {
                // set the path sum for the node
                step.getParentLeft().setNodeSum(left);
                // continue calculating the path sum starting from the left parent
                findPathSum(step.getParentLeft());
            }
        }

        // do not continue to the right if there is nothing to the right
        if (step.getParentRight() != null)
        {
            // calculate the path sum for the right parent
            right = step.getParentRight().getValue() + step.getNodeSum();
            // if the path sum is less than the already set value, do not continue recursion
            if (right > step.getParentRight().getNodeSum())
            {
                // set the path sum for the node
                step.getParentRight().setNodeSum(right);
                // continue calculating the path sum starting from the right parent
                findPathSum(step.getParentRight());
            }
        }
    }
    
}

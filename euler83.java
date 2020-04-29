
public class euler83
{
    private static String path = "C:\\Users\\clint\\OneDrive\\Documents\\IDEAProjects\\CS310Egnor\\src\\ProjectEuler67\\euler83.txt";
    private static File data_file = new File(path);
    
    private static mapNode head = null;
    private static mapNode tail = null;
    private static mapNode prevNodes[] = null;
    
    public static void main(String args[])
    {
        // log the start time
        long start = System.currentTimeMillis();
        
        // read the data file and build the data map
        processFile();
        // define reference to the end point we are searching for and where we will get the final distance from
        tail = prevNodes[prevNodes.length - 1];
        // start the recursive path finding algorithm
        findPath(head);
        
        // log the end time
        long end = System.currentTimeMillis();
    
        // calculate and print program duration
        System.out.println("Duration in ms: " + (end - start));
        // print the results
        System.out.println(tail);
    }
    
    /**
     * Processes the data file line-by-line and passes the line data to a tree builder
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
            lineValues = fs.nextLine().split(",");
            // build the row in the data structure
            buildMap(lineValues, line);
            // iterate the line counter, used for tracking the grid location of the element
            line++;
        }
    }
    
    /**
     * Builds the map data structure from the lines passed in by the file processor
     *
     * @param lineValues String[], the values of the line split along the commas
     * @param line int, the line number that the program is on
     */
    private static void buildMap(String lineValues[], int line)
    {
        mapNode currNodes[] = new mapNode[lineValues.length];
        
        for (int i = 0; i < lineValues.length; i++)
        {
            int v = Integer.parseInt(lineValues[i]);
            mapNode curr = new mapNode(line, i, v);
            
            // first line
            if (prevNodes == null)
            {
                // first element
                if (currNodes[0] == null)
                {
                    curr.setPathSum(curr.getValue());
                    head = curr;
                }
                // all others on the first line
                else
                {
                    curr.setLeft(currNodes[i - 1]);
                    currNodes[i - 1].setRight(curr);
                }
            }
            // not the first line
            else
            {
                curr.setUp(prevNodes[i]);
                prevNodes[i].setDown(curr);
                // excludes the first element in a row, which doesnt have an element to the left
                if (i > 0)
                {
                    // set the left reference
                    curr.setLeft(currNodes[i - 1]);
                    currNodes[i - 1].setRight(curr);
                }
            }
            // add the current element to a reference of the elements on the current line
            currNodes[i] = curr;
        }
        // set the reference to the elements of the current line to the previous line and return to the file handler
        prevNodes = currNodes;
    }
    
    /**
     * Will recursively find the path with the lowest total value from the initial passed
     * in value to the very last value in the map
     *
     * @param step mapNode, the current element from which the algorithm is looking forward
     */
    private static void findPath(mapNode step)
    {
        // we have reached root. Return so recursion can continue.
        if (step.getColumn() == 79 && step.getRow() == 79)
            return;
            
        // container for all of the options, will automatically resize for us since we dont know
        // how many directions are viable
        ArrayList<mapNode> opts = new ArrayList<mapNode>();
        
        // containers for the different direction options
        // we do not know where we came from, so we include all the directions.
        mapNode up = step.getUp();
        mapNode right = step.getRight();
        mapNode down = step.getDown();
        mapNode left = step.getLeft();
        
        // only add the up direction if it exists and we have not gone there before
        if (up != null && up.getPathSum() > step.getPathSum() + up.getValue())
            opts.add(step.getUp());
        
        // only add the right direction if it exists and we have not gone there before
        if (right != null && right.getPathSum() > step.getPathSum() + right.getValue())
            opts.add(step.getRight());
        
        // only add the down direction if it exists and we have not gone there before
        if (down != null && down.getPathSum() > step.getPathSum() + down.getValue())
            opts.add(step.getDown());
        
        // only add the left direction if it exists and we have not gone there before
        if (left != null && left.getPathSum() > step.getPathSum() + left.getValue())
            opts.add(step.getLeft());
        
        // define array container, convert the arraylist into array of the container type, then sort the array
        mapNode opt[] = new mapNode[opts.size()];
        opt = sortList(opts.toArray(opt));
        
        // the list is sorted by the lowest path value, recursively run through the options, lowest first
        for (mapNode e : opt)
        {
            // set the next path sum
            e.setPathSum(e.getValue() + step.getPathSum());
            // recursively call the next option
            findPath(e);
        }
    }
    
    /**
     * Insertion sort the list of possible paths based on the path sum and the distance from the end of the maze
     *
     * @param dists mapNode[], array of possible options to sort
     * @return mapNode[], the sorted array of possible options
     */
    private static mapNode[] sortList(mapNode[] dists)
    {
        for (int idx = 1; idx < dists.length; idx++)
        {
            int foo = idx;
            // move the value backwards through the list until the correct place
            //  is found for the current variable. Swap values at each step.
            while (foo > 0 && dists[foo].getDistance() + dists[foo].getValue() < dists[foo - 1].getDistance() + dists[foo - 1].getValue())
            {
                mapNode hold = dists[foo - 1];
                dists[foo - 1] = dists[foo];
                dists[foo] = hold;
                foo--;
            }
        }
        return dists;
    }
}

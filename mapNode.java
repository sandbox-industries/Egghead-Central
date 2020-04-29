
public class mapNode
{
    // node location in tree
    private int row;
    private int column;
    
    // node value
    private int value;
    private int pathSum;
    private double distance;
    
    // pointers
    mapNode left;
    mapNode right;
    mapNode up;
    mapNode down;
    
    
    
    public mapNode()
    {
        this.row = -1;
        this.column = -1;
        this.value = -1;
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = null;
        
        this.pathSum = Integer.MAX_VALUE;
    }
    
    public mapNode(int row, int column, int value)
    {
        // natives
        this.row = row;
        this.column = column;
        this.value = value;
        
        this.distance = calcDistance();
        
        // pointers
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = null;
    
        this.pathSum = Integer.MAX_VALUE;
    }
    
    // setters
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public void setPathSum(int val)
    {
        this.pathSum = val;
    }
    
    public void setLeft(mapNode left)
    {
        this.left = left;
    }
    
    public void setRight(mapNode right)
    {
        this.right = right;
    }
    
    public void setUp(mapNode up)
    {
        this.up = up;
    }
    
    public void setDown(mapNode down)
    {
        this.down = down;
    }
    
    public void setColumn(int column_index)
    {
        this.column = column_index;
    }
    
    public void setRow(int row_index)
    {
        this.row = row_index;
    }
    
    // getters
    
    public int getValue()
    {
        return value;
    }
    
    public int getPathSum()
    {
        return pathSum;
    }
    
    public mapNode getLeft()
    {
        return left;
    }
    
    public mapNode getRight()
    {
        return right;
    }
    
    public mapNode getUp()
    {
        return up;
    }
    
    public mapNode getDown()
    {
        return down;
    }
    
    public int getColumn()
    {
        return column;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public double getDistance()
    {
        return distance;
    }
    
    public double calcDistance()
    {
        return Math.sqrt(Math.pow(Math.abs(79 - row), 2) + Math.pow(Math.abs(79 - column), 2));
    }
    
    public String toString()
    {
       return "Node Value: " + this.value + "\nCumulative Sum: " + this.pathSum;
    }
}

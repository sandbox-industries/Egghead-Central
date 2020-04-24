package ProjectEuler67;

public class elementNode
{
    // node location in tree
    private int row;
    private int column;
    
    // node value
    private int value;
    private int nodeSum;
    
    // pointers
    private elementNode left;
    private elementNode right;
    private elementNode parent_left;
    private elementNode parent_right;
    
    
    public elementNode()
    {
        this.row = -1;
        this.column = -1;
        this.value = -1;
        this.left = null;
        this.right = null;
        this.parent_left = null;
        this.parent_right = null;
    }
    
    public elementNode(int row, int column, int value, elementNode parent_left, elementNode parent_right)
    {
        // natives
        this.row = row;
        this.column = column;
        this.value = value;
        
        // objects
        this.left = null;
        this.right = null;
        this.parent_left = parent_left;
        this.parent_right = parent_right;
    }
    
    // setters
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public void setNodeSum(int val)
    {
        this.nodeSum = val;
    }
    
    public void setLeft(elementNode left)
    {
        this.left = left;
    }
    
    public void setRight(elementNode right)
    {
        this.right = right;
    }
    
    public void setParentLeft(elementNode parent)
    {
        this.parent_left = parent;
    }
    
    public void setParentRight(elementNode parent)
    {
        this.parent_right = parent;
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
    
    public int getNodeSum()
    {
        return nodeSum;
    }
    
    public elementNode getLeft()
    {
        return left;
    }
    
    public elementNode getRight()
    {
        return right;
    }
    
    public elementNode getParentLeft()
    {
        return parent_left;
    }
    
    public elementNode getParentRight()
    {
        return parent_right;
    }
    
    public int getColumn()
    {
        return column;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public String toString()
    {
       return "Node Value: " + this.value + "\nCumulative Sum: " + this.nodeSum + "\n";
    }
}

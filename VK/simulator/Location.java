package VK.simulator;
/**
 * Represent a location in a rectangular grid.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Location
{
    // Row and column positions.
    private int row;
    private int col;

    /**
     * Represent a row and column.
     * @param row1 The row.
     * @param col1 The column.
     */
    public Location(int row1, int col1)
    {
        this.row = row1;
        this.col = col1;
    }
    
    /**
     * Implement content equality.
     */
    @Override
	public boolean equals(Object obj)
    {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return this.row == other.getRow() && this.col == other.getCol();
        }
		return false;
    }
    
    /**
     * Return a string of the form row,column
     * @return A string representation of the location.
     */
    @Override
	public String toString()
    {
        return this.row + "," + this.col;
    }
    
    /**
     * Use the top 16 bits for the row value and the bottom for
     * the column. Except for very big grids, this should give a
     * unique hash code for each (row, col) pair.
     * @return A hashcode for the location.
     */
    @Override
	public int hashCode()
    {
        return (this.row << 16) + this.col;
    }
    
    /**
     * @return The row.
     */
    public int getRow()
    {
        return this.row;
    }
    
    /**
     * @return The column.
     */
    public int getCol()
    {
        return this.col;
    }
}


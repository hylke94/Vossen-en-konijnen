package VK.simulator;
/**
 * Provide a counter for a participant in the simulation.
 * This includes an identifying string and a count of how
 * many participants of this type currently exist within 
 * the simulation.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Counter
{
    // A name for this type of simulation participant
    private String name;
    // How many of this type exist in the simulation.
    private int count;

    /**
     * Provide a name for one of the simulation types.
     * @param name1  A name, e.g. "Fox".
     */
    public Counter(String name1)
    {
        this.name = name1;
        this.count = 0;
    }
    
    /**
     * @return The short description of this type.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @return The current count for this type.
     */
    public int getCount()
    {
        return this.count;
    }

    /**
     * Increment the current count by one.
     */
    public void increment()
    {
        this.count++;
    }
    
    /**
     * Reset the current count to zero.
     */
    public void reset()
    {
        this.count = 0;
    }
}


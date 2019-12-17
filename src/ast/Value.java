package ast;

/**
 * A class that represents a data value in the SIMPLE
 * language. The data stored by an object of this class
 * can either be a Boolean or an Integer. This class
 * relies on client classes to call the correct retrieval
 * method; otherwise, a RuntimeException is thrown. If intVal
 * == null, the Value stores a boolean value, and vice versa.
 *
 * @author Daniel Wu
 * @version 12/16/2019
 */
public class Value
{
    private Integer intVal;
    private Boolean boolVal;

    /**
     * Constructs an Integer Value object that
     * stores the integer i. Sets the boolVal to
     * null.
     *
     * @param i the int value being stored by Value object
     */
    public Value(int i)
    {
        intVal = i;
        boolVal = null;
    }

    /**
     * Constructs a Boolean Value object that
     * stores the boolean b. Sets the intVal
     * to null.
     *
     * @param b the boolean value being stored by Value object
     */
    public Value(boolean b)
    {
        boolVal = b;
        intVal = null;
    }

    /**
     * Returns what type of value the Value stores
     * either by returning "b" or "i", depending on which
     * value type is stored.
     *
     * @return "b" if the Value object stores
     * a boolean, "i" if the object stores
     * an int
     */
    public String whichVal()
    {
        if (boolVal == null)
            return "i";
        else
            return "b";
    }

    /**
     * Returns the boolean stored by the Value object.
     *
     * @precondition client has called the whichVal()
     *               method and determined that the Value
     *               object stores an boolean
     * @return the boolean value stored by the Value object
     * @throws RuntimeException if the object stores an int type
     */
    public boolean getBoolVal()
    {
        if (boolVal == null)
            throw new RuntimeException("Value is an int type.");
        return boolVal;
    }

    /**
     * Returns the integer stored by the Value object
     *
     * @precondition client has called the whichVal()
     *               method and determined that the Value
     *               object stores an int
     *
     * @return the int value stored by the Value object.
     * @throws RuntimeException if the object stores a boolean type
     */
    public int getIntVal()
    {
        if (intVal == null)
            throw new RuntimeException("Value is a boolean type.");
        return intVal;
    }
}

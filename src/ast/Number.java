package ast;

import environment.Environment;

/**
 * A class that represents a Number. Stores an int value.
 * Extends the abstract Expression class.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 * @version 12/16/2019
 */
public class Number extends Expression
{
    private int value;

    /**
     * Constructs a Number object with an int value.
     *
     * @param num the int value stored by the Number class
     */
    public Number(int num)
    {
        value = num;
    }

    /**
     * A toString method that will print the Number's value as a String.
     * Overrides the Object toString() method.
     *
     * @return the value of the instance field value as a String
     */
    public String toString()
    {
        return Integer.toString(value);
    }

    /**
     * Evaluates itself by boxing itself in a Value class.
     * Returns this new Value object storing the Integer value
     * of the instance variable value.
     *
     * @param e the Environment related to the current method
     *          that contains the relevant symbol table for
     *          variables.
     * @return a Value containing the Integer value stored
     *         by this class.
     */
    public Value eval(Environment e)
    {
        return new Value(value);
    }
}

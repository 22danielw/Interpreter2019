package ast;

import environment.Environment;

/**
 * A class that represents a Number. Stores an int value.
 *
 * @author Daniel Wu
 * @version 10/19/2019
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
     * A getter for the int value stored by the Number class.
     *
     * @return the instance field value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * A toString method that will print the Number's value as a String.
     *
     * @return the value of the instance field value as a String
     */
    public String toString()
    {
        return Integer.toString(value);
    }

    public Value eval(Environment e)
    {
        return new Value(value);
    }
}

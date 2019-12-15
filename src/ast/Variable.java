package ast;

/**
 * A class that represents a Variable and
 * stores its name.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 */
public class Variable extends Expression
{
    private String name;

    /**
     * Constructs a Variable object with a given name.
     *
     * @param n the name of the Variable object
     */
    public Variable(String n)
    {
        name = n;
    }

    /**
     * A getter for the name stored by the Variable
     *
     * @return the instance field name
     */
    public String getName()
    {
        return name;
    }
}

package ast;

import environment.Environment;

/**
 * A class that represents a Variable and
 * stores its name. Relies on the Environment to
 * store its value.
 * Extends the abstract Expression class.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 * @version 12/16/2019
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

    /**
     * Evaluates a Variable object by returning the Value
     * corresponding with its name stored in the Environment
     * provided.
     *
     * @param e the Environment related to the current method
     *          that contains the relevant symbol table for
     *          variables.
     * @return a Value representing the value stored by the Variable.
     */
    public Value eval(Environment e)
    {
        return e.getVariable(name);
    }
}

package ast;

/**
 * A class that creates an Object representing
 * a read statement from the user. This object
 * stores a variable name which represents the
 * name of the variable to which the user's input
 * should be stored in.
 *
 * @author Daniel Wu
 * @version 12/16/2019
 */
public class Read extends Statement
{
    Variable id;

    /**
     * Constructs a Read object with a Variable.
     * and stores it into an instance field.
     *
     * @param v Variable to be stored
     */
    public Read(Variable v)
    {
        id = v;
    }

    /**
     * A getter for the Variable stored by the Read
     * class.
     *
     * @return the instance field id
     */
    public Variable getVariable()
    {
        return id;
    }
}


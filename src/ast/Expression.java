package ast;

import environment.Environment;

/**
 * An abstract class representing an Expression.
 * Contains one method for the sake of polymorphism, and
 * has many subclasses that override the method eval()
 * based on what the specific Expression subclass does to
 * evaluate itself.
 *
 *
 * @author Daniel Wu
 * @version 10/20/19
 * @version 12/16/2019
 */
public abstract class Expression
{
    /**
     * Provides a method to override to allow polymorphism
     * in the program (allowing general Expression objects
     * to be instantiated/returned). All subclasses of this
     * class override this method.
     *
     * @param e the Environment related to the current method
     *          that contains the relevant symbol table for
     *          variables.
     * @return a Value (either a boolean or an integer) that
     *         represents the result of the evaluation of the
     *         Expression subclass' object.
     */
    public Value eval(Environment e)
    {
        throw new RuntimeException("Implement me");
    }
}

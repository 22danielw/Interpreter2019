package ast;

import environment.Environment;

/**
 * An abstract class representing an Expression.
 *
 * @author Daniel Wu
 * @version 10/20/19
 */
public abstract class Expression
{

    /**
     * Evaluates the subclass' method in
     * @param e
     * @return
     */
    public Value eval(Environment e)
    {
        throw new RuntimeException("Implement me");
    }
}

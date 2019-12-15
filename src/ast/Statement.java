package ast;

import environment.Environment;

/**
 * An abstract class representing an Statement.
 *
 * @author Daniel Wu
 * @version 10/20/19
 */
public abstract class Statement
{

    public void eval(Environment e)
    {
        throw new RuntimeException("Implement me");
    }
}

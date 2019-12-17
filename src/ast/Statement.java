package ast;

import environment.Environment;

/**
 * An abstract class representing an Statement.
 * Contains one method for the sake of polymorphism, and
 * has many subclasses that override the method exec()
 * based on what the specific Statement subclass does to
 * execute itself.
 *
 * @author Daniel Wu
 * @version 12/16/2019
 */
public abstract class Statement
{

    /**
     * Provides a method to override to allow polymorphism
     * in the program (allowing general Statement objects to be
     * instantiated/returned). All subclasses of Statement override
     * this method.
     *
     * @param e the Environment related to the current method
     *          that contains the relevant symbol table for
     *          variables.
     */
    public void exec(Environment e)
    {
        throw new RuntimeException("Implement me");
    }
}

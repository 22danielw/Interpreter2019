package ast;

/**
 * A class that represents a While statement with an Expression
 * and a Statement.
 * Extends the abstract Statement class.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 */
public class While extends Statement
{

    private Expression expression;
    private Program program;

    /**
     * Constructor for the While class. Assigns an expression and statement
     * ot the object.
     *
     * @param e the Expression stored by the While object
     * @param p the Program stored by the While object
     */
    public While(Expression e, Program p)
    {
        expression = e;
        program = p;
    }

    /**
     * A getter for the Expression stored by the While object.
     *
     * @return the instance field condition
     */
    public Expression getExpression()
    {
        return expression;
    }

    /**
     * A getter for the Statement stored by the While object.
     *
     * @return the instance field statement
     */
    public Program getProgram()
    {
        return program;
    }
}

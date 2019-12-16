package ast;

import environment.Environment;

/**
 * A class that represents an If statement with an Expression
 * and a Statement.
 * Extends the abstract Statement class.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 */
public class If extends Statement
{
    private Expression expression;
    private Program program1;
    private Program program2;

    /**
     * Constructor for the If class. Assigns a expression and statement
     * ot the object.
     *
     * @param e the Expression stored by the If object
     * @param p1 the Program executed if the condition is true
     * @param p2 the Program within the else clause
     */
    public If(Expression e, Program p1, Program p2)
    {
        expression = e;
        program1 = p1;
        program2 = p2;
    }

    /**
     * A getter for the Expression stored by the If object.
     *
     * @return the instance field condition
     */
    public Expression getExpression()
    {
        return expression;
    }

    /**
     * A getter for the Statement stored by the If object.
     *
     * @return the instance field statement
     */
    public Program getProgram1()
    {
        return program1;
    }

    public Program getProgram2()
    {
        return program2;
    }

    /**
     * @precondition the Expression stored by the If class
     *               represents a boolean value.
     * @param e
     */
    public void exec(Environment e)
    {
        boolean cond;
        if (expression.eval(e).whichVal().equals("i"))
            throw new RuntimeException("Expected Relational Operation, Binary Op found.");
        else
            cond = expression.eval(e).getBoolVal();
        if (cond)
            program1.exec(e);
        else if (program2 != null)
            program2.exec(e);
    }
}

package ast;

import environment.Environment;

/**
 * A class that represents a While statement with an Expression
 * and a Program. The Expression must be relational
 * (must evaluate to a boolean).
 * Extends the abstract Statement class.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 * @version 12/16/2019
 */
public class While extends Statement
{

    private Expression expression;
    private Program program;

    /**
     * Constructor for the While class. Assigns an expression and program
     * to the object.
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
     * A getter for (the relational) Expression stored by the While object.
     *
     * @return the instance field expression
     */
    public Expression getExpression()
    {
        return expression;
    }


    /**
     * Executes a While statement. First evaluates the relational
     * Expression. If the expression evaluates to true, program
     * is executed. Then, in a loop, the condition is reevaluated.
     * As long as the Expression reevaluates to true, program is ran in the
     * loop again.
     *
     * @precondition the Expression stored by the While class
     *               represents a boolean value (is categorized as
     *               relational Expression)
     * @param e the Environment related to the current method
     *          that contains the relevant symbol table for
     * @throws RuntimeException in case the Expression stored
     *         in the While class is an arithmetic one (evaluates
     *         to an integer data type).
     */
    public void exec(Environment e)
    {
        boolean cond;
        if (expression.eval(e).whichVal().equals("i"))
            throw new RuntimeException("Expected Relational Operation, Binary Op found.");
        else
            cond = expression.eval(e).getBoolVal();
        while (cond)
        {
            program.exec(e);
            cond = expression.eval(e).getBoolVal();
        }
    }
}

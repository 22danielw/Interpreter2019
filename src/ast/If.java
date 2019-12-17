package ast;

import environment.Environment;

/**
 * A class that represents an If statement with an Expression
 * and two Programs. The Expression must be a relational one
 * (must evaluate to a boolean).
 * Extends the abstract Statement class.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 * @version 12/16/2019
 */
public class If extends Statement
{
    private Expression expression;
    private Program program1;
    private Program program2;

    /**
     * Constructor for the If class. Stores an expression two programs
     * in the object. program1 is executed if the expression evaluates
     * to true; program2 is in the else clause.
     *
     * @precondition the Expression is a relational Expression (evaluating
     *               it will return a boolean value).
     * @param e the relational Expression stored by the If object
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
     * A getter for the (relational) Expression stored by the If object.
     *
     * @return the instance field expression
     */
    public Expression getExpression()
    {
        return expression;
    }

    /**
     * Executes an If statement. First evaluates the relational
     * Expression. If the expression evaluates to true, program1
     * is executed. If it evaluates to false and program2 is not
     * null (user has declared an else clause), program2 is executed.
     * Both programs are executed using the Environment e.
     *
     * @precondition the Expression stored by the If class
     *               represents a boolean value (is categorized as
     *               relational Expression)
     * @param e the Environment related to the current method
     *          that contains the relevant symbol table for
     *          variables.
     * @throws RuntimeException in case the Expression stored
     *         in the If class is an arithmetic one (evaluates
     *         to an integer data type).
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

package ast;

import environment.Environment;

/**
 * A class that represents an assignment statement for
 * the AST. It stores a variable and the associated expression.
 * Extends the abstract Statement class
 *
 * @author Daniel Wu
 * @version 10/19/2019
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;

    /**
     * Constructs an Assignment object with the given parameters. Stores
     * the instance variables representing the name and value of the
     * variable
     *
     * @param v the name of the variable
     * @param e the expression represented by the variable
     */
    public Assignment(String v, Expression e)
    {
        var = v;
        exp = e;
    }

    /**
     * A getter method for the name of the variable
     * @return the instance field var
     */
    public String getVariable()
    {
        return var;
    }

    /**
     * A getter method for expression stored by the assignment
     * @return the instance field exp
     */
    public Expression getExpression()
    {
        return exp;
    }


    public void exec(Environment e)
    {
        e.setVariable(var, exp.eval(e));
    }
}

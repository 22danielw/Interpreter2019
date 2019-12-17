package ast;

import environment.Environment;

/**
 * A class that represents an assignment statement for
 * the AST. It stores a variable and its associated expression.
 * Extends the abstract Statement class.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 * @version 12/16/2019
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;

    /**
     * Constructs an Assignment object with the given parameters. Stores
     * the instance variables representing the name and value of the
     * variable.
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
     * A getter method for expression stored by the assignment.
     * Returns the instance variable.
     *
     * @return the instance field exp
     */
    public Expression getExpression()
    {
        return exp;
    }


    /**
     * Executes the Abstract Syntax Tree class and assigns
     * the expression's value to the correct variable name
     * stored in the symbol table represented by the Environment
     * e.
     *
     * @precondition the Environment is not null
     *
     * @postcondition the Environment's symbol table has been updated
     *
     * @param e the Environment related to the current method
     *          that contains the relevant symbol table for
     *          variables.
     */
    public void exec(Environment e)
    {
        e.setVariable(var, exp.eval(e));
    }
}

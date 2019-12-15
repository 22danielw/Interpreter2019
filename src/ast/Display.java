package ast;

/**
 * A class that represents a Writeln() statement that
 * stores an expression.
 * Extends the abstract Statement class
 *
 * @author Daniel Wu
 * @version 10/19/2019
 */
public class Display extends Statement
{
    private Expression exp;
    private Read read;

    /**
     * A constructor for the Writeln class that takes and
     * stores an Expression
     *
     * @param e the Expression stored by the Writeln class
     */
    public Display(Expression e, Read r)
    {
        exp = e;
        read = r;
    }

    /**
     * A getter for the Expression stored by the Writeln
     * object.
     *
     * @return the instance field exp
     */
    public Expression getExpression()
    {
        return exp;
    }
}
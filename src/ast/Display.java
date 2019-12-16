package ast;

import environment.Environment;
import java.util.Scanner;

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

    /**
     * Displays
     * @param e
     */
    public void exec(Environment e)
    {
        if (exp.eval(e).whichVal().equals("b"))
            System.out.println(exp.eval(e).getBoolVal());
        else
            System.out.println(exp.eval(e).getIntVal());
        if (read != null)
        {
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            e.setVariable(read.getVariable().getName(), new Value(input));
        }
    }
}

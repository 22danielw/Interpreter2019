package ast;

import environment.Environment;
import java.util.Scanner;

/**
 * A class that represents a display statement that
 * prints an expression. It also has the ability
 * to store input from the reader and set the value
 * to a given variable.
 *
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
     * A constructor for the display class that takes and
     * stores an Expression and Read object.
     *
     * @param e the Expression stored by the display class
     * @param r the Read object that the display class
     *          stores. If this object is null, program does
     *          not prompt the user for the input.
     */
    public Display(Expression e, Read r)
    {
        exp = e;
        read = r;
    }

    /**
     * A getter for the Expression stored by the display
     * object.
     *
     * @return the instance field exp
     */
    public Expression getExpression()
    {
        return exp;
    }

    /**
     * Executes a display/read Statement. First evaluates
     * the Expression stored to get a Value object. Then,
     * the method prints the value that the Value object
     * represents, printing either a boolean or integer
     * depending on what data type the Value object stored.
     * Then, if read is not null, it reads an integer input
     * from the user and stores it into the environment
     * using the variable name provided by the Read class.
     *
     * @param e the Environment related to the current method
     *          that contains the relevant symbol table for
     *          variables.
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

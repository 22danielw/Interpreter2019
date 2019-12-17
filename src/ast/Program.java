package ast;
import environment.Environment;

import javax.print.DocFlavor;
import java.util.List;

/**
 * A class that represents a Program containing a List
 * of Statement objects. Treated and executed like a block
 * of statements.
 *
 * @author Daniel Wu
 * @version 10/10/19
 * @version 12/16/2019
 */
public class Program
{
    List<Statement> statements;

    /**
     * The constructor that initializes the list of statements.
     *
     * @param s the Statements contained by the Program
     */
    public Program(List<Statement> s)
    {
        statements = s;
    }


    /**
     * Executes a Program by executing each statement
     * the program contains in sequential order. Each
     * statement is given the Environment e, and polymorphism
     * ensures that the correct exec() is called.
     *
     * @param e the Environment related to the current method
     *          that contains the relevant symbol table for
     *          variables.
     */
    public void exec(Environment e)
    {
        for (Statement s: statements)
        {
            s.exec(e);
        }
    }
}

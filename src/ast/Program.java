package ast;
import environment.Environment;

import javax.print.DocFlavor;
import java.util.List;

/**
 * A class that represents a Program containing a List
 * of ProcedureDeclarations and a single statement (typically
 * a BEGIN END block).
 *
 * @author Daniel Wu
 * @version 10/10/19
 */
public class Program
{
    List<Statement> statements;

    /**
     * The constructor that initializes the list of ProcedureDeclarations
     * and the Statement.
     *
     * @param s the Statement contained by the Program
     */
    public Program(List<Statement> s)
    {
        statements = s;
    }

    /**
     * Getter for the Statement stored by the Program.
     *
     * @return the instance variable statement
     */
    public List<Statement> getStatements()
    {
        return statements;
    }

    public void exec(Environment e)
    {
        for (Statement s: statements)
        {
            s.exec(e);
        }
    }
}

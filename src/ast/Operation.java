package ast;

import environment.Environment;
import parser.Parser;

/**
 * A class that represents either an arithmetic operation or
 * a relational operation between two expressions.
 * Extends the abstract Expression class.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 * @version 12/16/2019
 */
public class Operation extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;

    /**
     * A constructor for an Operation object. Assigns an operator and two
     * expressions as instance variables.
     *
     * @param operator the operator for the operation
     * @param e1 the first expression in the operation
     * @param e2 the second expression in the operation
     */
    public Operation(String operator, Expression e1, Expression e2)
    {
        op = operator;
        exp1 = e1;
        exp2 = e2;
    }

    /**
     * Uses the Parser's static method isRelop(String s) to return
     * whether or not the Operation is a relational operation. If
     * it is a relational operation, client classes should treat
     * the return value from the evaluated method as a boolean
     * value. Else, it should treat the returned value as an integer
     * value.
     *
     * @return true if the Operation is relational (if op is a relational
     *         operator), false otherwise (if the Operation is arithmetic/if
     *         the operator is an arithmetic operator).
     */
    public boolean isRelOp()
    {
        return Parser.isRelop(op);
    }

    /**
     * Evaluates an operation. If the operation is arithmetic, a Value object
     * containing the integer result of the arithmetic operation is
     * returned. However, if the operation is relational, then
     * the operator is examined. If the operator is = or <>, two different cases
     * are examined. If both expressions involved in the operation are of the
     * same type, the operation can be evaluated as a normal boolean operation. However,
     * if the wrong getter is used to retrieve a value, this exception is handled by
     * the Value class. If the operator is <, >, <=, or >=, only integer comparisons
     * can be done, as these relational operators are only compatible with expressions
     * of numerical/integer type. In all cases, the eval method either returns
     * a Value object representing the value of the Expression or a RuntimeException
     * if types and operators don't match.
     *
     * @param e the Environment related to the current method
     *          that contains the relevant symbol table for
     *          variables.
     * @return a Value that either contains some integer or boolean
     *         value based on what type of Operation the object
     *         represents and what the result evaluates to.
     */
    public Value eval(Environment e)
    {
        if (!isRelOp())
        {
            if (op.equals("*"))
                return new Value(exp1.eval(e).getIntVal() * exp2.eval(e).getIntVal());
            else if (op.equals("/"))
                return new Value(exp1.eval(e).getIntVal() / exp2.eval(e).getIntVal());
            else if (op.equals("+"))
                return new Value(exp1.eval(e).getIntVal() + exp2.eval(e).getIntVal());
            else if (op.equals("-"))
                return new Value(exp1.eval(e).getIntVal() - exp2.eval(e).getIntVal());
            else
                throw new RuntimeException("Arithmetic operator expected, " + op + " found.");
        }
        else
        {
            if (op.equals("="))
            {
                if (exp1.eval(e).whichVal().equals("b"))
                    return new Value(exp1.eval(e).getBoolVal() == exp2.eval(e).getBoolVal());
                else
                    return new Value(exp1.eval(e).getIntVal() == exp2.eval(e).getIntVal());
            }
            else if (op.equals("<>"))
            {
                if (exp1.eval(e).whichVal().equals("b"))
                    return new Value(exp1.eval(e).getBoolVal() != exp2.eval(e).getBoolVal());
                else
                    return new Value(exp1.eval(e).getIntVal() != exp2.eval(e).getIntVal());
            }
            else if (op.equals("<="))
                return new Value(exp1.eval(e).getIntVal() <= exp2.eval(e).getIntVal());
            else if (op.equals(">="))
                return new Value(exp1.eval(e).getIntVal() >= exp2.eval(e).getIntVal());
            else if (op.equals("<"))
                return new Value(exp1.eval(e).getIntVal() < exp2.eval(e).getIntVal());
            else if (op.equals(">"))
                return new Value(exp1.eval(e).getIntVal() > exp2.eval(e).getIntVal());
            else
                throw new RuntimeException("Relational operator expected, " + op + " found.");
        }
    }
}

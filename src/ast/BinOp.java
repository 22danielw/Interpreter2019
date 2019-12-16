package ast;

import environment.Environment;
import parser.Parser;

import java.util.concurrent.locks.Condition;

/**
 * A class that represents a binary operation between two expressions.
 * Extends the abstract Expression class.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;

    /**
     * A constructor for a BinOp object. Assigns an operator and two
     * expressions as instance variables.
     * @param operator the operator for the binary operation
     * @param e1 the first expression in the operation
     * @param e2 the second expression in the operation
     */
    public BinOp(String operator, Expression e1, Expression e2)
    {
        op = operator;
        exp1 = e1;
        exp2 = e2;
    }

    public boolean isRelOp()
    {
        return Parser.isRelop(op);
    }

    /**
     * A getter that returns the operator for the BinOp class
     * as a String.
     *
     * @return the instance field op
     */
    public String getOperator()
    {
        return op;
    }

    /**
     * A getter that returns the first expression
     * stored by the BinOp object
     *
     * @return the instance field exp1
     */
    public Expression getExpression1()
    {
        return exp1;
    }

    /**
     * A getter that returns the second expression
     * stored by the BinOp object
     *
     * @return the instance field exp2
     */
    public Expression getExpression2()
    {
        return exp2;
    }

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
            else
                return new Value(exp1.eval(e).getIntVal() - exp2.eval(e).getIntVal());
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
            else
                return new Value(exp1.eval(e).getIntVal() > exp2.eval(e).getIntVal());
        }
    }
}

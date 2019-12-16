package parser;

import ast.*;
import ast.Number;
import environment.Environment;
import scanner.ScanErrorException;
import scanner.Scanner;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A parser class that parses the input from a stream of
 * tokens from a scanner. It follows the grammar rules provided.
 * The file ends with a period.
 *
 * @author Daniel Wu
 *
 * @version 10/19/19
 */
public class Parser
{

    private Scanner scanner;
    private String currentToken;
    private HashMap<String, Expression> variables;

    /**
     * Constructs a Parser object from a Scanner and assigns
     * instance variables scanner and currentToken.
     *
     * @param s the Scanner that feeds tokens to the Parser
     * @throws ScanErrorException when there is an error in the Scanner
     */
    public Parser(Scanner s) throws ScanErrorException
    {
        scanner = s;
        currentToken = scanner.nextToken();
        variables = new HashMap<String, Expression>();
    }

    /**
     * Eats a token and sets the currentToken the next token provided by
     * the scanner if the eaten token matches the previous currentToken.
     *
     * @precondition currentToken is valid and initialized
     * @param expected the expected currentToken
     * @throws ScanErrorException if the eaten token does not match the currentToken
     */
    private void eat(String expected) throws ScanErrorException
    {
        if (currentToken.equals(expected))
        {
            currentToken = scanner.nextToken();
        }
        else
        {
            throw new IllegalArgumentException(currentToken +
                    " was expected and " + expected + " was found.");
        }
    }

    /**
     * Parses an integer and returns its value. Eats the integer.
     *
     * @precondition currentToken is an integer
     * @postcondition number token has been eaten
     * @return the value of the parsed integer String
     * @throws ScanErrorException if eaten token does not match the currentToken
     */
    private Number parseNumber() throws ScanErrorException
    {
        int num = Integer.parseInt(currentToken);
        eat(currentToken);
        return new Number(num);
    }

    /**
     * Parses and returns a program. A Program contains any number of
     * Procedure Declarations, followed by a single statement (typically
     * a BEGIN END block). Returns a Program object.
     *
     * @return a Program object storing the ProcedureDeclarations and statement
     * @throws ScanErrorException if eaten token does not match currentToken
     */
    public Program parseProgram() throws ScanErrorException
    {
        ArrayList<Statement> statements = new ArrayList<Statement>();
        while (scanner.hasNext() &&  !(currentToken.equals("end") || currentToken.equals("else")) )
        {
            Statement s = parseStatement();
            statements.add(s);
        }
        return new Program(statements);
    }

    /**
     * The method parses a single statement based on the grammar for
     * parsing Statement.
     *
     * @return a new Statement class based on what kind of Statement the file contains
     * @throws ScanErrorException if eaten tokens do not match their currentToken
     */
    public Statement parseStatement() throws ScanErrorException
    {
        if (currentToken.equals("display"))
        {
            eat("display");
            Expression exp = parseExpression();
            return new Display(exp, parseSt1());
        }
        else if (currentToken.equals("assign"))
        {
            eat("assign");
            String id = currentToken;
            eat(currentToken);
            eat("=");
            Expression e = parseExpression();
            return new Assignment(id, e);
        }
        else if (currentToken.equals("while"))
        {
            eat("while");
            Expression e = parseExpression();
            eat("do");
            Program p = parseProgram();
            eat("end");
            return new While(e, p);
        }
        else
        {
            eat("if");
            Expression e = parseExpression();
            eat("then");
            Program p1 = parseProgram();
            Program p2 = parseSt2();
            return new If(e, p1, p2);
        }
    }

    private Read parseSt1() throws ScanErrorException
    {
        if (currentToken.equals("read"))
        {
            eat("read");
            String id = currentToken;
            eat(currentToken);
            return new Read(new Variable(id));
        }
        else
            return null;
    }

    private Program parseSt2() throws ScanErrorException
    {
        if (currentToken.equals("end"))
        {
            eat("end");
            return null;
        }
        else
        {
            eat("else");
            Program p = parseProgram();
            eat("end");
            return p;
        }
    }


    private Expression parseExpression() throws ScanErrorException
    {
        Expression exp = parseAddExpression();
        while (isRelop(currentToken))
        {
            String s = parseRelop();
            Expression e = parseExpression();
            exp = new BinOp(s, exp, e);
        }
        return exp;
    }

    /**
     * Parses a term as either a term * factor, term / factor,
     * or factor based on the grammar provided.
     *
     * @return the value of the term parsed based on grammars and
     *         evaluation rules
     * @throws ScanErrorException if eaten tokens do not match their currentToken
     */
    private Expression parseAddExpression() throws ScanErrorException
    {
        Expression exp = parseMultExpression();

        while (currentToken.equals("-") || currentToken.equals("+"))
        {
            if (currentToken.equals("-"))
            {
                eat("-");
                exp = new BinOp("-", exp, parseAddExpression());
            }
            else
            {
                eat("+");
                exp = new BinOp("+", exp, parseAddExpression());
            }
        }
        return exp;
    }

    private Expression parseMultExpression() throws ScanErrorException
    {
        Expression exp = parseNegExpression();

        while (currentToken.equals("*") || currentToken.equals("/"))
        {
            if (currentToken.equals("*"))
            {
                eat("*");
                exp = new BinOp("*", exp, parseMultExpression());
            }
            else
            {
                eat("/");
                exp = new BinOp("/", exp, parseMultExpression());
            }
        }
        return exp;
    }


    private Expression parseNegExpression() throws ScanErrorException
    {

        if (currentToken.equals("-"))
        {
            eat("-");
            return new BinOp("*", new Number(-1), parseValue());
        }
        else
        {
            Expression e = parseValue();
            return e;
        }
    }

    private Expression parseValue() throws ScanErrorException
    {
        if (currentToken.equals("("))
        {
            eat("(");
            Expression exp = parseExpression();
            eat(")");
            return exp;
        }
        else if (isNumber(currentToken))
        {
            return parseNumber();
        }
        else if (currentToken.equals("true") || currentToken.equals("false"))
        {
            String s = currentToken;
            eat(currentToken);
            if (s.equals("true"))
            {
                return new BinOp("=", new Number(0), new Number(0));
            }
            else
                return new BinOp("<>", new Number(0), new Number(0));
        }
        else
        {
            String s = currentToken;
            eat(currentToken);
            return new Variable(s);
        }
    }

    /**
     * Returns whether or not the given String stores a number.
     * Scans each character and determines if it is a digit.
     *
     * @param s the String being evaluated
     * @return true if the String is a number; false otherwise
     */
    public static boolean isNumber(String s)
    {
        boolean ret = true;
        for (int i = 0; i < s.length(); i++)
        {
            if (!Scanner.isDigit(s.charAt(i)))
                ret = false;
        }
        return ret;
    }

    /**
     * Returns whether or not the given String is a relational operator.
     *
     * @param op the String being evaluated
     * @return true if the String is a relop; false otherwise
     */
    public static boolean isRelop(String op)
    {
        return op.equals("=") || op.equals("<>") || op.equals("<") ||
                op.equals(">") || op.equals("<=") || op.equals(">=");
    }

    /**
     * Parses a Relational Operator and returns the result as a String.
     *
     * @return the relational operator found as a String
     * @throws ScanErrorException if eaten tokens do not match their currentToken
     */
    private String parseRelop() throws ScanErrorException
    {
        String op = currentToken;
        if (op.equals("=") || op.equals("<>") || op.equals("<") ||
                op.equals(">") || op.equals("<=") || op.equals(">="))
        {
            eat(currentToken);
            return op;
        }
        throw new ScanErrorException("Unexpected Token: RelOperator expected.");
    }


}
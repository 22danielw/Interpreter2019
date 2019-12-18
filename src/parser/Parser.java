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
 * @version 12/16/19
 */
public class Parser
{

    private Scanner scanner;
    private String currentToken;

    /**
     * Constructs a Parser object from a Scanner and assigns
     * instance variables scanner and currentToken. The Scanner
     * feeds the Parser object a stream of tokens to parse,
     * and the currentToken is set to the first token of the
     * stream. currentToken represents the token being parsed.
     *
     * @param s the Scanner that feeds tokens to the Parser
     * @throws ScanErrorException when there is an error in the Scanner
     */
    public Parser(Scanner s) throws ScanErrorException
    {
        scanner = s;
        currentToken = scanner.nextToken();
    }

    /**
     * Eats a token and sets the currentToken the next token provided by
     * the scanner if the token passed into the eat() method matches the
     * currentToken
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
     * Parses an integer and returns its as a Number object. Eats the integer.
     *
     * @precondition currentToken is an integer
     * @postcondition Number token has been eaten
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
     * Parses and returns a program. A Program contains any number of statements
     * separated by either an "end", "else", or at the end of a file. The method
     * returns a Program object containing all statements in the block.
     *
     * @return a Program object storing the block of statements.
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
     * parsing Statement. Either returns an Assignment, Display, While, or
     * If object, all of which are subclasses of Statement, depending on what
     * the currentToken is when entering the method. Each part of the statement
     * is then categorized and returned as its respective object containing
     * information for execution.
     *
     * @precondition the Statement is one of four recognized by the grammar.
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

    /**
     * Parses the second part of a display statement that the user may have
     * included. Called when the user makes a display statement. If the user
     * does not include a read statement after the display statement, returns a
     * null object. The returned object is used to construct the Display
     * Statement.
     *
     * @return a new Read object containing the variable the object reads to, or
     *         a null object.
     * @throws ScanErrorException if eaten tokens do not match their currentToken
     */
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

    /**
     * Parses the else block of an If statement and returns the block of statements
     * as a Program object. If there is no else block, method returns null. Used
     * to construct an If statement.
     *
     * @return the block of statements representing the else block of an If statement.
     * @throws ScanErrorException if eaten tokens do not match their currentToken
     */
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


    /**
     * Parses an Expression following the grammar by assigning either returning
     * an AddExpr or recursively parsing a sequence of relational operations between
     * two or more expressions. Only expressions that are boolean values or evaluate
     * to boolean values may be chained together. Chain expressions always evaluate
     * from right to left; for example, true = 2 < 3 works, but 2 < 3 = true does not.
     *
     * @return either an AddExpr or a chain of relational operations, eventually
     *         returning an Expression object.
     * @throws ScanErrorException if eaten tokens do not match their currentToken
     */
    private Expression parseExpression() throws ScanErrorException
    {
        Expression exp = parseAddExpression();
        while (isRelop(currentToken))
        {
            String s = parseRelop();
            Expression e = parseExpression();
            exp = new Operation(s, exp, e);
        }
        return exp;
    }

    /**
     * Parses an expression for addition/subtraction, either returning a Binary
     * Operation that adds a MultExpr and an AddExpr or an AddExpr
     * recursively. Addition/subtraction can be chained together as long
     * as each expression only returns a numerical value.
     *
     * @return the AddExpr following the grammar rules provided.
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
                exp = new Operation("-", exp, parseAddExpression());
            }
            else
            {
                eat("+");
                exp = new Operation("+", exp, parseAddExpression());
            }
        }
        return exp;
    }

    /**
     * Parses an expression for multiplication/division, either returning a Binary
     * Operation that adds a NegExpr and a MultExpr or a MultExpr
     * recursively. Multiplication/division can be chained together as long
     * as each expression only returns a numerical value.
     *
     * @return a MultExpr that follows the grammar rules provided
     * @throws ScanErrorException if eaten tokens do not match their currentToken
     */
    private Expression parseMultExpression() throws ScanErrorException
    {
        Expression exp = parseNegExpression();

        while (currentToken.equals("*") || currentToken.equals("/"))
        {
            if (currentToken.equals("*"))
            {
                eat("*");
                exp = new Operation("*", exp, parseMultExpression());
            }
            else
            {
                eat("/");
                exp = new Operation("/", exp, parseMultExpression());
            }
        }
        return exp;
    }


    /**
     * Parses a NegExpr, defined as a Value or a negative value. If the
     * value is negative, the negative sign is eaten and an Operation
     * multiplying the Value with -1 is returned to represent the negative
     * value. However, if there is no negative value, the Value is returned
     * based on the grammar.
     *
     * @return a NegExpr based on the grammar rules provided
     * @throws ScanErrorException if eaten tokens do not match their currentToken
     */
    private Expression parseNegExpression() throws ScanErrorException
    {

        if (currentToken.equals("-"))
        {
            eat("-");
            return new Operation("*", new Number(-1), parseValue());
        }
        else
        {
            Expression e = parseValue();
            return e;
        }
    }

    /**
     * Parses a Value defined as either a boolean, integer, Expression in
     * parenthesis, or a variable id. Eats all parts of the Value and returns
     * the its Expression representation.
     *
     * @return a Value based on the grammar rules provided
     * @throws ScanErrorException if eaten tokens do not match their currentToken
     */
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
                return new Operation("=", new Number(0), new Number(0));
            }
            else
                return new Operation("<>", new Number(0), new Number(0));
        }
        else
        {
            String s = currentToken;
            eat(currentToken);
            return new Variable(s);
        }
    }

    /**
     * This is a static utility method. It returns whether or not the given
     * String stores a number. Scans each character to determine if it is a digit.
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
     * Relational operators are considered =, <>, <=, <, >, >=.
     * This is a static utility method.
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
     * Eats the relational operator. If the token is not an operator,
     * an Exception is thrown.
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
import ast.Program;
import environment.Environment;
import parser.Parser;
import scanner.ScanErrorException;
import scanner.Scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * A class that tests the Interpreter created by calling one of three
 * static test methods and running test files. By tracing the output
 * of the code and comparing it to the executed code as well as making
 * sure that all edge cases are reached will ensure that the Interpreter
 * correctly tests all the code.
 *
 * @author Daniel Wu
 * @version 12/19/2019
 */
public class InterpreterTester
{

    /**
     * Main method for the Interpreter Tester. This method is used to call
     * one of three test methods to either test the Interpreter based on
     * given test cases or a custom criteria.
     *
     * @param args Runtime arguments
     * @throws FileNotFoundException the name of the given file is invalid
     * @throws ScanErrorException when there is an error in scanning the file
     */
    public static void main(String[] args) throws ScanErrorException, FileNotFoundException
    {
        simpleTest();
        //simpleTest2();
    }

    /**
     * Executes the code in simpleTest.txt via the SIMPLE interpreter. Can
     * be called by the main method.
     * Note: simpleTest.txt has a statement that says "x < 5 > 3"; however, this
     * SIMPLE interpreter does not support this functionality because it evaluates
     * the statements separately, returning a boolean one expression before evaluating
     * the other.
     *
     * @throws FileNotFoundException the name of the given file is invalid
     * @throws ScanErrorException when there is an error in scanning the file
     */
    public static void simpleTest() throws ScanErrorException, FileNotFoundException
    {
        String s = "simpleTest.txt";
        InputStream reader = new FileInputStream(s);
        Scanner scanner = new Scanner(reader);
        Parser p = new Parser(scanner);
        Environment env = new Environment();
        Program program1 = p.parseProgram();
        program1.exec(env);
    }

    /**
     * Executes the code in simpleTest2.txt via the SIMPLE interpreter. Can
     * be called by the main method.
     * Note: simpleText2.txt has a statement that says "if (limit + 5)" then ...
     * This SIMPLE interpreter does not support this functionality because the
     * expression given as a condition for an if statement must return a boolean,
     * whether it is a relational operator, boolean variable, or "true or false".
     *
     * @throws FileNotFoundException the name of the given file is invalid
     * @throws ScanErrorException when there is an error in scanning the file
     */
    public static void simpleTest2() throws ScanErrorException, FileNotFoundException
    {
        String s = "simpleTest2.txt";
        InputStream reader = new FileInputStream(s);
        Scanner scanner = new Scanner(reader);
        Parser p = new Parser(scanner);
        Environment env = new Environment();
        Program program1 = p.parseProgram();
        program1.exec(env);
    }

    /**
     * Executes the code in in any SIMPLE file using the interpreter. Used to test
     * custom files.
     *
     * @param s the name of the SIMPLE file being executed
     * @throws FileNotFoundException the name of the given file is invalid
     * @throws ScanErrorException when there is an error in scanning the file
     */
    public static void test(String s) throws ScanErrorException, FileNotFoundException
    {
        InputStream reader = new FileInputStream(s);
        Scanner scanner = new Scanner(reader);
        Parser p = new Parser(scanner);
        Environment env = new Environment();
        Program program1 = p.parseProgram();
        program1.exec(env);
    }
}

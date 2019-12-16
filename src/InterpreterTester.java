import ast.Program;
import environment.Environment;
import parser.Parser;
import scanner.Scanner;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * A
 */
public class InterpreterTester {

    public static void main(String[] args) throws Exception
    {
        //First test file
        String s = "simpleTest.txt";
        InputStream reader = new FileInputStream(s);
        Scanner scanner = new Scanner(reader);
        Parser p = new Parser(scanner);
        Environment env = new Environment();
        Program program1 = p.parseProgram();
        program1.exec(env);


        //Second test file
        s = "simpleTest.txt";
        reader = new FileInputStream(s);
        scanner = new Scanner(reader);
        p = new Parser(scanner);
        env = new Environment();
        Program program2 = p.parseProgram();
    }
}

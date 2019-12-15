package ast;

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
        String s = "parserTest8.txt";
        InputStream reader = new FileInputStream(s);
        Scanner scanner = new Scanner(reader);
        Parser p = new Parser(scanner);
        Environment env = new Environment(null);
    }
}

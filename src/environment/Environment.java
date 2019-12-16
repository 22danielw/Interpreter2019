package environment;

import ast.Expression;
import ast.Value;

import java.util.HashMap;

/**
 * Represents an Environment that stores a Hashmap of
 * variable names and their corresponding Integer values.
 * It can also map procedure names to declarations.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 */
public class Environment
{
    HashMap<String, Value> variables;

    /**
     * Constructs an environment by initializing the HashMap that
     * stores variable information.
     */
    public Environment()
    {
        variables = new HashMap<String, Value>();
    }

    /**
     * Declares a variable in the current environment by
     * adding the name and value into the variables Map.
     *
     * @param variable the name of the variable being declared
     * @param val the Value assigned to the label for the variable being declared
     */
    public void setVariable(String variable, Value val)
    {
        variables.put(variable, val);
    }

    /**
     * Returns the value of a variable with a given name. If the current
     * environment does not contain the variable, the method looks into
     * the parent environments. If no environment contains the variable, the
     * method throws an Exception.
     *
     * @param name the name of the variable whose value is to be returned
     * @return the int value of the variable with the name name
     * @throws RuntimeException if the variable is not found
     */
    public Value getVariable(String name) throws RuntimeException
    {
        if (variables.containsKey(name))
            return variables.get(name);
        else
            throw new RuntimeException("Environment does not return");
    }


}

package environment;

import ast.Value;

import java.util.HashMap;

/**
 * Represents an Environment that stores a Hashmap of
 * variable names and their corresponding Value objects.
 *
 * @author Daniel Wu
 * @version 10/19/2019
 * @version 12/16/2019
 */
public class Environment
{
    HashMap<String, Value> variables;

    /**
     * Constructs an environment by initializing the HashMap that
     * stores variable information. The HashMap's keys are Strings
     * (variable names) and the values are Values (boolean/int data).
     */
    public Environment()
    {
        variables = new HashMap<String, Value>();
    }

    /**
     * Declares a variable in the current environment by
     * adding the name and value into the variables Map.
     * If the variable already exists, the Map will automatically
     * map the new Value to the existing key.
     *
     * @param variable the name of the variable being declared
     * @param val the Value assigned to the label for the variable being declared
     */
    public void setVariable(String variable, Value val)
    {
        variables.put(variable, val);
    }

    /**
     * Returns the value of a variable with a given name from the
     * HashMap.
     *
     * @param name the name of the variable whose value is to be returned
     * @return the Value of the variable with the name name
     * @throws RuntimeException if the variable is not found
     */
    public Value getVariable(String name) throws RuntimeException
    {
        if (variables.containsKey(name))
            return variables.get(name);
        else
            throw new RuntimeException("Variable " + name + " has not been found");
    }


}

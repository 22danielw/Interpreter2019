package environment;

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
    HashMap<String, Integer> variables;
    Environment parent;

    /**
     * Constructs an environment by initializing the HashMap that
     * stores variable information.
     *
     * @param p the parent environment. If the Environment is the
     *          global environment, p will be null.
     */
    public Environment(Environment p)
    {
        variables = new HashMap<String, Integer>();
        parent = p;
    }

    /**
     * Declares a variable in the current environment by
     * adding the name and value into the variables Map.
     *
     * @param variable the name of the variable being declared
     * @param value the value of the variable being declared
     */
    public void setVariable(String variable, int value)
    {
        variables.put(variable, value);
    }

    /**
     * Returns the value of a variable with a given name. If the current
     * environment does not contain the variable, the method looks into
     * the parent environments. If no environment contains the variable, the
     * method throws an Exception.
     *
     * @param name the name of the variable whose value is to be returned
     * @return the int value of the variable with the name name
     * @throws Exception if the variable is not found
     */
    public int getVariable(String name) throws Exception
    {
        if (variables.containsKey(name))
            return variables.get(name);
        if (parent != null)
            return parent.getVariable(name);
        throw new Exception("Variable has not been declared");
    }


}

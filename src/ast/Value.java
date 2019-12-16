package ast;

public class Value
{
    private Integer intVal;
    private Boolean boolVal;

    public Value(int i)
    {
        intVal = i;
        boolVal = null;
    }

    public Value(boolean b)
    {
        boolVal = b;
        intVal = null;
    }

    public String whichVal()
    {
        if (boolVal == null)
            return "i";
        else
            return "b";
    }

    public boolean getBoolVal()
    {
        return boolVal;
    }

    public int getIntVal()
    {
        return intVal;
    }
}

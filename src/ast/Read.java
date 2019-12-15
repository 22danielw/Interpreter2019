package ast;

public class Read extends Statement
{
    Variable id;

    public Read(Variable v)
    {
        id = v;
    }
}


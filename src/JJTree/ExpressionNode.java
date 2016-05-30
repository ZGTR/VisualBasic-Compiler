package JJTree;

public class ExpressionNode extends TreeNode {
    private String type;

    public ExpressionNode(TreeNode baseNode)
    {
        this.baseNode = baseNode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Object execute() {
        try
        {
            double x1 = (Double)(childs.get(0).execute());
            double x2 = (Double)(childs.get(1).execute());

            if(type.equals("+"))
                return x1 + x2;
            if(type.equals("-"))
                return x1 - x2;
            if(type.equals("*"))
                return x1 * x2;
            if(type.equals("/"))
                return x1 / x2;
            if(type.equals("=="))
                return x1 == x2;
            if(type.equals(">"))
                return x1 > x2;
            if(type.equals("<"))
                return x1 < x2;
            if(type.equals(">="))
                return x1 >= x2;
            if(type.equals("<="))
                return x1 <= x2;
            if(type.equals("!="))
                return x1 != x2;
            if(type.equals("OR"))
                return (x1 != 0) || (x2 != 0);
            if(type.equals("XOR"))
                return (!(x1 != 0) && (x2 != 0))||((x1 != 0) && !(x2 != 0));
            if(type.equals("AND"))
                return (x1 != 0) && (x2 != 0);
            if(type.equals("NOT"))
                return !(x2 != 0);
        }
        catch(Exception ex)
        {
            return childs.get(0).execute();
        }       
        return null;
    }
}

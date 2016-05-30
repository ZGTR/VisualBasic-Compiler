package JJTree;

public class ConstantNode extends TreeNode {
    private Object value;
    
    public Object getObjectVal()
    {
        return value;
    }   
    
    public ConstantNode(Object value, TreeNode baseNode)
    {
        this.baseNode = baseNode;
        this.value = value;
    }

    @Override
    public Object execute() {
        return value;
    }
}
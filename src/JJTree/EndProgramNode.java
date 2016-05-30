package JJTree;

public class EndProgramNode extends TreeNode{

    public EndProgramNode(TreeNode baseNode)
    {
        this.baseNode = baseNode;
    }
    
    @Override
    public Object execute() {
        this.baseNode.setCanExecute(false, "");
        return null;
    }
    
}

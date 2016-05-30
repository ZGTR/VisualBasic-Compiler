package JJTree;

public class PrintNode extends TreeNode {

    public PrintNode(TreeNode baseNode)
    {
        this.baseNode = baseNode;
    }

    @Override
    public Object execute() 
    {
        for(TreeNode t : childs)
            System.out.println(t.execute());
        return null;
    }

}

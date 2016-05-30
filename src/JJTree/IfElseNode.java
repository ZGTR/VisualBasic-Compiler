package JJTree;

import java.util.ArrayList;

public class IfElseNode extends TreeNode{
    
    public IfElseNode(TreeNode baseNode)
    {
        this.baseNode = baseNode;
    }

    @Override
    public Object execute()
    {
        BodyNode ifNode = (BodyNode)childs.get(0);
        if ((Boolean)ifNode.childs.get(0).execute())
        {
            ifNode.childs.get(1).execute();
        }
        else
        {
            if ( childs.size() > 1 )
            {
                childs.get(1).execute();
            }
            //getElseNode().execute();
        }
        return null;
    }
}

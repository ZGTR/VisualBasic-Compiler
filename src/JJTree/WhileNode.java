/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JJTree;

/**
 *
 * @author ZGTR
 */
public class WhileNode extends TreeNode{
    BodyNode bodyNode;
    public WhileNode(TreeNode baseNode)
    {
        this.baseNode = baseNode;
    }
    
    @Override
    public Object execute() {
        bodyNode = (BodyNode)this.childs.get(0);
        while( ((Boolean)bodyNode.childs.get(0).execute())  )
        {
                bodyNode.childs.get(1).execute();
        }
        return null;
    }
    
}

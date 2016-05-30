/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JJTree;

import java.util.ArrayList;

/**
 *
 * @author ZGTR
 */
public class DoPostfixWhileUntilNode extends TreeNode {
    BodyNode bodyNode;
    public DoPostfixWhileUntilNode(TreeNode baseNode)
    {
        this.baseNode = baseNode;
    }

    @Override
    public Object execute() {
        bodyNode = (BodyNode)this.childs.get(0);
        boolean boolCondition = true;
        while( boolCondition )
        {
                bodyNode.childs.get(0).execute();
                boolCondition = (Boolean)bodyNode.childs.get(1).execute();
        }
        return null;
    }
    
}

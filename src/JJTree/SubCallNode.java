/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JJTree;

import java.util.ArrayList;

/**
 *
 * @author Hasan
 */
public class SubCallNode extends TreeNode
{
    private ArrayList<TreeNode> exprNodes = new ArrayList<TreeNode>();
    private String subName;
    
    public SubCallNode(String subName, ArrayList<TreeNode> exprNodes, TreeNode baseNode)
    {
        this.baseNode = baseNode;
        this.subName = subName;
        this.exprNodes = exprNodes;
        //convertParamValues();
        
    }

    @Override
    public Object execute()
    {
        if (Subs.containsKey(subName))
        {
            //SubNode sNode = (SubNode)Subs.get(subName);
            //SubNode sNode = new SubNode(subName, sNode1.parameters, baseNode);
            SubNode sNode = new SubNode((SubNode)Subs.get(subName));
            sNode.baseNode = baseNode;
            //sNode.childs.get(0).baseNode = sNode;
            for(int i = 0; i < sNode.parameters.size(); i++)
                sNode.parameters.get(i).setValue(exprNodes.get(i).execute());
            //((FunctionNode)fNode).setBase(baseNode);
            sNode.execute();
            
            return null;
        }
        else
        {
//            if(HashmapController.IsOccuredInBaseHashmaps(this, subName))
//            {
//
//            }

            
        }
        System.err.println("Calling Unfifiende Sub");


        for(TreeNode t : childs)
            t.execute();
        return null;
    }

}

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
public class LabelsController {
    static String nextPrivilegedLabelString = "L_";
    static int nextPrivilegedLabelNr = 0;
    
    public static String GetNextPrivilegedLabelString()
    {
        nextPrivilegedLabelNr++;        
        return nextPrivilegedLabelString + nextPrivilegedLabelNr;
    }    
    
    public static LabelNode LocateMatchedLabel(GotoNode gotoNode)
    {
        try
        {
            String goToString = gotoNode.getGotoString();
            TreeNode currentBaseNode = gotoNode.baseNode;
            boolean labelFound = false;
            while (!(labelFound))
            {
                while (!(currentBaseNode instanceof BodyNode))
                {
                    currentBaseNode = currentBaseNode.baseNode;
                }            
                if (IsOccuredInLabelList(goToString, ((BodyNode)currentBaseNode).getLabelList()))
                {
                    LabelNode labelNode = LocateLabelInLabelList(goToString,((BodyNode)currentBaseNode).labelNodeList);
                    return labelNode;
                } 
                else
                {
                    currentBaseNode = currentBaseNode.baseNode;
                }
            }        
            return null;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    public static boolean IsOccuredInLabelList(String goToString, ArrayList<LabelNode> labelNodeList) {
        for (int i = 0 ; i < labelNodeList.size(); i++)
        {
            if (labelNodeList.get(i).getLabelString().equals(goToString))
            {
                return true;
            }
        }
        return false;
    }

    private static LabelNode LocateLabelInLabelList(String goToString, ArrayList<LabelNode> labelNodeList) {
        for (int i = 0 ; i < labelNodeList.size(); i++)
        {
            if (labelNodeList.get(i).getLabelString().equals(goToString))
            {
                return labelNodeList.get(i);
            }
        }
        return null;
    }

    static boolean IsSonOfTypeGotoNode(TreeNode t) {
        boolean isSonGoto = false;
        for(TreeNode childNode : t.childs)
        {
            if (childNode instanceof GotoNode)
            {
                isSonGoto = true;
                break;
            }
            else
            {
                if (!childNode.childs.isEmpty())
                {
                    IsSonOfTypeGotoNode(childNode);
                }
                else
                {
                    isSonGoto = false;
                    break;
                }
            }
        }
        return isSonGoto;
    }

    static TreeNode LocateNextProperExecutableNode(TreeNode baseNode) {
        
        
        
        return null;
    }
}

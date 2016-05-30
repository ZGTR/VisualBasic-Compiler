package JJTree;

import java.util.ArrayList;

public class BodyNode extends TreeNode implements IHashmapable {

    ArrayList<LabelNode> labelNodeList = new ArrayList<LabelNode>();

    public void addToLabelList(LabelNode labelNode)
    {
        labelNodeList.add(labelNode);
    }

    public ArrayList<LabelNode> getLabelList()
    {
        return labelNodeList;
    }

    public BodyNode(TreeNode baseNode)
    {
        this.baseNode = baseNode;
    }

    @Override
    public Object execute()
    {
        for(TreeNode t : childs)
        {
            if (isCanExecute())
            {
                t.execute();
            }
        }
        return null;
    }

//    public Object execute(int numToExceuteFrom)
//    {
//        for(int i = numToExceuteFrom ; i < this.childs.size() ; i++)
//        {
//            if (isCanExecute())
//            {
//                this.childs.get(i).execute();
//            }
//        }
//        return null;
//    }

    public Object execute(TreeNode nodeToContinueAfter)
    {
        boolean isFreeToGo = false;
        for(int i = 0 ; i < this.childs.size() ; i++)
        {
            if(isFreeToGo)
            {
                if (isCanExecute())
                {
                    this.childs.get(i).execute();
                }
            }
            if (this.childs.get(i) == nodeToContinueAfter)
            {
                isFreeToGo = true;
            }
        }
        return null;
    }
}

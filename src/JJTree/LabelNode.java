package JJTree;

public class LabelNode extends TreeNode {
    
    private String labelString;
    private BodyNode bodyNode;
    public LabelNode(TreeNode baseNode)
    {
        this.baseNode = baseNode;
        //HashmapController.LocateNearestIHashmapableBaseNode(this);
        ((BodyNode)this.baseNode).addToLabelList(this);
    }
    
    public String getLabelString() {
        return labelString;
    }

    public void setLabelString(String labelString) {
        this.labelString = labelString;
    }
    
    public void setBodyNode(BodyNode bodyNode)
    {
        this.bodyNode = bodyNode;
    }
    
//    private boolean IsValidLabelStringWithGotoString() {
//        String str1 = (String)(((ConstantNode)this.childs.get(0)).getObjectVal());
//        String str2 = (String)(((ConstantNode)this.childs.get(2)).getObjectVal());
//        if (str1.equals(str2))        
//        {
//            return true;
//        }
//        return false;
//    }    
    
    
    TreeNode getBodyChild() {
        return bodyNode;
    }
       
    @Override
    public Object execute() {
        //if(IsValidLabelStringWithGotoString())
        //{
            //this.childs.get(0).execute();
        //}
        return null;
    }
}

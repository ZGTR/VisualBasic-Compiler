package JJTree;

public class GotoNode extends TreeNode {
    
    private String gotoString;
    
    public GotoNode(TreeNode baseNode)
    {
        this.baseNode = baseNode;        
    }   
    
    public String getGotoString() {
        return gotoString;
    }
    
    public void setGotoString(String gotoString) {
        this.gotoString = gotoString;
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
    
    @Override
    public Object execute() {
    //    if(IsValidLabelStringWithGotoString())
    //    {
        LabelNode jumpedToLabel = LabelsController.LocateMatchedLabel(this);
        jumpedToLabel.getBodyChild().execute();
        this.baseNode.setCanExecute(false, this.gotoString);
        //jumpedToLabel.setCanExecute(false);
    //    }
        return null;
    }
}

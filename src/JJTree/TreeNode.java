package JJTree;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class TreeNode {    
    protected ArrayList<TreeNode> childs = new ArrayList<TreeNode>();
    protected HashMap<String,Object> vars = new HashMap<String,Object>();
    public TreeNode baseNode;
    public boolean isBase = false;
    public static HashMap<String, TreeNode> Functions = new HashMap<String,TreeNode>();
    public static HashMap<String, TreeNode> Subs = new HashMap<String,TreeNode>();

    private boolean canExecute = true;

    public boolean isCanExecute() {
        return canExecute;
    }

    public void setCanExecute(boolean canExecute, String gotoStr) {
        this.canExecute = canExecute;
        if (canExecute == false && isBase == false)
        {
            if (this instanceof BodyNode)
            {
                if (!LabelsController.IsOccuredInLabelList(gotoStr, ((BodyNode)this).getLabelList()))
                {
                    this.baseNode.setCanExecute(false, gotoStr);
                }
            }        
            else
            {
                this.baseNode.setCanExecute(false, gotoStr);
            }
        }
    }    
    
    public abstract Object execute();
        
    public void addChild(TreeNode node)
    {
        childs.add(node);
    }
}

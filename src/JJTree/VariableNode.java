package JJTree;
import MattusCompilerPackage.*;

public class VariableNode extends TreeNode {
    private String var;

    public VariableNode(String var, TreeNode baseNode)
    {
        this.var = var.toLowerCase();
        this.baseNode = baseNode;
    }

    public String getVar() {
        return var;
    }

    @Override
    public Object execute() 
    {
        if (HashmapController.IsOccuredInBaseHashmaps(this, this.var))
        {
            return HashmapController.LocateVariableValueInBaseHashmaps(this, this.var);
        }
        else
        {
            System.err.println("Varibale not found, zero value added");
            HashmapController.AddToBaseHashMap(this, this.var, new Double(0));
            return new Double(0.0);
        }    
    }
}
package JJTree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class InputNode extends TreeNode {
    
    private ArrayList<String> variables = new ArrayList<String>();

    public InputNode(ArrayList<String> variables, TreeNode baseNode)
    {
        this.baseNode = baseNode;
        this.variables = variables;

    }
    
//    public void setVar(String var)
//    {
//        this.var = var;
//    }

    @Override
    public Object execute() 
    {
        Scanner s = new Scanner(System.in);
        for(String var : variables)
        {
            String val = s.next();
            HashmapController.AddToBaseHashMap(baseNode, var, val);
        }
        return null;
    }
}

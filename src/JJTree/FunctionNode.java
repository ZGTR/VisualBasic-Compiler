package JJTree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Hasan
 */
public class FunctionNode extends TreeNode implements IHashmapable
{
    private String functionName, returnType = null;
    public ArrayList<Parameter> parameters = new ArrayList<Parameter>();


    public FunctionNode(String functionName,ArrayList<Parameter> parameters,String returnType, TreeNode baseNode)
    {
        this.baseNode = baseNode;
        this.functionName = functionName;
        this.parameters = parameters;
        this.returnType = returnType;
        //addFunctionNameToHash();
        addParametersToHash();
        //Functions.put(functionName, this);
    }

    public FunctionNode(FunctionNode functionNode)
    {
        this.baseNode = functionNode.baseNode;
        this.functionName = functionNode.functionName;
        this.returnType = functionNode.returnType;
        //parameters = new ArrayList<Parameter>(subNode.parameters.size());
        //Parameter p = new Parameter(null, null);
        for(int i = 0; i<functionNode.parameters.size();i++){
          parameters.add(functionNode.parameters.get(i));
        }
        for(int i = 0;i<functionNode.childs.size();i++)
            childs.add(functionNode.childs.get(i));
        vars = new HashMap<String,Object>();
        childs.get(0).vars = new HashMap<String,Object>(functionNode.childs.get(0).vars);
    }

//    public void setBase(TreeNode baseNode)
//    {
//        this.baseNode = baseNode;
//    }

    private void addParametersToHash()
    {
        for(Parameter p:parameters)
        {
            if(p.getType() != null)
            {
                String typeStr = p.getType();

                if (typeStr.equals("INTEGER"))
                {
                    HashmapController.AddToBaseHashMap(this, p.getName(), new Integer(0));
                    p.setValue(0);
                    return;
                }
                if (typeStr.equals("DOUBLE"))
                {
                    HashmapController.AddToBaseHashMap(this, p.getName(), new Double(0));
                    p.setValue(0.0);
                    return;
                }
                if (typeStr.equals("STRING"))
                {
                    HashmapController.AddToBaseHashMap(this, p.getName(), new String());
                    p.setValue("");
                    return;
                }
            }
            else
            {
                
                HashmapController.AddToBaseHashMap(this, p.getName(), new Double(0));
            }
        }
    }

    private void addFunctionNameToHash()
    {
        if (returnType != null)
        {
            if (returnType.equals("INTEGER"))
            {
                HashmapController.AddToBaseHashMap(this.childs.get(0), functionName, new Integer(0));
                return;
            }
            if (returnType.equals("DOUBLE"))
            {
                HashmapController.AddToBaseHashMap(this.childs.get(0),functionName, new Double(0));
                return;
            }
            if (returnType.equals("STRING"))
            {
                HashmapController.AddToBaseHashMap(this.childs.get(0), functionName, new String());
                return;
            }
        }
        else
        {
            HashmapController.AddToBaseHashMap(this.childs.get(0),functionName, new Double(0));
            return;
        }
    }

    @Override
    public Object execute()
    {
        childs.get(0).baseNode = this;
        addFunctionNameToHash();
        for(Parameter param : parameters)
            HashmapController.AddToBaseHashMap(this.childs.get(0), param.getName(), param.getValue());
        for(TreeNode t : childs)
            t.execute();
        return HashmapController.LocateVariableValueInBaseHashmaps(this.childs.get(0), functionName);
        //return null;
    }

}

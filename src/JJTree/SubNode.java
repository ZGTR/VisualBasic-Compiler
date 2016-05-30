/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JJTree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Hasan
 */
public class SubNode extends TreeNode 
{

    private String subName = null;
    public ArrayList<Parameter> parameters = new ArrayList<Parameter>();

    public SubNode(String subName,ArrayList<Parameter> parameters)
    {
        this.baseNode = baseNode;
        this.subName = subName;
        this.parameters = parameters;
    }

    public SubNode(SubNode subNode)
    {
        this.baseNode = subNode.baseNode;
        this.subName = subNode.subName;
        //parameters = new ArrayList<Parameter>(subNode.parameters.size());
        //Parameter p = new Parameter(null, null);
        for(int i = 0; i<subNode.parameters.size();i++){
          parameters.add(subNode.parameters.get(i));
        }
        for(int i = 0;i<subNode.childs.size();i++)
            childs.add(subNode.childs.get(i));
        vars = new HashMap<String,Object>();
        childs.get(0).vars = new HashMap<String,Object>(subNode.childs.get(0).vars);
    }

    @Override
    public Object execute()
    {
        //addFunctionNameToHash();
        childs.get(0).baseNode = this;
        for(Parameter param : parameters)
            HashmapController.AddToBaseHashMap(this.childs.get(0), param.getName(), param.getValue());
        for(TreeNode t : childs)
            t.execute();
        //return HashmapController.LocateVariableValueInBaseHashmaps(this.childs.get(0), functionName);
        return null;
    }

}

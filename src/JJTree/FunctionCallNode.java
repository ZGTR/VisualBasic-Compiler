/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JJTree;

import java.lang.String;
import java.util.ArrayList;

/**
 *
 * @author Hasan
 */
public class FunctionCallNode extends TreeNode implements IHashmapable
{

    //private ArrayList<Object> paramValues = new ArrayList<Object>();
    //private ArrayList<String> paramValuesAsStrings = new ArrayList<String>();
    private ArrayList<TreeNode> exprNodes = new ArrayList<TreeNode>();
    private String functionName;

    public FunctionCallNode(String functionName, ArrayList<TreeNode> exprNodes, TreeNode baseNode)
    {
        this.baseNode = baseNode;
        this.functionName = functionName;
        this.exprNodes = exprNodes;
        //convertParamValues();
        
    }

//    private void convertParamValues()
//    {
//        int i = 0;
//        for(String param:paramValuesAsStrings)
//        {
//            try
//            {
//                int x = Integer.parseInt(param);
//                paramValues.set(i, x);
//
//            }
//            catch(Exception ex)
//            {
//                try
//                {
//                    double x = Double.parseDouble(param);
//                    paramValues.set(i, x);
//                }
//                catch(Exception ex1)
//                {
//                    paramValues.set(i, param);
//                }
//            }
//            finally{i++;}
//        }
//    }

    @Override
    public Object execute()
    {
        if (Functions.containsKey(functionName))
        {
            FunctionNode fNode = new FunctionNode((FunctionNode)Functions.get(functionName));
            fNode.baseNode = baseNode;
            for(int i = 0; i < fNode.parameters.size(); i++)
                fNode.parameters.get(i).setValue(exprNodes.get(i).execute());
            //((FunctionNode)fNode).setBase(baseNode);
            return fNode.execute();
        }
        else
        {
            try
            {
                Object o = HashmapController.LocateVariableValueInBaseHashmaps(this, functionName);
                ArrayList<Double> arr = (ArrayList<Double>)o;
                Double x = (Double)exprNodes.get(0).execute();
                return arr.get((Integer.parseInt(Double.toString(x))));
            }
            catch(Exception ex)
            {
            }
        }
        System.err.println("Function is not declared");


        for(TreeNode t : childs)
            t.execute();
        return null;
    }

}

package JJTree;

import java.util.ArrayList;
import java.util.List;
import MattusCompilerPackage.*;
import java.util.Iterator;
import java.util.Map;


public class TypeController {
    public static ArrayList<TypeUDNodeStructure> TypeUDList = new ArrayList<TypeUDNodeStructure>();
    public static Object GetProperType(Object obj)
    {
        Object objToReturn = obj;
        try
        {
            objToReturn = (Integer)obj;
            return objToReturn;
        }
        catch (Exception e1)
        {
            try
            {
                objToReturn = (Double)obj;
                return objToReturn;
            }
            catch (Exception e2)
            {
                try
                {
                    objToReturn = (String)obj;
                    return objToReturn;
                }
                catch (Exception e3)
                {
                    objToReturn = GetUserDefineType(obj);
                    if (objToReturn != null)
                    {
                        return objToReturn;
                    }
                    return null;
                }
            }
        }
    }
    
    public static Object GetProperTypeInitialValue(Object variable)
    {
        Object correspondingType = GetProperType(variable);
        if (correspondingType != null)
        {
            if (correspondingType instanceof Integer)
            {
                return new Integer(0);
            }
            if (correspondingType instanceof Double)
            {
                return new Double(0);
            }
            if (correspondingType instanceof String)
            {
                return "";
            }
        }
        return null;
    }

    private static Object GetUserDefineType(Object obj) {
        for(TypeUDNodeStructure currType : TypeUDList)
        {
            if (obj.toString().equals(currType.getStringOfTypeNode()))
            {
                return currType;
            }
        }
        return null;
    }
    
    public static void FixTypesInJJTree(TreeNode node)
    {
        for(TreeNode childNode : node.childs)
        {
            if (childNode instanceof BodyNode)
            {
                for(TypeUDNodeStructure type : TypeController.TypeUDList)
                { 
                    //if (childNode.vars.containsValue(type.getStringOfTypeNode()))
                    Iterator it = childNode.vars.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pairs = (Map.Entry)it.next();
                        //System.out.println(pairs.getKey() + " = " + pairs.getValue());
                        if (pairs.getValue().equals(type.getStringOfTypeNode()))
                        {
                            pairs.setValue(new TypeUDNodeStructure(type));
                        }
                        it.remove(); 
                    }                    
                }
            }
        }
    }
    
    public static void FixTypesInTypesList()
    {
        for(TypeUDNodeStructure type : TypeController.TypeUDList)
        { 
            //if (childNode.vars.containsValue(type.getStringOfTypeNode()))
            Iterator it = type.gethashmapOfFields().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry)it.next();
                //System.out.println(pairs.getKey() + " = " + pairs.getValue());
                if (pairs.getValue().equals(type.getStringOfTypeNode()))
                {
                    pairs.setValue(new TypeUDNodeStructure(type));
                }
                it.remove(); 
            }                    
        }
    }
}

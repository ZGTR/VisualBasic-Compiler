package JJTree;

import java.util.HashMap;

public class HashmapController {    
    public static TreeNode LocateNearestIHashmapableBaseNode(TreeNode node) {
        TreeNode currentBaseNode = node;
        if (currentBaseNode instanceof IHashmapable)
        {
            currentBaseNode = currentBaseNode.baseNode;            
        }
        while (!(currentBaseNode instanceof IHashmapable))
        {
            currentBaseNode = currentBaseNode.baseNode;            
        }
        return currentBaseNode;
    }
    
    private static TreeNode LocateMatchedStringIHashmapableBaseNode(TreeNode node, String varString) {
        TreeNode currentBaseNode = node;
        if (currentBaseNode instanceof IHashmapable && currentBaseNode.vars.containsKey(varString))
        {
            currentBaseNode = currentBaseNode;//.baseNode;            
        }
        else
        {
            while (true)
            {
                while (!(currentBaseNode instanceof IHashmapable))
                {
                    currentBaseNode = currentBaseNode.baseNode; 
                }
                if (currentBaseNode.vars.containsKey(varString))
                {
                    return currentBaseNode;
                }
                currentBaseNode = currentBaseNode.baseNode;                     
            }
        }
        return currentBaseNode;
    }
    
    public static void AddToBaseHashMap(TreeNode node, String varString, Object varValue) {
        if(!IsAddToHashmapHandledTypeUD(node,varString,varValue))
        {
            if (IsOccuredInBaseHashmaps(node, varString))
            {
                TreeNode matchedIHashmapable = LocateMatchedStringIHashmapableBaseNode(node, varString);
                matchedIHashmapable.vars.put(varString, varValue);
            }
            else
            {
                // not found in any base nodes, so added it to nearest IHashmapable node
                TreeNode locatedIHashBaseNode = LocateNearestIHashmapableBaseNode(node);
                if (node instanceof IHashmapable)
                {
                    locatedIHashBaseNode = node;
                }
                else
                {
                    locatedIHashBaseNode = LocateNearestIHashmapableBaseNode(node);
                }
                locatedIHashBaseNode.vars.put(varString, varValue);            
            }
        }
    }

   public static boolean IsOccuredInBaseHashmaps(TreeNode node, String varString) {
        boolean varIsFound = false;
        TreeNode currentIHashBaseNode = node;
        while (!varIsFound)
        {
            if (currentIHashBaseNode.isBase == true)
            {
                if (currentIHashBaseNode.vars.containsKey(varString) && currentIHashBaseNode instanceof IHashmapable)
                {
                    varIsFound = true;
                }
                // Manipulating base node of JJTree, so break either way! if var is found or not!
                break;
            }
            else
            {
                if (currentIHashBaseNode.vars.containsKey(varString) && currentIHashBaseNode instanceof IHashmapable)
                {
                    varIsFound = true;
                    break;
                }
                currentIHashBaseNode = LocateNearestIHashmapableBaseNode(currentIHashBaseNode);            
            }
        }
        if (varIsFound)
        {
            return true;
        }
        return false;
    }
    
    public static Object LocateVariableValueInBaseHashmaps(TreeNode node, String varString) {
        boolean varIsFound = false;
        Object varValue = 0;
        TreeNode currentIHashBaseNode = node;
        //TreeNode matchedIHashmapable = LocateMatchedStringIHashmapableBaseNode(node, varString);
        Object obToReturn = IsLocatedInHashmapHandledTypeUD(node, varString);
        if (obToReturn == null)
        {
            while (!varIsFound)
            {
                if (currentIHashBaseNode.isBase == true)
                {
                    if (currentIHashBaseNode.vars.containsKey(varString) && currentIHashBaseNode instanceof IHashmapable)
                    {
                        varIsFound = true;       
                        varValue = currentIHashBaseNode.vars.get(varString);
                    }
                    // Manipulating base node of JJTree, so break either way! if var is found or not!
                    break;
                }
                else
                {
                    if (currentIHashBaseNode.vars.containsKey(varString) && currentIHashBaseNode instanceof IHashmapable)
                    {
                        varIsFound = true;
                        varValue = currentIHashBaseNode.vars.get(varString);
                        break;
                    }
                    currentIHashBaseNode = LocateNearestIHashmapableBaseNode(currentIHashBaseNode);            
                }
            }
            if (varIsFound)
            {
                return varValue;
            }
        }
        else
        {
            return obToReturn;
        }
        return null;
    }

    private static boolean IsAddToHashmapHandledTypeUD(TreeNode node, String varString, Object varValue) {
        try
        {
            int desiredDepthOfField = varString.split(".").length;
            String strVar = varString.split(".")[0];
            String strField = varString.split(".")[1];                        
            int cOuter = 1;
            if (IsOccuredInBaseHashmaps(node, strVar))
            {
                TreeNode matchedIHashmapable = LocateMatchedStringIHashmapableBaseNode(node, strVar);
                HashMap<String, Object> typeHash =  (HashMap<String, Object>)matchedIHashmapable.vars.get(strVar);
                while(cOuter < desiredDepthOfField)
                {
                    typeHash = (HashMap<String, Object>)typeHash.get(varString.split(".")[cOuter]);
                    cOuter++;
                }
                String currentFieldStr = varString.split(".")[cOuter];
                typeHash.put(currentFieldStr, varValue);
                return true;
            }                
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    private static Object IsLocatedInHashmapHandledTypeUD(TreeNode node, String varString) {
        try
        {
            int desiredDepthOfField = varString.split(".").length;
            String strVar = varString.split(".")[0];
            String strField = varString.split(".")[1];                        
            int cOuter = 1;
            if (IsOccuredInBaseHashmaps(node, strVar))
            {
                TreeNode matchedIHashmapable = LocateMatchedStringIHashmapableBaseNode(node, strVar);
                HashMap<String, Object> typeHash =  (HashMap<String, Object>)matchedIHashmapable.vars.get(strVar);
                while(cOuter < desiredDepthOfField)
                {
                    typeHash = (HashMap<String, Object>)typeHash.get(varString.split(".")[cOuter]);
                    cOuter++;
                }
                String currentFieldStr = varString.split(".")[cOuter];
                return typeHash.get(currentFieldStr);   
            }
            return null;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    static void AddToProgramHashMap(AssignNode aThis, String var, Object initialVal) {
        TreeNode currentNode = aThis;
        while(currentNode.isBase == false)
        {
            currentNode = currentNode.baseNode;
        }
        currentNode.vars.put(var, initialVal);
    }   
}


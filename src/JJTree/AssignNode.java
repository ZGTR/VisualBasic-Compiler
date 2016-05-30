package JJTree;
import MattusCompilerPackage.Token;
import java.util.HashMap;

public class AssignNode extends TreeNode {
    private String var;
    private boolean isShared = false;
    private Object type;
    
    public String getVar() {
        return var;
    }   
  
    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public AssignNode(String var, TreeNode baseNode)
    {
        this.var = var;
        this.baseNode = baseNode;
        this.type = new Integer(0);
    }

    public AssignNode(java.lang.String image, Token tType, Token tShared, TreeNode baseNode) {
        this.var = image.toLowerCase();
        this.baseNode = baseNode;
        SetType(tType);
        SetShared(tShared);
    }

    private void SetShared(Token shared) {
        try
        {
            if (shared.image != null)
            {
                this.isShared = true;
            }
        }
        catch (Exception ex)
        {

        }
    }

    private void SetType(Token type) {
        if (type != null)
        {
            String typeStr = type.image;
            if (typeStr.equals("INTEGER"))
            {
                this.setType(new Integer(0));
                return;
            }
            if (typeStr.equals("DOUBLE"))
            {
                this.setType(new Double(0));
                return;
            }
            if (typeStr.equals("STRING"))
            {
                this.setType(new String());
                return;
            }
            // Type - structrue manipulating - set type str to type name for temporal time, till we parse the whole code
            this.setType(typeStr);
            return;
        }
        else
        {
            this.setType(new Double(0));
        }
    }       
    
    @Override
    public Object execute() {
        try
        {
            if (this.isShared)
            {
                Object varValue = childs.get(0).execute();
                HashmapController.AddToProgramHashMap(this, getVar(), varValue);
                return varValue;
            }
            else
            {
                Object varValue = childs.get(0).execute();
                HashmapController.AddToBaseHashMap(this, getVar(), varValue);
                return varValue;
            }
        }
        catch (Exception ex)
        {
            // DIM Statement
            Object initialVal = TypeController.GetProperTypeInitialValue(this.getType());
            if (initialVal != null)
            {
                if (this.isShared)
                {
                    HashmapController.AddToProgramHashMap(this, getVar(), initialVal);
                }
                else
                {
                    HashmapController.AddToBaseHashMap(this, getVar(), initialVal);
                }
            }
            else
            {
                if (this.isShared)
                {
                    HashmapController.AddToProgramHashMap(this, getVar(), -1);
                }
                else
                {
                    HashmapController.AddToBaseHashMap(this, getVar(), -1);
                }
            }
            return 0;
        }
    }

}

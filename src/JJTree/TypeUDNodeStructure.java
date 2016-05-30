package JJTree;
import java.util.ArrayList;
import java.util.HashMap;

public class TypeUDNodeStructure{
    private HashMap<String,Object> hashmapOfFields = new HashMap<String,Object>();
    private String stringOfTypeNode;
    private Object typeOfTypeNode;
    
    public TypeUDNodeStructure(TypeUDNodeStructure type)
    {
        this.hashmapOfFields = new HashMap<String, Object>(type.hashmapOfFields);
        this.stringOfTypeNode = type.stringOfTypeNode;
        this.typeOfTypeNode = type.typeOfTypeNode;
    }
    
    public TypeUDNodeStructure()
    {

    }
   
    public void addFieldToHashmap(String strField, Object obField) {
        hashmapOfFields.put(strField, obField);
    }
    
    public void addFieldToHashmap(AssignNode node) {
        hashmapOfFields.put(node.getVar(), node.getType());
    }
    
    public String getStringOfTypeNode() {
        return stringOfTypeNode;
    }

    public void setStringOfTypeNode(String stringOfTypeNode) {
        this.stringOfTypeNode = stringOfTypeNode;
    }
    
    public Object getTypeOfTypeNode() {
        return typeOfTypeNode;
    }

    public void setTypeOfTypeNode(Object typeOfTypeNode) {
        this.typeOfTypeNode = typeOfTypeNode;
    }
    
    public HashMap<String,Object> gethashmapOfFields() {
        return hashmapOfFields;
    }

    public void sethashmapOfFields(HashMap<String,Object> hashmapOfFields) {
        this.hashmapOfFields = hashmapOfFields;
    }
}

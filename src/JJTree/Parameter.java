/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JJTree;

/**
 *
 * @author Hasan
 */
public class Parameter
{
    private String name, type = null;
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Parameter(String name, String type)
    {
        this.name= name;
        this.type = type;
    }
    public void CopyTo(Parameter p)
    {
        p = new Parameter(name, type);
        p.value = value;
    }

}

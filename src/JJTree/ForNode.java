package JJTree;
import java.util.ArrayList;

public class ForNode extends TreeNode {

    private boolean stepOccured;
    private boolean varNextOccured;
    private boolean canExecuteFor = true;
    BodyNode bodyNode;

    public ForNode(TreeNode baseNode)
    {
        this.baseNode = baseNode;
    }

    public boolean isStepOccured() {
        return stepOccured;
    }

    public void setStepOccured(boolean stepOccured) {
        this.stepOccured = stepOccured;
    }

    public boolean isVarNextOccured() {
        return varNextOccured;
    }

    public void setVarNextOccured(boolean varNextOccured) {
        this.varNextOccured = varNextOccured;
    }

    private boolean IsValidCounterStringWithNextString() {
        bodyNode = (BodyNode)this.childs.get(0);
        if (isVarNextOccured())
        {
            String originalCounterString = ((AssignNode)bodyNode.childs.get(0)).getVar();
            String varNextString = ((VariableNode)bodyNode.childs.get(bodyNode.childs.size()-1)).getVar();
            if (originalCounterString.equals(varNextString))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object execute() {

        bodyNode = (BodyNode)this.childs.get(0);
            if (!IsValidCounterStringWithNextString())
            {
                return null;
            }
            else
            {
                // Next Occured
                if (!(isStepOccured()))
                {
                    // step is 1, so increment by i++
                    double counterInitVal = (Double)(bodyNode.childs.get(0).execute());
                    double counterUpperLimit = (Double)(bodyNode.childs.get(1).execute());
                    double counter = counterInitVal;
                    boolean isFreeToGo = true;
                    while (counter <= counterUpperLimit && isFreeToGo)
                    {
                        BodyNode body = (BodyNode)bodyNode.childs.get(2);
                        for(TreeNode t : body.childs)
                        {
                            if (isCanExecute())
                            {
                                if(isCanExecuteFor())
                                {
                                     t.execute();
                                     counter += 1;
                                     HashmapController.AddToBaseHashMap(bodyNode.childs.get(0), ((AssignNode)bodyNode.childs.get(0)).getVar(), counter);
                                     //((AssignNode)childs.get(0)).childs.remove(0);
                                     //((AssignNode)childs.get(0)).addChild(new ConstantNode(counter, (AssignNode)childs.get(0)));
                                     //childs.get(0).execute();
                                }
                                else
                                {
                                    isFreeToGo = false;
                                }
                            }
                        }
                    }
                }
                else
                {
                    // Step Occured
                    double counterInitVal = (Double)(bodyNode.childs.get(0).execute());
                    double counterUpperLimit = (Double)(bodyNode.childs.get(1).execute());
                    double step = (Double)(bodyNode.childs.get(2).execute());
                    double counter = counterInitVal;
                    boolean isFreeToGo = true;
                   while (counter <= counterUpperLimit && isFreeToGo)
                    {
                        BodyNode body = (BodyNode)bodyNode.childs.get(3);
                        for(TreeNode t : body.childs)
                        {
                            if (isCanExecute())
                            {
                                if(isCanExecuteFor())
                                {
                                     t.execute();
                                     counter += step;
                                     HashmapController.AddToBaseHashMap(bodyNode.childs.get(0), ((AssignNode)bodyNode.childs.get(0)).getVar(), counter);
                                     //((AssignNode)childs.get(0)).childs.remove(0);
                                     //((AssignNode)childs.get(0)).addChild(new ConstantNode(counter, (AssignNode)childs.get(0)));
                                     //childs.get(0).execute();
                                }
                                else
                                {
                                    isFreeToGo = false;
                                }
                            }
                        }
                    }
                }
            }
        return null;
    }

    /**
     * @return the canExecuteFor
     */
    public boolean isCanExecuteFor() {
        return canExecuteFor;
    }

    /**
     * @param canExecuteFor the canExecuteFor to set
     */
    public void setCanExecuteFor(boolean canExecuteFor) {
        this.canExecuteFor = canExecuteFor;
    }
}

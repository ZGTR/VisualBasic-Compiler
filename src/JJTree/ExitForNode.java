package JJTree;


public class ExitForNode extends TreeNode {

    public ExitForNode(TreeNode baseNode)
    {
        this.baseNode = baseNode;
    }

    private ForNode FindNearestForNode(ExitForNode exitNode) {
        TreeNode currentBaseNode = this.baseNode;
        while (true)
        {
            if (currentBaseNode instanceof ForNode)
            {
                return (ForNode)currentBaseNode;
            }
            else
            {
                currentBaseNode = currentBaseNode.baseNode;
            }
        }
    }

//    private void HallucinateGoto(ForNode nearestForNode)
//    {
//        // Set new label's baseNode to the parent of the currently manipulated for
//        LabelNode labelNode = new LabelNode(nearestForNode.baseNode);
//        nearestForNode.baseNode.addChild(labelNode);
//        // Set the string of the new label String
//        String labelString = LabelsController.GetNextPrivilegedLabelString();
//        labelNode.setLabelString(labelString);
//        TreeNode nextNode = LabelsController.LocateNextProperExecutableNode(this.baseNode);
//        if (nextNode != null)
//        {
//            labelNode.setBodyNode(nextNode);
//
//            GotoNode gNode = new GotoNode(this);
//            gNode.setGotoString(labelString);
//
//            gNode.execute();
//        }
//        else
//        {
//            this.baseNode.setCanExecute(false, labelString);
//        }
//    }

    @Override
    public Object execute() {
        ForNode nearestForNode = FindNearestForNode(this);
        //BodyNode bodyNode = (BodyNode)nearestForNode.baseNode;
        nearestForNode.setCanExecuteFor(false);
        //bodyNode.execute(nearestForNode);

        //nearestForNode.setCanExecute(false, "");
        return null;
    }
}

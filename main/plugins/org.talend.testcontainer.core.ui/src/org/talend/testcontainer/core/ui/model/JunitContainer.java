package org.talend.testcontainer.core.ui.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainer;
import org.talend.designer.core.ui.editor.nodes.Node;

public class JunitContainer extends NodeContainer {

    public static final String UPDATE_JUNIT_CONTENT = "UPDATE_JUNIT_CONTENT"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_DATA = "UPDATE_JUNIT_DATA"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_CONNECTIONS = "UPDATE_JUNIT_CONNECTIONS"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_TITLE_COLOR = "UPDATE_JUNIT_TITLE_COLOR"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_DISPLAY = "UPDATE_JUNIT_DISPLAY"; //$NON-NLS-1$

    private List<NodeContainer> nodeContainers = new ArrayList<NodeContainer>();

    private IProcess2 process;

    private Node node;

    protected List<IElement> jobletElements = new ArrayList<IElement>();

    List<Node> testNodes = new ArrayList<Node>();

    public static final int EXPEND_SIZE = 10;

    public JunitContainer(Node node) {
        super(node);
        this.node = node;
    }

    /**
     * Getter for process.
     * 
     * @return the process
     */
    public IProcess2 getProcess() {
        if (process == null) {
            IProcess iPro = node.getComponent().getProcess();
            if (iPro instanceof IProcess2) {
                return (IProcess2) iPro;
            }
        }
        return this.process;
    }

    /**
     * DOC hwang Comment method "getJobletContainerRectangle".
     * 
     * @return
     */
    public Rectangle getJobletContainerRectangle() {
        Rectangle totalRectangle = null;
        if (nodeContainers.size() > 0) {
            for (NodeContainer container : nodeContainers) {
                Rectangle curRect = container.getNodeContainerRectangle();
                if (totalRectangle == null) {
                    totalRectangle = curRect.getCopy();
                } else {
                    totalRectangle = totalRectangle.getUnion(curRect);
                }
            }
            totalRectangle.y = totalRectangle.y - EXPEND_SIZE * 2;
            totalRectangle.height = totalRectangle.height + EXPEND_SIZE * 3;

        } else if (node != null) {
            NodeContainer container = node.getNodeContainer();
            Rectangle curRect = container.getNodeContainerRectangle();
            if (totalRectangle == null) {
                totalRectangle = curRect.getCopy();
            } else {
                totalRectangle = totalRectangle.getUnion(curRect);
            }
        }

        if (totalRectangle == null) {
            return null;
        }
        return totalRectangle.getCopy();
    }

    public boolean isReadonly() {
        return true;
    }

    @Override
    public void setPropertyValue(String id, Object value) {
        super.setPropertyValue(id, value);
    }

    public void refreshJobletNodes() {
        this.nodeContainers.clear();
        this.jobletElements.clear();
        for (INode inode : this.testNodes) {
            if ((inode instanceof Node)) {
                Node temNode = (Node) inode;
                temNode.setReadOnly(true);
                this.nodeContainers.add(temNode.getNodeContainer());

                this.jobletElements.add(temNode);
                this.jobletElements.add(temNode.getNodeLabel());
                this.jobletElements.add(temNode.getNodeError());
                this.jobletElements.add(temNode.getNodeProgressBar());

            }
        }
    }

    // public void updateSubjobContainer() {
    // fireStructureChange(UPDATE_JUNIT_CONTENT, this);
    // }

    // public void updateSubjobDisplay() {
    // fireStructureChange(UPDATE_JUNIT_DISPLAY, this);
    // }

    @Override
    public List getElements() {
        if (this.jobletElements.size() <= 0) {
            return super.getElements();
        } else {
            return this.jobletElements;
        }

    }

    public List<NodeContainer> getNodeContainers() {
        return this.nodeContainers;
    }

    /**
     * Getter for testNodes.
     * 
     * @return the testNodes
     */
    public List<Node> getTestNodes() {
        return this.testNodes;
    }

    /**
     * Sets the testNodes.
     * 
     * @param testNodes the testNodes to set
     */
    public void setTestNodes(List<Node> testNodes) {
        this.testNodes = testNodes;
    }

}

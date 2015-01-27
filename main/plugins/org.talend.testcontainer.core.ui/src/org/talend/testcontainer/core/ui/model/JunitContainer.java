package org.talend.testcontainer.core.ui.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainer;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.testcontainer.core.ui.models.TestContainerInputOutputComponent;

public class JunitContainer extends NodeContainer {

    public static final String UPDATE_JUNIT_CONTENT = "UPDATE_JUNIT_CONTENT"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_DATA = "UPDATE_JUNIT_DATA"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_CONNECTIONS = "UPDATE_JUNIT_CONNECTIONS"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_TITLE_COLOR = "UPDATE_JUNIT_TITLE_COLOR"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_DISPLAY = "UPDATE_JUNIT_DISPLAY"; //$NON-NLS-1$

    private List<NodeContainer> nodeContainers = new ArrayList<NodeContainer>();

    private IProcess2 process;

    private Node node;

    protected List<IElement> junittElements = new ArrayList<IElement>();

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
     * DOC hwang Comment method "getJunitContainerRectangle".
     * 
     * @return
     */
    public Rectangle getJunitContainerRectangle() {
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
            totalRectangle.height = totalRectangle.height + EXPEND_SIZE * 4;

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

    public void refreshJunitNodes() {
        this.nodeContainers.clear();
        this.junittElements.clear();
        List<Node> nodeList = new ArrayList<Node>();
        nodeList.addAll(getInputComponentNodes(this.node));
        nodeList.addAll(getOutputComponentNodes(this.node));
        nodeList.addAll(testNodes);
        for (Node temNode : nodeList) {
            this.nodeContainers.add(temNode.getNodeContainer());
            this.junittElements.add(temNode);
            this.junittElements.add(temNode.getNodeLabel());
            this.junittElements.add(temNode.getNodeError());
            this.junittElements.add(temNode.getNodeProgressBar());
        }
        for (Node temNode : testNodes) {
            temNode.setReadOnly(true);
        }
    }

    private List<Node> getInputComponentNodes(Node inNode) {
        List<Node> nodeList = new ArrayList<Node>();
        for (IConnection conn : inNode.getIncomingConnections()) {
            if (conn.getSource().getComponent() instanceof TestContainerInputOutputComponent) {
                nodeList.add((Node) conn.getSource());
            } else {
                nodeList.addAll(getInputComponentNodes((Node) conn.getSource()));
            }
        }
        return nodeList;
    }

    private List<Node> getOutputComponentNodes(Node outNode) {
        List<Node> nodeList = new ArrayList<Node>();
        for (IConnection conn : outNode.getOutgoingConnections()) {
            if (conn.getTarget().getComponent() instanceof TestContainerInputOutputComponent) {
                nodeList.add((Node) conn.getTarget());
            } else {
                nodeList.addAll(getOutputComponentNodes((Node) conn.getTarget()));
            }
        }
        return nodeList;
    }

    @Override
    public List getElements() {
        if (this.junittElements.size() <= 0) {
            return super.getElements();
        } else {
            return this.junittElements;
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

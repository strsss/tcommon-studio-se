package org.talend.testcontainer.core.ui.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.designer.core.DesignerPlugin;
import org.talend.designer.core.model.components.EParameterName;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainer;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.preferences.TalendDesignerPrefConstants;
import org.talend.testcontainer.core.ui.models.AbstractTestContainer;

public class JunitContainer extends NodeContainer {

    public static final String UPDATE_JUNIT_CONTENT = "UPDATE_JUNIT_CONTENT"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_DATA = "UPDATE_JUNIT_DATA"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_CONNECTIONS = "UPDATE_JUNIT_CONNECTIONS"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_TITLE_COLOR = "UPDATE_JUNIT_TITLE_COLOR"; //$NON-NLS-1$

    public static final String UPDATE_JUNIT_DISPLAY = "UPDATE_JUNIT_DISPLAY"; //$NON-NLS-1$

    protected List<Node> nodes = new ArrayList<Node>();

    private List<NodeContainer> nodeContainers = new ArrayList<NodeContainer>();

    private IProcess2 process;

    private Node node;

    private Rectangle jobletRectangle;

    private boolean update = false;

    private boolean needchangeLock = true;

    protected List<IElement> jobletElements = new ArrayList<IElement>();

    List<Node> testNodes = null;

    public static final int EXPEND_SIZE = 10;

    public JunitContainer(Node node) {
        super(node);
        this.node = node;
    }

    public JunitContainer(List<Node> testNodes) {
        super(testNodes.get(0));
        this.node = testNodes.get(0);
        this.testNodes = testNodes;
        updateJobletNodes(true);
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

    public Rectangle getJobletUnion(Rectangle jobletNodeRec, Rectangle rect) {
        Rectangle union = new Rectangle(jobletNodeRec.x, jobletNodeRec.y, 0, 0);
        union.width = Math.max(jobletNodeRec.x + jobletNodeRec.width, rect.x + rect.width) - union.x;
        union.height = Math.max(jobletNodeRec.y + jobletNodeRec.height, rect.y + rect.height) - union.y;
        return union;
    }

    /**
     * DOC hwang Comment method "getJobletContainerRectangle".
     * 
     * @return
     */
    public Rectangle getJobletContainerRectangle() {
        Rectangle totalRectangle = null;

        AbstractTestContainer testProcess = (AbstractTestContainer) this.getNode().getProcess();

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

        jobletRectangle = totalRectangle.getCopy();
        return totalRectangle;
    }

    public boolean isReadonly() {
        return true;
    }

    @SuppressWarnings("unchecked")
    public Node getJobletStartNode() {
        if (getProcess() != null) {
            if (getProcess().getSubjobContainers().size() > 0) {
                String subjobStartUniqueName = (String) getProcess().getSubjobContainers().get(0)
                        .getPropertyValue(EParameterName.UNIQUE_NAME.getName());
                if (getProcess() != null && (List<Node>) getProcess().getGraphicalNodes() != null) {
                    for (Node node : (List<Node>) getProcess().getGraphicalNodes()) {
                        if (node.getUniqueName() != null && node.getUniqueName().equals(subjobStartUniqueName)) {
                            return node;
                        }
                    }
                }
            }
        } else if (node != null) {
            return node;
        }

        return null;
    }

    @Override
    public void setPropertyValue(String id, Object value) {
        super.setPropertyValue(id, value);
    }

    public void updateJobletNodes(boolean update) {
        refreshJobletNodes(update, isCollapsed());
        updateSubjobContainer();
    }

    public void refreshJobletNodes(boolean update, boolean coll) {
        AbstractTestContainer testProcess = (AbstractTestContainer) this.getNode().getProcess();
        nodeContainers.clear();
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

    private void transferLocation(boolean update) {
        this.update = update;
        if (update) {
            // do nothing
        }
        if (this.isCollapsed() == true) {
            return;
        }
        if (this.nodeContainers.size() <= 0) {
            return;
        }
        Point oragPoint = this.getNode().getLocation();
        Node startNode = getJobletStartNode();
        if (startNode == null) {
            return;
        }
        Point stratPoint = startNode.getLocation();
        int withe_x = oragPoint.x - stratPoint.x;
        int hight_y = oragPoint.y - stratPoint.y;
        for (NodeContainer nodeCon : this.nodeContainers) {
            Node jobNode = nodeCon.getNode();
            if (jobNode.getJoblet_unique_name().equals(startNode.getUniqueName())) {
                jobNode.setLocation(oragPoint);
            } else {
                Point nodePoint = jobNode.getLocation();
                jobNode.setLocation(new Point(nodePoint.x + withe_x, nodePoint.y + hight_y));
            }
        }

    }

    public void updateSubjobContainer() {
        fireStructureChange(UPDATE_JUNIT_CONTENT, this);
    }

    public void updateSubjobDisplay() {
        if (!isDisplayed() && isCollapsed()) {
            setCollapsed(false);
        }
        fireStructureChange(UPDATE_JUNIT_DISPLAY, this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.process.ISubjobContainer#isDisplayed()
     */
    public boolean isDisplayed() {
        if (!DesignerPlugin.getDefault().getPreferenceStore().getBoolean(TalendDesignerPrefConstants.DISPLAY_SUBJOBS)) {
            return false;
        }
        return true;// (Boolean) getPropertyValue(EParameterName.SUBJOB_DISPLAYED.getName());
    }

    public boolean isUpdate() {
        return this.update;
    }

    /**
     * Sets the collapsed.
     * 
     * @param collapsed the collapsed to set
     */
    public void setCollapsed(boolean collapsed) {
        setPropertyValue(EParameterName.COLLAPSED.getName(), new Boolean(collapsed));
    }

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

    private Set<IConnection> getFlowInput(Set<IConnection> inputs) {
        Set<IConnection> finputs = new HashSet<IConnection>();
        Iterator<IConnection> ite = inputs.iterator();
        while (ite.hasNext()) {
            IConnection conn = ite.next();
            if (!conn.getConnectorName().equals("SUBJOB_OK") && !conn.getConnectorName().equals("SUBJOB_ERROR")
                    && !conn.getConnectorName().equals("COMPONENT_OK") && !conn.getConnectorName().equals("COMPONENT_ERROR")) {
                finputs.add(conn);
            }
        }
        return finputs;
    }

    public void setNeedchangeLock(boolean needchangeLock) {
        this.needchangeLock = needchangeLock;
    }
}

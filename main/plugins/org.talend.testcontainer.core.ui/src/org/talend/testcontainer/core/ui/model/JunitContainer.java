package org.talend.testcontainer.core.ui.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.core.ui.IJobletProviderService;
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
        // if (rect == null || rect.isEmpty())
        // return new Rectangle(this);
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
                // if (node.getNodeContainer() instanceof JunitContainer) {
                // String title = (String)
                // node.getNodeContainer().getPropertyValue(EParameterName.SUBJOB_TITLE.getName());
                // SimpleHtmlFigure titleFigure = new SimpleHtmlFigure();
                //                    titleFigure.setText("<b> " + title + "</b>"); //$NON-NLS-1$ //$NON-NLS-2$
                // Dimension preferedSize = titleFigure.getPreferredSize();
                // if (preferedSize.width > curRect.width) {
                // curRect.setSize(preferedSize.width, curRect.height);
                // }
                // }
                // if (container.getNode().isDesignSubjobStartNode()) {
                // totalRectangle = curRect.getCopy();
                // } else {
                if (totalRectangle == null) {
                    totalRectangle = curRect.getCopy();
                } else {
                    totalRectangle = totalRectangle.getUnion(curRect);
                }
                // }
            }
            // totalRectangle.setLocation(jobletNodeRec.getLocation());
            // totalRectangle.x = totalRectangle.x - EXPEND_SIZE * 2;
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

        if (id.equals(EParameterName.COLLAPSED.getName())) {
            // outputs.clear();
            for (IConnection conn : node.getOutgoingConnections()) {
                outputs.add(conn);
            }
            // inputs.clear();
            for (IConnection conn : node.getIncomingConnections()) {
                inputs.add(conn);
            }
            if (needchangeLock) {
                if (!((Boolean) value)) {
                } else {
                    IJobletProviderService service = (IJobletProviderService) GlobalServiceRegister.getDefault().getService(
                            IJobletProviderService.class);
                    if (service != null) {
                        service.unlockJoblet(node, true);
                    }
                }
            }
            needchangeLock = true;
            refreshJobletNodes(false, (Boolean) value);
            // if (!canCollapse()) {
            // Shell shell = Display.getCurrent().getActiveShell();
            //
            // MessageDialog dlg = new MessageDialog(new Shell(shell), "ERROR", null, "Please attach connection!",
            // MessageDialog.QUESTION, new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
            // dlg.open();
            // return;
            // }
            super.setPropertyValue(id, value);
            transferLocation(false);
            updateSubjobContainer();

            fireStructureChange(EParameterName.COLLAPSED.getName(), this);
        } else {
            super.setPropertyValue(id, value);
        }
    }

    public void updateJobletNodes(boolean update) {
        // TDI-18915:no need "if(!isCollapsed()){}" here since it is only called in UpdateJobletNodeCommand and can not
        // update the joblet NodeContainer in job when modify the joblet
        refreshJobletNodes(update, isCollapsed());
        // transferLocation(update);
        updateSubjobContainer();
        // refreshJobletConnections();
    }

    public void refreshJobletNodes(boolean update, boolean coll) {
        // if (!coll || update) {
        AbstractTestContainer testProcess = (AbstractTestContainer) this.getNode().getProcess();
        // List<? extends INode> testNodes = testProcess.getTestNodes();
        // Set<IConnection> conns = new HashSet<IConnection>();
        // boolean lockByOther = false;

        // Map<String, List<? extends IElementParameter>> paraMap = new HashMap<String, List<? extends
        // IElementParameter>>();
        // List<NodeContainer> temList = new ArrayList<NodeContainer>(nodeContainers);
        // for (NodeContainer nc : nodeContainers) {
        // if (this.node.getProcess() instanceof IProcess2) {
        // if (!update) {
        // paraMap.put(nc.getNode().getJoblet_unique_name(), nc.getNode().getElementParameters());
        // }
        // ((IProcess2) this.node.getProcess()).removeUniqueNodeName(nc.getNode().getUniqueName());
        // }
        // }
        nodeContainers.clear();
        // jobletElements.clear();

        // boolean canAdd = false;
        // boolean canRemove = false;
        for (INode inode : this.testNodes) {
            // canAdd = util.canAdd(temList, inode);
            if ((inode instanceof Node)) {
                Node temNode = (Node) inode;
                // if (canAdd) {
                // conns.addAll(temNode.getIncomingConnections());
                // conns.addAll(temNode.getOutgoingConnections());
                // Node jnode = util.cloneNode(temNode, this.node.getProcess(), paraMap, lockByOther);
                if (!this.node.isActivate()) {
                    // jnode.setActivate(this.node.isActivate());
                }
                // NodeContainer nodeContainer = util.cloneNodeContainer(temNode.getNodeContainer(), jnode);
                // jnode.setJobletnode(this.node);
                // jnode.setJoblet_unique_name(temNode.getUniqueName());
                this.nodeContainers.add(temNode.getNodeContainer());

                this.jobletElements.add(temNode);
                this.jobletElements.add(temNode.getNodeLabel());
                this.jobletElements.add(temNode.getNodeError());
                this.jobletElements.add(temNode.getNodeProgressBar());
                // } else if (update) {
                // for (NodeContainer nodeC : nodeContainers) {
                // if (nodeC.getNode().getJoblet_unique_name().equals(temNode.getUniqueName())) {
                // util.updateNode(nodeC.getNode(), temNode);
                // break;
                // }
                // }
                // }

            }
        }
        // temList = new ArrayList<NodeContainer>(nodeContainers);
        // for (NodeContainer nodeCon : temList) {
        // Node temNode = nodeCon.getNode();
        // canRemove = util.canDelete(jobletNodes, temNode);
        // if (canRemove) {
        // this.nodeContainers.remove(nodeCon);
        // this.jobletElements.remove(temNode);
        // this.jobletElements.remove(temNode.getNodeError());
        // this.jobletElements.remove(temNode.getNodeLabel());
        // this.jobletElements.remove(temNode.getNodeProgressBar());
        // List<? extends IConnection> inCons = new ArrayList<IConnection>(temNode.getIncomingConnections());
        // for (IConnection con : inCons) {
        // con.getTarget().removeInput(con);
        // }
        // List<? extends IConnection> outCons = new ArrayList<IConnection>(temNode.getOutgoingConnections());
        // for (IConnection con : outCons) {
        // con.getTarget().removeOutput(con);
        // }
        // }
        // }
        // for (IConnection con : conns) {
        // String sourceName = con.getSource().getUniqueName();
        // String targetName = con.getTarget().getUniqueName();
        // Node sourceNode = null;
        // Node targetNode = null;
        // for (NodeContainer nodeC : nodeContainers) {
        // Node connNode = nodeC.getNode();
        // if (connNode.getJoblet_unique_name().equals(sourceName)) {
        // sourceNode = connNode;
        // }
        // if (connNode.getJoblet_unique_name().equals(targetName)) {
        // targetNode = connNode;
        // }
        // if (sourceNode != null && targetNode != null) {
        // util.cloneConnection(con, sourceNode, targetNode);
        // break;
        // }
        // }
        // }

        // }
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
            // if the subjob hidden and collapsed, remove the collapse status first.
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

    // public Boolean isNeedLock() {
    // return needLock;
    // }

    public void setNeedchangeLock(boolean needchangeLock) {
        this.needchangeLock = needchangeLock;
    }
}

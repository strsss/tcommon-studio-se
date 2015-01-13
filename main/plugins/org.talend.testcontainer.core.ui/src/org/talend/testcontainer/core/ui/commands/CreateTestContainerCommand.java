// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.testcontainer.core.ui.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.talend.commons.exception.LoginException;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataToolHelper;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.INodeConnector;
import org.talend.core.model.process.IProcess2;
import org.talend.core.ui.CoreUIPlugin;
import org.talend.core.ui.services.IDesignerCoreUIService;
import org.talend.designer.core.model.components.EParameterName;
import org.talend.designer.core.model.process.AbstractProcessProvider;
import org.talend.designer.core.ui.AbstractMultiPageTalendEditor;
import org.talend.designer.core.ui.editor.TalendEditor;
import org.talend.designer.core.ui.editor.cmd.ConnectionCreateCommand;
import org.talend.designer.core.ui.editor.cmd.CreateNodeContainerCommand;
import org.talend.designer.core.ui.editor.cmd.MultiplePasteCommand;
import org.talend.designer.core.ui.editor.cmd.NodesPasteCommand;
import org.talend.designer.core.ui.editor.cmd.NotesPasteCommand;
import org.talend.designer.core.ui.editor.connections.Connection;
import org.talend.designer.core.ui.editor.jobletcontainer.JobletContainer;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainer;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.editor.nodes.NodePart;
import org.talend.designer.core.ui.editor.notes.NoteEditPart;
import org.talend.designer.core.ui.editor.process.Process;
import org.talend.designer.core.ui.editor.subjobcontainer.SubjobContainerPart;
import org.talend.repository.RepositoryWorkUnit;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;
import org.talend.testcontainer.core.ui.models.AbstractTestContainer;
import org.talend.testcontainer.core.ui.models.ETestContainerNodeType;
import org.talend.testcontainer.core.ui.models.TestContainerInputOutputComponent;

/**
 * created by Talend on Jan 6, 2015 Detailled comment
 *
 */
public class CreateTestContainerCommand extends Command {

    private AbstractProcessProvider findProcessProvider = AbstractProcessProvider
            .findProcessProviderFromPID(IComponent.JOBLET_PID);

    private final IProcess2 workProcess;

    private final IProcess2 testConProcess;

    private final Point currentPoint;

    /**
     * part of nodes in subjob.
     */
    private List<NodePart> singleNodeParts;

    private List<NoteEditPart> noteParts;

    /**
     * all of nodes in subjob.
     */
    private List<NodePart> subjobNodeParts;

    private List<SubjobContainerPart> subjobParts;

    /**
     * input/output node.
     */

    private List<NodePart> inputNodeParts;

    private Map<String, List<NodePart>> inputNodePartsMap;

    private List<NodePart> outputNodeParts;

    private Map<String, List<NodePart>> outputNodePartsMap;

    /**
     * current job.
     */
    private Map<String, List<String>> curInputConnectionMap = new HashMap<String, List<String>>();

    private Map<String, List<String>> curOutputConnectionMap = new HashMap<String, List<String>>();

    private final CompoundCommand delCompoundCmd;

    private final CompoundCommand addCompoundCmd;

    public CreateTestContainerCommand(IProcess2 workProcess, IProcess2 testConProcess, Point currentPoint) {
        super();
        this.testConProcess = testConProcess;
        this.workProcess = workProcess;
        this.currentPoint = currentPoint;
        this.delCompoundCmd = new CompoundCommand();
        this.addCompoundCmd = new CompoundCommand();
    }

    public Process getWorkProcess() {
        return (Process) this.workProcess;
    }

    public Process getTestContainerProcess() {
        return (Process) this.testConProcess;
    }

    public Point getCurrentPoint() {
        return this.currentPoint;
    }

    public void setNodesParameters(List<NodePart> singleNodeParts, List<NodePart> subjobNodeParts,
            List<SubjobContainerPart> subjobParts) {
        this.singleNodeParts = singleNodeParts;
        this.subjobNodeParts = subjobNodeParts;
        this.subjobParts = subjobParts;
    }

    public void setNoteParts(List<NoteEditPart> noteParts) {
        this.noteParts = noteParts;
    }

    public void setInputOutputNodesParameters(List<NodePart> inputNodeParts, List<NodePart> outputNodeParts) {
        this.inputNodeParts = inputNodeParts;
        this.outputNodeParts = outputNodeParts;
    }

    public void setInputOutputNodesMap(Map<String, List<NodePart>> inputNodePartsMap,
            Map<String, List<NodePart>> outputNodePartsMap) {
        this.inputNodePartsMap = inputNodePartsMap;
        this.outputNodePartsMap = outputNodePartsMap;
    }

    @Override
    public void execute() {
        IRepositoryService service = CorePlugin.getDefault().getRepositoryService();
        IProxyRepositoryFactory factory = service.getProxyRepositoryFactory();
        factory.executeRepositoryWorkUnit(new RepositoryWorkUnit("create process") {

            @Override
            protected void run() throws LoginException, PersistenceException {
                // TODO Auto-generated method stub
                // create joblet
                createElementsForJoblet();
                // save and check joblet process
                saveAndCheckJobletProcess();
                // check problem.
                checkWorkProcess();
                ((AbstractTestContainer) getTestContainerProcess()).loadJunitContainer();
            }
        });
    }

    /**
     * 
     * cLi Comment method "createElementsForJoblet".
     */
    private void createElementsForJoblet() {
        if (getTestContainerProcess() == null) {
            return;
        }
        List<NodePart> allNodeParts = new ArrayList<NodePart>();
        List<SubjobContainerPart> allSubjobParts = new ArrayList<SubjobContainerPart>();
        if (singleNodeParts != null && singleNodeParts.size() > 0) {
            allNodeParts.addAll(singleNodeParts);
            allSubjobParts.add((SubjobContainerPart) singleNodeParts.get(0).getParent().getParent());
        }
        if (subjobParts != null && subjobNodeParts != null && subjobNodeParts.size() > 0) {
            allNodeParts.addAll(subjobNodeParts);
            allSubjobParts.addAll(subjobParts);
        }

        // copy the nodes and notes.
        if (allNodeParts.size() > 0 && noteParts != null && noteParts.size() > 0) {
            MultiplePasteCommand cmd = new MultiplePasteCommand(allNodeParts, noteParts, getTestContainerProcess(),
                    getCurrentPoint());
            cmd.setSelectedSubjobs(allSubjobParts);
            // cmd.setJobletRefactor(true); // related to method "getRelatedNode()".
            cmd.execute();
        } else if (allNodeParts.size() > 0) {
            NodesPasteCommand cmd = new NodesPasteCommand(allNodeParts, getTestContainerProcess(), getCurrentPoint());
            cmd.setJunitCreate(true);
            cmd.setSelectedSubjobs(allSubjobParts);
            // cmd.setJobletRefactor(true); // related to method "getRelatedNode()".
            cmd.execute();
        } else if (noteParts != null && noteParts.size() > 0) {
            NotesPasteCommand cmd = new NotesPasteCommand(noteParts, getTestContainerProcess(), getCurrentPoint(), false, null);
            cmd.execute();
        }
        // process the inputs/outputs.
        createInputOutputNodes(true);
        createInputOutputNodes(false);
    }

    /**
     * 
     * cLi Comment method "saveAndCheckJobletProcess".
     */
    private void saveAndCheckJobletProcess() {
        // check
        getTestContainerProcess().checkStartNodes();
        getTestContainerProcess().checkProcess();
        for (INode node : getTestContainerProcess().getGraphicalNodes()) {
            // node.setReadOnly(true);
        }
        // save
        final AbstractMultiPageTalendEditor editor = (AbstractMultiPageTalendEditor) getTestContainerProcess().getEditor();
        if (editor != null) {
            // fake the dirty to save it, and there is no undo also.
            IDesignerCoreUIService designerCoreUIService = CoreUIPlugin.getDefault().getDesignerCoreUIService();
            if (designerCoreUIService != null) {
                designerCoreUIService.executeCommand(getTestContainerProcess(), new Command() {
                });
            }
            // force to use super method "doSave()". In order to, don't reload joblet, when save the joblet.
            editor.doSave(new NullProgressMonitor());

        }
    }

    private void createJobletNodeConnections(final Node jobletNode, boolean input) {
        List<NodePart> curInputOutputNodeParts = null;
        Map<String, List<NodePart>> curInputOutputNodePartsMap = null;
        Map<String, List<String>> curInputOutputConnectionMap = null;
        if (input) {
            curInputOutputNodeParts = inputNodeParts;
            curInputOutputNodePartsMap = inputNodePartsMap;
            curInputOutputConnectionMap = curInputConnectionMap;
        } else {
            curInputOutputNodeParts = outputNodeParts;
            curInputOutputNodePartsMap = outputNodePartsMap;
            curInputOutputConnectionMap = curOutputConnectionMap;
        }
        if (curInputOutputNodeParts != null && curInputOutputNodePartsMap != null && curInputOutputConnectionMap != null) {
            for (NodePart nodePart : curInputOutputNodeParts) {
                Node node = (Node) nodePart.getModel();
                List<NodePart> nodeParts = curInputOutputNodePartsMap.get(node.getUniqueName());
                if (nodeParts != null) {
                    for (NodePart part : nodeParts) {
                        Node sourceNode = null;
                        Node targetNode = null;
                        String uniqueNameKey = null;
                        if (input) {
                            sourceNode = (Node) part.getModel();
                            targetNode = jobletNode;
                            uniqueNameKey = sourceNode.getUniqueName();
                        } else {
                            sourceNode = jobletNode;
                            targetNode = (Node) part.getModel();
                            uniqueNameKey = targetNode.getUniqueName();
                        }
                        final List<String> paramsList = curInputOutputConnectionMap.get(uniqueNameKey);
                        if (paramsList != null && paramsList.size() >= 3) {
                            final String metaName = paramsList.get(0);
                            final String connectionName = paramsList.get(1);
                            String connectorName = paramsList.get(2);
                            if (input) {
                                // input is joblet.
                                if (findProcessProvider != null && findProcessProvider.isExtensionComponent(sourceNode)) {
                                    connectorName = metaName;
                                }
                            } else {
                                connectorName = metaName;
                            }

                            // some problems for undo.
                            Command cmd = createConnection(sourceNode, targetNode, metaName, connectionName, connectorName);
                            if (cmd != null) {
                                addCompoundCmd.add(cmd);
                                // not good. maybe, need improve it latter.
                                addCompoundCmd.add(new Command() {

                                    @Override
                                    public void execute() {
                                        super.execute();
                                    }

                                    @Override
                                    public void undo() {
                                        /*
                                         * there are some problems for connection name in multi-schema
                                         * connections(tMap...), when undo.
                                         */
                                        getWorkProcess().removeUniqueConnectionName(connectionName);
                                    }

                                });
                            }
                            // set connections links.
                            setJobletAttachedConnections(jobletNode, targetNode, paramsList, input);
                        }
                    }
                }
            }
        }
    }

    private void setJobletAttachedConnections(final Node jobletNode, Node targetNode, final List<String> paramsList, boolean input) {
        if (paramsList.size() >= 4) {
            final String connectionName = paramsList.get(1);
            final String jobletMetaName = paramsList.get(3);
            if (jobletMetaName != null) {
                if (input) {
                    // set attached connection for current joblet.
                    Command setCmd = new SetJobletConnectionsCommand(jobletNode, jobletMetaName, connectionName);
                    setCmd.execute();
                    addCompoundCmd.add(setCmd);
                } else {

                    // if the link node is joblet, set the attached connection
                    if (findProcessProvider != null && findProcessProvider.isExtensionComponent(targetNode)) {
                        Command setCmd = new SetJobletConnectionsCommand(targetNode, jobletMetaName, connectionName);
                        setCmd.execute();
                        addCompoundCmd.add(setCmd);
                        // the "undo" must be after undoning for addCompoundCmd.
                        if (paramsList.size() >= 5) {
                            final String oldConnName = paramsList.get(4);
                            final IElementParameter param = targetNode.getElementParameter(jobletMetaName + ":" //$NON-NLS-1$
                                    + EParameterName.CONNECTION.getName());
                            if (param != null && oldConnName != null) {
                                // work for undo.
                                delCompoundCmd.add(new Command() {

                                    @Override
                                    public void execute() {
                                        super.execute();
                                    }

                                    @Override
                                    public void undo() {
                                        param.setValue(oldConnName);
                                    }

                                });
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 
     * cLi Comment method "createInputOutputNodes".
     * 
     * create joblet input/output node in joblet process.
     */
    private void createInputOutputNodes(boolean input) {
        List<NodePart> inputOutputNodeParts = null;
        Map<String, List<NodePart>> inputOutputNodePartMap = null;
        Map<String, List<String>> inputOutputConnectionMap = null;
        ETestContainerNodeType nodeType = null;
        if (input) {
            inputOutputNodeParts = inputNodeParts;
            inputOutputNodePartMap = inputNodePartsMap;
            inputOutputConnectionMap = curInputConnectionMap;
            nodeType = ETestContainerNodeType.INPUT;
        } else {
            inputOutputNodeParts = outputNodeParts;
            inputOutputNodePartMap = outputNodePartsMap;
            inputOutputConnectionMap = curOutputConnectionMap;
            nodeType = ETestContainerNodeType.OUTPUT;
        }
        if (inputOutputNodeParts != null && inputOutputNodePartMap != null && inputOutputConnectionMap != null) {
            for (NodePart nodePart : inputOutputNodeParts) {
                Node node = (Node) nodePart.getModel();
                List<NodePart> nodeParts = inputOutputNodePartMap.get(node.getUniqueName());
                if (nodeParts != null) {
                    for (NodePart part : nodeParts) {
                        // create nodes.
                        Rectangle bounds = part.getFigure().getBounds();
                        Point point = new Point(bounds.x, bounds.y);

                        TestContainerInputOutputComponent inputOutputComponent = new TestContainerInputOutputComponent(nodeType);
                        Node inputOutputNode = new Node(inputOutputComponent, getTestContainerProcess());
                        NodeContainer inputOutputNodeContainer = null;
                        if (inputOutputNode.isJoblet() || inputOutputNode.isMapReduce()) {
                            inputOutputNodeContainer = new JobletContainer(inputOutputNode);
                        } else {
                            inputOutputNodeContainer = new NodeContainer(inputOutputNode);
                        }

                        CreateNodeContainerCommand inputOutputCmd = new CreateNodeContainerCommand(getTestContainerProcess(),
                                inputOutputNodeContainer, point);
                        if (inputOutputCmd.canExecute()) {
                            inputOutputCmd.execute();
                            // copy table.
                            Node linkNode = (Node) part.getModel();
                            Node sourceNode = null;
                            Node targetNode = null;

                            List<? extends IConnection> connections = null;
                            if (input) {
                                connections = linkNode.getOutgoingConnections();
                                sourceNode = inputOutputNode;
                                targetNode = getRelatedNode(node);
                            } else {
                                connections = linkNode.getIncomingConnections();
                                sourceNode = getRelatedNode(node);
                                targetNode = inputOutputNode;
                            }
                            IConnection conn = getConnection(connections, node, input);
                            final String inputOutputUniqueName = inputOutputNode.getUniqueName();
                            if (conn != null) {
                                IMetadataTable sourceTable = conn.getMetadataTable();
                                if (sourceTable != null) {
                                    IMetadataTable inputOutputTable = inputOutputNode.getMetadataTable(inputOutputUniqueName);
                                    MetadataToolHelper.copyTable(sourceTable, inputOutputTable);
                                }
                            }
                            // create connection.
                            final String connMetaName = ((Connection) conn).getMetaName();
                            String metaName = null;
                            final String connectionName = conn.getUniqueName();
                            String connectorName;
                            if (input) {
                                metaName = inputOutputUniqueName;
                                connectorName = conn.getTargetNodeConnector().getName();
                            } else {
                                metaName = connMetaName;
                                connectorName = conn.getSourceNodeConnector().getName();
                            }
                            if (input && findProcessProvider != null && findProcessProvider.isExtensionComponent(linkNode)) {
                                connectorName = conn.getLineStyle().getName();
                            }
                            createConnection(sourceNode, targetNode, metaName, connectionName, connectorName);
                            // if input node is joblet, set the connection parameter.
                            if (input && findProcessProvider != null && findProcessProvider.isExtensionComponent(targetNode)) {
                                Command setCmd = new SetJobletConnectionsCommand(targetNode, metaName, connectionName);
                                setCmd.execute();
                            }
                            // record connections in current process.
                            final String linkUiqueName = linkNode.getUniqueName();
                            List<String> connMapList = inputOutputConnectionMap.get(linkUiqueName);
                            if (connMapList == null) {
                                connMapList = new ArrayList<String>();
                                inputOutputConnectionMap.put(linkUiqueName, connMapList);
                            }
                            if (input) {
                                connMapList.add(connMetaName);
                            } else {
                                connMapList.add(inputOutputUniqueName);
                            }
                            connMapList.add(connectionName);
                            // connMapList.add(connectorName);
                            connMapList.add(conn.getConnectorName());
                            // work for setting connections link.
                            if (input) {
                                connMapList.add(metaName);
                            } else {
                                // if the link node is joblet, record attached connection.
                                if (findProcessProvider != null && findProcessProvider.isExtensionComponent(linkNode)) {
                                    List<INodeConnector> connectors = linkNode.getConnectorsFromType(EConnectionType.FLOW_MAIN);
                                    boolean found = false;
                                    if (connectors != null) {
                                        for (INodeConnector nector : connectors) {
                                            IElementParameter param = linkNode.getElementParameter(nector.getName() + ":" //$NON-NLS-1$
                                                    + EParameterName.CONNECTION.getName());
                                            if (param != null && connectionName.equals(param.getValue())) {
                                                connMapList.add(nector.getName());
                                                connMapList.add((String) param.getValue());
                                                found = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (!found) {
                                        connMapList.add(null);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private Command createConnection(Node sourceNode, Node targetNode, String metaName, final String connectionName,
            final String connectorName) {
        List<Object> listArgs = new ArrayList<Object>();
        listArgs.add(metaName);
        listArgs.add(connectionName);
        listArgs.add(null);

        if (sourceNode != null && targetNode != null) {
            ConnectionCreateCommand connCmd = new ConnectionCreateCommand(sourceNode, connectorName, listArgs);
            connCmd.setTarget(targetNode);
            if (connCmd.canExecute(true)) {
                connCmd.execute();
                return connCmd;
            }
        }
        return null;
    }

    private IConnection getConnection(List<? extends IConnection> connections, Node targetNode, boolean input) {
        if (connections == null || targetNode == null) {
            return null;
        }
        for (IConnection conn : connections) {
            INode tmpNode = null;
            if (input) {
                tmpNode = conn.getTarget();
            } else {
                tmpNode = conn.getSource();
            }
            if (tmpNode == targetNode) {
                return conn;
            }
        }

        return null;
    }

    private Node getRelatedNode(Node originalNode) {
        for (INode node : getTestContainerProcess().getGraphicalNodes()) {
            if (node.getUniqueName().equals(originalNode.getUniqueName())) {
                return (Node) node;
            }
        }
        return null;
    }

    /**
     * 
     * cLi Comment method "calculateJobletNodePoint".
     * 
     * calculate the joblet point.
     */
    private Point calculateJobletNodePoint() {
        Rectangle rec = null;
        double height = 0;
        double width = 0;
        int size = 0;
        if (singleNodeParts != null) {
            for (NodePart nodePart : singleNodeParts) {
                Rectangle bounds = nodePart.getFigure().getBounds();
                height += bounds.preciseHeight();
                width += bounds.preciseWidth();
                rec = bounds.getCopy().union(rec);
            }
            size += singleNodeParts.size();
        }
        if (size > 0) {
            height /= size;
            width /= size;
        } else {
            if (subjobParts != null) {
                for (SubjobContainerPart subjobPart : subjobParts) {
                    Rectangle bounds = subjobPart.getFigure().getBounds();
                    rec = bounds.getCopy().union(rec);
                }
            }
            height = width = TalendEditor.GRID_SIZE;
        }
        if (rec != null) {
            double x = rec.preciseX() + rec.preciseWidth() / 2;
            if (x - width > 0) {
                x -= width / 2;
            }
            double y = rec.preciseY() + rec.preciseHeight() / 2;
            if (y - height > 0) {
                y -= height / 2;
            }

            return new Point(x, y);
        }
        return null;
    }

    private void checkWorkProcess() {
        if (getWorkProcess() != null) {
            getWorkProcess().checkStartNodes();
            getWorkProcess().checkProcess();
        }
    }

    @Override
    public void redo() {
        // must use this order.
        delCompoundCmd.redo();
        addCompoundCmd.redo();
        checkWorkProcess();
    }

    @Override
    public void undo() {
        // must use this order.
        addCompoundCmd.undo();
        delCompoundCmd.undo();
        checkWorkProcess();
    }

    /**
     * 
     * cLi SetJobletConnectionsCommand class global comment. Detailled comment
     */
    class SetJobletConnectionsCommand extends Command {

        private Node jobletNode;

        private String metaName;

        private String connName;

        private IElementParameter connParam;

        public SetJobletConnectionsCommand(Node jobletNode, String metaName, String connName) {
            super();
            this.jobletNode = jobletNode;
            this.metaName = metaName;
            this.connName = connName;
            if (jobletNode != null && metaName != null) {
                connParam = jobletNode.getElementParameter(metaName + ":" //$NON-NLS-1$
                        + EParameterName.CONNECTION.getName());
            }
        }

        @Override
        public void execute() {
            if (connParam != null && connName != null) {
                connParam.setValue(connName);
            }
        }

    }
}

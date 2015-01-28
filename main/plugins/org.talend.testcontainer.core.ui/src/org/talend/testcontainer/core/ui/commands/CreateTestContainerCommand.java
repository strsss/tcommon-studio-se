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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.talend.commons.exception.LoginException;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataToolHelper;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess2;
import org.talend.core.ui.CoreUIPlugin;
import org.talend.core.ui.services.IDesignerCoreUIService;
import org.talend.designer.core.ui.AbstractMultiPageTalendEditor;
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
                // create junit
                createElementsForJunit();
                ((AbstractTestContainer) getTestContainerProcess()).loadJunitContainer();
                // save and check junit process
                saveAndCheckJunitProcess();
            }
        });
    }

    /**
     * 
     * cLi Comment method "createElementsForJunit".
     */
    private void createElementsForJunit() {
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
            cmd.execute();
        } else if (allNodeParts.size() > 0) {
            NodesPasteCommand cmd = new NodesPasteCommand(allNodeParts, getTestContainerProcess(), getCurrentPoint());
            cmd.setJunitCreate(true);
            cmd.setSelectedSubjobs(allSubjobParts);
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
     * cLi Comment method "saveAndCheckJunitProcess".
     */
    private void saveAndCheckJunitProcess() {
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

        }
    }

    /**
     * 
     * cLi Comment method "createInputOutputNodes".
     * 
     * create junit input/output node in junit process.
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
                                    inputOutputTable.setReadOnly(true);
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
                            createConnection(sourceNode, targetNode, metaName, connectionName, connectorName);
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

}

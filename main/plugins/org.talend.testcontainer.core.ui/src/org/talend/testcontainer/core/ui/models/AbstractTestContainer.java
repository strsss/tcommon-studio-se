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
package org.talend.testcontainer.core.ui.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.core.ui.component.ComponentsFactoryProvider;
import org.talend.designer.core.model.components.EParameterName;
import org.talend.designer.core.model.metadata.MetadataEmfFactory;
import org.talend.designer.core.model.utils.emf.talendfile.ConnectionType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
import org.talend.designer.core.ui.editor.connections.Connection;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.editor.process.Process;
import org.talend.testcontainer.core.testConProperties.TestContainerItem;
import org.talend.testcontainer.core.testcontainer.OriginalNode;
import org.talend.testcontainer.core.testcontainer.TestContainer;
import org.talend.testcontainer.core.testcontainer.TestContainerNode;
import org.talend.testcontainer.core.testcontainer.TestcontainerFactory;
import org.talend.testcontainer.core.ui.model.JunitContainer;

/**
 * created by Talend on Jan 7, 2015 Detailled comment
 *
 */
public class AbstractTestContainer extends Process {

    protected ImageDescriptor image;

    protected String id, originalJobID, version;

    protected List<INode> testNodes;

    protected JunitContainer junitContainer = null;

    /**
     * Getter for image.
     * 
     * @return the image
     */
    public ImageDescriptor getImage() {
        if (image == null) {
            image = null;// ETestContainerImages.getURLImageDescriptor(ETestContainerImages.TestContainer_COMPONENT_32);
        }
        return this.image;
    }

    /**
     * Sets the image.
     * 
     * @param image the image to set
     * @throws IOException
     */
    public void setImage(ImageDescriptor image) {
        this.image = image;
    }

    /**
     * DOC qzhang TestContainerProcess constructor comment.
     * 
     * @param property
     */
    public AbstractTestContainer(Property property) {
        super(property);
        this.id = property.getId();
        this.version = property.getVersion();
    }

    /**
     * Getter for originalJobID.
     * 
     * @return the originalJobID
     */
    public String getOriginalJobID() {
        return this.originalJobID;
    }

    /**
     * Sets the originalJobID.
     * 
     * @param originalJobID the originalJobID to set
     */
    public void setOriginalJobID(String originalJobID) {
        this.originalJobID = originalJobID;
    }

    /**
     * Getter for testNodes.
     * 
     * @return the testNodes
     */
    public List<INode> getTestNodes() {
        return this.testNodes;
    }

    /**
     * Sets the testNodes.
     * 
     * @param testNodes the testNodes to set
     */
    public void setTestNodes(List<INode> testNodes) {
        this.testNodes = testNodes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.designer.core.ui.editor.process.Process#createProcessType(org.talend.designer.core.model.utils.emf
     * .talendfile.TalendFileFactory)
     */
    @Override
    protected ProcessType createProcessType(TalendFileFactory fileFact) {
        return TestcontainerFactory.eINSTANCE.createTestContainer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.process.Process#loadXmlFile()
     */
    @Override
    public void loadXmlFile(boolean loadScreenshots) {
        super.loadXmlFile(loadScreenshots);
        loadJunitContainer();
    }

    /**
     * DOC nrousseau Comment method "loadSubjobs".
     * 
     * @param processType
     */
    public void loadJunitContainer() {
        // junitContainer = new JunitContainer(this);
        // for (INode node : this.getGraphicalNodes()) {
        // for (INode testNode : testNodes) {
        // if (node.getUniqueName().equals(testNode.getUniqueName())) {
        // junitContainer.addNodeContainer(((Node) node).getNodeContainer());
        // }
        // }
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.process.Process#saveXmlFile()
     */
    @Override
    public ProcessType saveXmlFile() throws IOException {
        ProcessType processType = super.saveXmlFile();
        saveOriginalNodes(processType);
        saveRelationConnection(processType);
        return processType;
    }

    private void saveRelationConnection(ProcessType processType) {
        List<Connection> connList;
        Connection connec;
        ConnectionType cType;
        List<? extends IElementParameter> paramList;
        EList listParamType;

        for (INode node : this.getGraphicalNodes()) {
            if ((node.getComponent() instanceof TestContainerInputOutputComponent)
                    && !((TestContainerInputOutputComponent) node.getComponent()).isInput()) {
                connList = new ArrayList<Connection>();
                List<? extends IConnection> inComingConnections = node.getIncomingConnections();
                for (IConnection connection : inComingConnections) {
                    if (connection instanceof Connection) {
                        connList.add((Connection) connection);
                    }
                }
                for (int j = 0; j < connList.size(); j++) {
                    connec = connList.get(j);
                    cType = TalendFileFactory.eINSTANCE.createConnectionType();
                    cType.setTarget(node.getUniqueName());
                    INode jSource = connec.getSource();
                    String sourceUniqueName = jSource.getUniqueName();
                    if (jSource instanceof Node) {
                        Node jn = (Node) jSource.getJobletNode();
                        if (jn != null) {
                            sourceUniqueName = jn.getUniqueName();
                        }
                    }
                    cType.setSource(sourceUniqueName);
                    cType.setLabel(connec.getName());
                    cType.setLineStyle(connec.getLineStyleId());
                    cType.setConnectorName(connec.getConnectorName());
                    cType.setOffsetLabelX(connec.getConnectionLabel().getOffset().x);
                    cType.setOffsetLabelY(connec.getConnectionLabel().getOffset().y);
                    cType.setMetaname(connec.getMetaName());
                    int id = connec.getOutputId();
                    if (id >= 0) {
                        cType.setOutputId(id);
                    }
                    INode connTarget = connec.getTarget();
                    if (connTarget.getJobletNode() != null) {
                        connTarget = connTarget.getJobletNode();
                    }
                    if (connTarget.getComponent().useMerge()) {
                        cType.setMergeOrder(connec.getInputId());
                    }
                    listParamType = cType.getElementParameter();
                    paramList = connec.getElementParameters();
                    saveElementParameters(TalendFileFactory.eINSTANCE, paramList, listParamType, processType);
                    processType.getConnection().add(cType);
                }

            }

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.designer.core.ui.editor.process.Process#saveNode(org.talend.designer.core.model.utils.emf.talendfile
     * .TalendFileFactory, org.talend.designer.core.model.utils.emf.talendfile.ProcessType,
     * org.eclipse.emf.common.util.EList, org.eclipse.emf.common.util.EList,
     * org.talend.designer.core.ui.editor.nodes.Node, org.talend.designer.core.model.metadata.MetadataEmfFactory)
     */
    @Override
    protected void saveNode(TalendFileFactory fileFact, ProcessType process, EList nList, EList cList, Node node,
            MetadataEmfFactory factory) {
        for (INode testNode : testNodes) {
            if (testNode.getUniqueName().equals(node.getUniqueName())) {
                return;
            }
        }
        super.saveNode(fileFact, process, nList, cList, node, factory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    private void saveOriginalNodes(ProcessType processType) {
        if (testNodes == null) {
            return;
        }
        if (!(processType instanceof TestContainer)) {
            return;
        }
        TestcontainerFactory testConFactory = TestcontainerFactory.eINSTANCE;
        EList oriList = ((TestContainer) processType).getOriginalNodes();
        for (INode node : testNodes) {
            OriginalNode oriNode = testConFactory.createOriginalNode();
            oriNode.setOriginalJobID(originalJobID);
            oriNode.setUniqueName(node.getUniqueName());
            oriNode.setPosX(node.getPosX());
            oriNode.setPosY(node.getPosY());
            oriList.add(oriNode);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.designer.core.ui.editor.process.Process#loadNodes(org.talend.designer.core.model.utils.emf.talendfile
     * .ProcessType, java.util.Hashtable)
     */
    @Override
    protected void loadNodes(ProcessType process, Hashtable<String, Node> nodesHashtable) throws PersistenceException {
        super.loadNodes(process, nodesHashtable);
        loadTestContainerElements((TestContainer) process, nodesHashtable);
    }

    /**
     * DOC hwang Comment method "loadTestContainerElements".
     * 
     * @param TestContainerProcess
     * @throws PersistenceException
     */
    private void loadTestContainerElements(TestContainer testContainerProcess, Hashtable<String, Node> nodesHashtable)
            throws PersistenceException {
        EList<TestContainerNode> testContainerNodes = testContainerProcess.getTestContainerNodes();
        EList listParamType;
        Node nc;
        for (TestContainerNode testContainerNode : testContainerNodes) {
            listParamType = testContainerNode.getElementParameter();
            TestContainerInputOutputComponent component;
            // if (testContainerProcess.isTrigger()) {
            // ETestContainerNodeType nodeType = testContainerProcess.isInput() ? ETestContainerNodeType.TRIGGER_INPUT
            // : ETestContainerNodeType.TRIGGER_OUTPUT;
            // component = new TestContainerTriggerLinkComponent(nodeType);
            // } else {

            ETestContainerNodeType nodeType = testContainerNode.isInput() ? ETestContainerNodeType.INPUT
                    : ETestContainerNodeType.OUTPUT;
            component = new TestContainerInputOutputComponent(nodeType);
            // }
            nc = loadNode(testContainerNode, component, nodesHashtable, listParamType);
        }

        EList<OriginalNode> oriNodes = testContainerProcess.getOriginalNodes();
        String oriID = null;
        ProcessType process = null;
        if (oriNodes.size() > 0) {
            oriID = oriNodes.get(0).getOriginalJobID();
            IRepositoryViewObject repositoryNode = ProxyRepositoryFactory.getInstance().getLastVersion(oriID);
            Item item = repositoryNode.getProperty().getItem();
            if (item instanceof ProcessItem) {
                process = ((ProcessItem) item).getProcess();
            }
        }
        for (OriginalNode oriNode : oriNodes) {
            String uniqueName = oriNode.getUniqueName();
            if (process != null) {
                NodeType nType = null;
                EList nodeList = process.getNode();
                boolean found = false;
                out: for (int i = 0; i < nodeList.size(); i++) {
                    nType = (NodeType) nodeList.get(i);
                    ElementParameterType pType;
                    for (int j = 0; j < nType.getElementParameter().size(); j++) {
                        pType = (ElementParameterType) nType.getElementParameter().get(j);
                        if (pType.getName().equals(EParameterName.UNIQUE_NAME.getName())) {
                            if (pType.getValue().equals(uniqueName)) {
                                found = true;
                                break out;
                            }
                            continue out;
                        }
                    }
                }
                if (found && nType != null) {
                    listParamType = nType.getElementParameter();
                    IComponent component = ComponentsFactoryProvider.getInstance().get(nType.getComponentName(),
                            getComponentsType());
                    if (component == null) {
                        getUnloadedNode().add(nType);
                        continue;
                    }
                    nc = loadNode(nType, component, nodesHashtable, listParamType);
                    nc.setLocation(new Point(oriNode.getPosX(), oriNode.getPosY()));
                }
            }
        }
        if (process != null) {
            loadConnections(process, nodesHashtable);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.designer.core.ui.editor.process.Process#createNodeType(org.talend.designer.core.model.utils.emf.talendfile
     * .TalendFileFactory, org.talend.designer.core.model.utils.emf.talendfile.ProcessType,
     * org.eclipse.emf.common.util.EList, org.talend.designer.core.ui.editor.nodes.Node)
     */
    @Override
    protected NodeType createNodeType(TalendFileFactory fileFact, ProcessType process, EList list, Node node) {
        TestContainer testContainerProcess = (TestContainer) process;
        if (node.getComponent() instanceof TestContainerTriggerLinkComponent) {
            TestContainerTriggerLinkComponent component = (TestContainerTriggerLinkComponent) node.getComponent();
            TestContainerNode jNode = TestcontainerFactory.eINSTANCE.createTestContainerNode();
            jNode.setInput(component.getTestContainerNodeType().equals(ETestContainerNodeType.TRIGGER_INPUT));
            // jNode.setTrigger(true);
            testContainerProcess.getTestContainerNodes().add(jNode);
            return jNode;
        } else if (node.getComponent() instanceof TestContainerInputOutputComponent) {
            TestContainerInputOutputComponent component = (TestContainerInputOutputComponent) node.getComponent();
            TestContainerNode jNode = TestcontainerFactory.eINSTANCE.createTestContainerNode();
            jNode.setInput(component.getTestContainerNodeType().equals(ETestContainerNodeType.INPUT));
            // jNode.setTrigger(false);
            testContainerProcess.getTestContainerNodes().add(jNode);
            return jNode;
        } else {
            return super.createNodeType(fileFact, testContainerProcess, list, node);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.process.Process#disableRunJobView()
     */
    @Override
    public boolean disableRunJobView() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.process.Process#getProcessType()
     */
    @Override
    protected ProcessType getProcessType() {
        TestContainerItem item = (TestContainerItem) getProperty().getItem();
        ProcessType processType = item.getTestContainerProcess();
        return processType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.process.Process#checkStartNodes()
     */
    @Override
    public void checkStartNodes() {
        boolean haveInput = false;
        boolean haveTrrInput = false;
        for (INode node : nodes) {
            if (node == null) {
                continue;
            }
            IComponent component = node.getComponent();
            if (component instanceof TestContainerInputOutputComponent) {
                if (((TestContainerInputOutputComponent) component).getTestContainerNodeType() == ETestContainerNodeType.INPUT) {
                    haveInput = true;
                } else if (((TestContainerInputOutputComponent) component).getTestContainerNodeType() == ETestContainerNodeType.TRIGGER_INPUT) {
                    haveTrrInput = true;
                }

            }
        }
        if (!haveInput || haveTrrInput) {
            this.setPropertyValue(EParameterName.STARTABLE.getName(), true);
        }
        super.checkStartNodes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.process.Process#getProperty()
     */
    @Override
    public Property getProperty() {
        Property property = super.getProperty();
        if (property == null) {
            try {
                property = ProxyRepositoryFactory.getInstance().getSpecificVersion(id, version, true).getProperty();
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }
        return property;
    }

    public JunitContainer getJunitContainer() {
        return this.junitContainer;
    }

}

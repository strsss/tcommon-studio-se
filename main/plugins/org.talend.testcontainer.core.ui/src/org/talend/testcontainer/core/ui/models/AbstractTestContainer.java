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
import java.util.Hashtable;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.process.INode;
import org.talend.core.model.properties.Property;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.designer.core.model.components.EParameterName;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.editor.process.Process;
import org.talend.testcontainer.core.testConProperties.TestContainerItem;
import org.talend.testcontainer.core.testcontainer.TestContainer;
import org.talend.testcontainer.core.testcontainer.TestContainerNode;
import org.talend.testcontainer.core.testcontainer.TestcontainerFactory;

/**
 * created by Talend on Jan 7, 2015 Detailled comment
 *
 */
public class AbstractTestContainer extends Process {

    private ImageDescriptor image;

    private String id, originalJobID, version;

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
    public AbstractTestContainer(Property property, String originalJobID) {
        super(property);
        this.originalJobID = originalJobID;
        this.id = property.getId();
        this.version = property.getVersion();
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
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.process.Process#saveXmlFile()
     */
    @Override
    public ProcessType saveXmlFile() throws IOException {
        return super.saveXmlFile();
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
        TestContainer testContainerProcess = (TestContainer) process;
        loadTestContainerNodes(testContainerProcess, nodesHashtable);
    }

    /**
     * DOC qzhang Comment method "loadTestContainerNodes".
     * 
     * @param TestContainerProcess
     */
    private void loadTestContainerNodes(TestContainer testContainerProcess, Hashtable<String, Node> nodesHashtable) {
        EList<TestContainerNode> TestContainerNodes = testContainerProcess.getTestContainerNodes();
        EList listParamType;
        Node nc;
        for (TestContainerNode testContainerNode : TestContainerNodes) {
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

}

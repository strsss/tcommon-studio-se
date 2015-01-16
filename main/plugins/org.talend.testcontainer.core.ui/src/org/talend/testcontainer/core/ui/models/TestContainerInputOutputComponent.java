// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.core.model.components.ComponentCategory;
import org.talend.core.model.components.EComponentType;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.components.IMultipleComponentManager;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.process.EComponentCategory;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.INodeConnector;
import org.talend.core.model.process.INodeReturn;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.Property;
import org.talend.core.model.temp.ECodePart;
import org.talend.designer.core.model.components.EParameterName;
import org.talend.designer.core.model.components.ElementParameter;
import org.talend.designer.core.model.components.EmfComponent;
import org.talend.designer.core.model.components.NodeConnector;
import org.talend.designer.core.model.components.NodeReturn;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.testcontainer.core.testConProperties.TestContainerItem;
import org.talend.testcontainer.core.testcontainer.TestContainer;
import org.talend.testcontainer.core.testcontainer.TestContainerNode;
import org.talend.testcontainer.core.ui.image.ETestContainerImages;

/**
 * DOC qzhang class global comment. Detailled comment
 */
public class TestContainerInputOutputComponent implements IComponent {

    protected ETestContainerNodeType testConType;

    public static final String FAMILY = "Junit"; //$NON-NLS-1$

    protected ImageDescriptor icon32;

    protected ImageDescriptor icon24;

    protected ImageDescriptor icon16;

    protected TestContainer testContainerProcess;

    protected Property property;

    private String paletteType;

    protected TestContainerInputOutputComponent() {
        icon32 = ETestContainerImages.getURLImageDescriptor(ETestContainerImages.JUNIT_INPUT_OUTPUT_COMPONENT);
        icon24 = ImageDescriptor.createFromImageData(icon32.getImageData().scaledTo(24, 24));
        icon16 = ImageDescriptor.createFromImageData(icon32.getImageData().scaledTo(16, 16));
    }

    public TestContainerInputOutputComponent(ETestContainerNodeType testContainerNodeType) {
        this();
        this.testConType = testContainerNodeType;
        switch (testContainerNodeType) {
        case ELEMENT:
            break;
        default:
            break;
        }
    }

    /**
     * DOC qzhang testContainerComponent constructor comment.
     */
    public TestContainerInputOutputComponent(Property property, ETestContainerNodeType testContainerNodeType) {
        this(testContainerNodeType);
        this.property = property;
        testContainerProcess = ((TestContainerItem) property.getItem()).getTestContainerProcess();
    }

    public boolean isInput() {
        return this.getTestContainerNodeType() == ETestContainerNodeType.INPUT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#createConnectors()
     */
    @Override
    public List<? extends INodeConnector> createConnectors(INode node) {
        List<INodeConnector> listConnector = new ArrayList<INodeConnector>();
        INodeConnector nodeConnector;
        int nbInput = 0;
        int nbOutput = 0;
        for (int i = 0; i < EConnectionType.values().length; i++) {
            EConnectionType currentType = EConnectionType.values()[i];

            if ((currentType == EConnectionType.FLOW_REF) || (currentType == EConnectionType.FLOW_MERGE)) {
                continue;
            }
            boolean exists = false;
            for (INodeConnector curNodeConn : listConnector) {
                if (curNodeConn.getDefaultConnectionType().equals(currentType)) {
                    exists = true;
                }
            }
            if (!exists) { // will add by default all connectors not defined in
                // the xml files
                nodeConnector = new NodeConnector(node);
                nodeConnector.setDefaultConnectionType(currentType);
                nodeConnector.setName(currentType.getName());
                nodeConnector.setBaseSchema(currentType.getName());
                nodeConnector.addConnectionProperty(currentType, currentType.getRGB(), currentType.getDefaultLineStyle());
                nodeConnector.setLinkName(currentType.getDefaultLinkName());
                nodeConnector.setMenuName(currentType.getDefaultMenuName());
                nodeConnector.setMaxLinkInput(0);
                nodeConnector.setMinLinkInput(0);
                nodeConnector.setMaxLinkOutput(0);
                nodeConnector.setMinLinkOutput(0);
                if (currentType == EConnectionType.FLOW_MAIN) {
                    nodeConnector.addConnectionProperty(EConnectionType.FLOW_REF, EConnectionType.FLOW_REF.getRGB(),
                            EConnectionType.FLOW_REF.getDefaultLineStyle());
                    nodeConnector.addConnectionProperty(EConnectionType.FLOW_MERGE, EConnectionType.FLOW_MERGE.getRGB(),
                            EConnectionType.FLOW_MERGE.getDefaultLineStyle());
                }
                listConnector.add(nodeConnector);
            }
        }
        INodeConnector mainConnector = null;
        for (INodeConnector connector : listConnector) {
            if (connector.getDefaultConnectionType().equals(EConnectionType.FLOW_MAIN)) {
                mainConnector = connector;
            }
        }
        if (ETestContainerNodeType.INPUT.equals(testConType)) {
            if (mainConnector != null) {
                mainConnector.setMaxLinkInput(1);
                mainConnector.setMinLinkInput(1);
                mainConnector.setMaxLinkOutput(1);
                mainConnector.setMinLinkOutput(1);
                // mainConnector.setCurLinkNbOutput(nbInput++);
            }
        } else if (ETestContainerNodeType.OUTPUT.equals(testConType)) {
            if (mainConnector != null) {
                mainConnector.setMaxLinkInput(1);
                mainConnector.setMinLinkInput(1);
                mainConnector.setMaxLinkOutput(1);
                mainConnector.setMinLinkOutput(1);
                // mainConnector.setCurLinkNbOutput(nbOutput++);
            }
        }
        return listConnector;
    }

    /**
     * DOC qzhang Comment method "getUniqueName".
     * 
     * @param node
     * @return
     */
    private String getUniqueName(TestContainerNode node) {
        EList elementParameter = node.getElementParameter();
        for (Object object : elementParameter) {
            ElementParameterType type = (ElementParameterType) object;
            if (type.getName().equals(EParameterName.UNIQUE_NAME.getName())) {
                return type.getValue();
            }
        }
        return ""; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#createElementParameters(org.talend.core.model.process.INode)
     */
    @Override
    public List<? extends IElementParameter> createElementParameters(INode node) {
        List<IElementParameter> listParam = new ArrayList<IElementParameter>();
        ElementParameter param = new ElementParameter(node);
        param.setName(EParameterName.UNIQUE_NAME.getName());
        param.setValue(""); //$NON-NLS-1$
        param.setDisplayName(EParameterName.UNIQUE_NAME.getDisplayName());
        param.setFieldType(EParameterFieldType.TEXT);
        param.setCategory(EComponentCategory.MAIN);
        param.setNumRow(1);
        param.setReadOnly(true);
        param.setShow(true);
        listParam.add(param);

        param = new ElementParameter(node);
        param.setName(EParameterName.STARTABLE.getName());
        if (ETestContainerNodeType.INPUT.equals(testConType)) {
            param.setValue(Boolean.TRUE);
        } else {
            param.setValue(Boolean.FALSE);
        }
        param.setDisplayName("STARTABLE"); //$NON-NLS-1$
        param.setFieldType(EParameterFieldType.CHECK);
        param.setCategory(EComponentCategory.MAIN);
        param.setNumRow(1);
        param.setReadOnly(true);
        param.setShow(false);
        listParam.add(param);

        param = new ElementParameter(node);
        param.setName(EParameterName.START.getName());
        param.setValue(Boolean.FALSE);
        param.setDisplayName(EParameterName.START.getDisplayName());
        param.setFieldType(EParameterFieldType.CHECK);
        param.setCategory(EComponentCategory.MAIN);
        param.setNumRow(1);
        param.setReadOnly(true);
        param.setShow(false);
        listParam.add(param);

        param = new ElementParameter(node);
        param.setName(EParameterName.SCHEMA_TYPE.getName());
        param.setValue(EmfComponent.BUILTIN);
        param.setDisplayName(EParameterName.SCHEMA_TYPE.getDisplayName());
        param.setFieldType(EParameterFieldType.SCHEMA_TYPE);
        param.setCategory(EComponentCategory.BASIC);
        param.setContext(EConnectionType.FLOW_MAIN.getName());
        param.setNumRow(1);
        param.setReadOnly(false);
        param.setShow(true);
        listParam.add(param);

        String context = EConnectionType.FLOW_MAIN.getName();
        String displayName = EParameterName.SCHEMA_TYPE.getDisplayName();

        IElementParameter parentParam = param;
        ElementParameter newParam = new ElementParameter(node);
        newParam.setCategory(EComponentCategory.BASIC);
        newParam.setName(EParameterName.SCHEMA_TYPE.getName());
        newParam.setDisplayName(displayName);
        newParam.setListItemsDisplayName(new String[] { EmfComponent.TEXT_BUILTIN, EmfComponent.TEXT_REPOSITORY });
        newParam.setListItemsDisplayCodeName(new String[] { EmfComponent.BUILTIN, EmfComponent.REPOSITORY });
        newParam.setListItemsValue(new String[] { EmfComponent.BUILTIN, EmfComponent.REPOSITORY });
        newParam.setValue(EmfComponent.BUILTIN);
        newParam.setNumRow(1);
        newParam.setFieldType(EParameterFieldType.TECHNICAL);
        newParam.setShow(true);
        newParam.setShowIf(parentParam.getName() + " =='" + EmfComponent.REPOSITORY + "'"); //$NON-NLS-1$ //$NON-NLS-2$
        newParam.setReadOnly(false);
        newParam.setNotShowIf(""); //$NON-NLS-1$

        newParam.setContext(context);
        newParam.setParentParameter(parentParam);

        newParam = new ElementParameter(node);
        newParam.setCategory(EComponentCategory.BASIC);
        newParam.setName(EParameterName.REPOSITORY_SCHEMA_TYPE.getName());
        newParam.setDisplayName(EParameterName.REPOSITORY_SCHEMA_TYPE.getDisplayName());
        newParam.setListItemsDisplayName(new String[] {});
        newParam.setListItemsValue(new String[] {});
        newParam.setNumRow(1);
        newParam.setFieldType(EParameterFieldType.TECHNICAL);
        newParam.setValue(""); //$NON-NLS-1$
        newParam.setShow(false);
        newParam.setRequired(true);
        newParam.setReadOnly(false);
        newParam.setShowIf(""); //$NON-NLS-1$
        newParam.setNotShowIf(""); //$NON-NLS-1$
        newParam.setContext(context);
        newParam.setParentParameter(parentParam);

        param = new ElementParameter(node);
        param.setName(EParameterName.UPDATE_COMPONENTS.getName());
        param.setValue(Boolean.TRUE);
        param.setDisplayName(EParameterName.UPDATE_COMPONENTS.getDisplayName());
        param.setFieldType(EParameterFieldType.CHECK);
        param.setCategory(EComponentCategory.BASIC);
        param.setNumRow(5);
        param.setReadOnly(true);
        param.setShow(false);
        listParam.add(param);

        // config the view tabs of component setting view.
        param = new ElementParameter(node);
        param.setName(EParameterName.LABEL.getName());
        param.setDisplayName(EParameterName.LABEL.getDisplayName());
        param.setFieldType(EParameterFieldType.TEXT);
        param.setCategory(EComponentCategory.VIEW);
        param.setNumRow(1);
        param.setReadOnly(false);
        param.setRequired(false);
        param.setShow(true);
        listParam.add(param);

        param = new ElementParameter(node);
        param.setName(EParameterName.HINT.getName());
        param.setDisplayName(EParameterName.HINT.getDisplayName());
        param.setFieldType(EParameterFieldType.TEXT);
        param.setCategory(EComponentCategory.VIEW);
        param.setNumRow(2);
        param.setReadOnly(false);
        param.setRequired(false);
        param.setShow(true);
        listParam.add(param);

        param = new ElementParameter(node);
        param.setName(EParameterName.CONNECTION_FORMAT.getName());
        param.setDisplayName(EParameterName.CONNECTION_FORMAT.getDisplayName());
        param.setFieldType(EParameterFieldType.TEXT);
        param.setCategory(EComponentCategory.VIEW);
        param.setNumRow(3);
        param.setReadOnly(false);
        param.setRequired(false);
        param.setShow(true);
        listParam.add(param);

        // config the documentation tab.
        param = new ElementParameter(node);
        param.setName(EParameterName.INFORMATION.getName());
        param.setValue(new Boolean(false));
        param.setDisplayName(EParameterName.INFORMATION.getDisplayName());
        param.setFieldType(EParameterFieldType.CHECK);
        param.setCategory(EComponentCategory.DOC);
        param.setNumRow(1);
        param.setReadOnly(false);
        param.setRequired(false);
        param.setShow(true);
        listParam.add(param);

        param = new ElementParameter(node);
        param.setName(EParameterName.COMMENT.getName());
        param.setValue(""); //$NON-NLS-1$
        param.setDisplayName(EParameterName.COMMENT.getDisplayName());
        param.setFieldType(EParameterFieldType.MEMO);
        param.setNbLines(10);
        param.setCategory(EComponentCategory.DOC);
        param.setNumRow(3);
        param.setReadOnly(false);
        param.setRequired(false);
        param.setShow(true);
        listParam.add(param);

        return listParam;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#createReturns()
     */
    @Override
    public List<? extends INodeReturn> createReturns() {
        return new ArrayList<NodeReturn>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getAvailableCodeParts()
     */
    @Override
    public List<ECodePart> getAvailableCodeParts() {
        return new ArrayList<ECodePart>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getFamily()
     */
    @Override
    public String getOriginalFamilyName() {
        return FAMILY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getTranslatedFamilyName()
     */
    @Override
    public String getTranslatedFamilyName() {
        return getOriginalFamilyName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getIcon16()
     */
    @Override
    public ImageDescriptor getIcon16() {
        return icon16;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getIcon24()
     */
    @Override
    public ImageDescriptor getIcon24() {
        return icon24;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getIcon32()
     */
    @Override
    public ImageDescriptor getIcon32() {
        return icon32;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getLongName()
     */
    @Override
    public String getLongName() {
        switch (this.testConType) {
        case ELEMENT:
            return property.getDescription();
        default:
            return testConType.getLongName();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getModulesNeeded()
     */
    @Override
    public List<ModuleNeeded> getModulesNeeded() {
        List<ModuleNeeded> list = new ArrayList<ModuleNeeded>();
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getMultipleComponentManager()
     */
    @Override
    public List<IMultipleComponentManager> getMultipleComponentManagers() {
        return Collections.EMPTY_LIST;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getName()
     */
    @Override
    public String getName() {
        switch (this.testConType) {
        case ELEMENT:
            return property.getLabel();
        default:
            return testConType.toString();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getPathSource()
     */
    @Override
    public String getPathSource() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getPluginDependencies()
     */
    @Override
    public List<String> getPluginDependencies() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getPluginFullName()
     */
    @Override
    public String getPluginExtension() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getTranslatedName()
     */
    public String getTranslatedName() {
        switch (this.testConType) {
        case ELEMENT:
            return property.getLabel();
        default:
            return testConType.getDisplayName();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getVersion()
     */
    @Override
    public String getVersion() {
        switch (this.testConType) {
        case ELEMENT:
            return property.getVersion();
        default:
            return "0.1"; //$NON-NLS-1$
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#hasConditionalOutputs()
     */
    @Override
    public boolean hasConditionalOutputs() {
        return Boolean.FALSE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isDataAutoPropagated()
     */
    @Override
    public boolean isDataAutoPropagated() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isLoaded()
     */
    @Override
    public boolean isLoaded() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isMultiplyingOutputs()
     */
    @Override
    public boolean isMultiplyingOutputs() {
        return Boolean.FALSE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isMultiplyingOutputs()
     */
    public Boolean isSubtreeWithLoop() {
        return Boolean.FALSE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isSchemaAutoPropagated()
     */
    @Override
    public boolean isSchemaAutoPropagated() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isVisible()
     */
    @Override
    public boolean isVisible() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isVisible(java.lang.String)
     */
    @Override
    public boolean isVisible(String family) {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#useMerge()
     */
    @Override
    public boolean useMerge() {
        return false;
    }

    /**
     * Getter for property.
     * 
     * @return the property
     */
    public Property getProperty() {
        return this.property;
    }

    /**
     * Sets the property.
     * 
     * @param property the property to set
     */
    public void setProperty(Property property) {
        this.property = property;
        testContainerProcess = ((TestContainerItem) property.getItem()).getTestContainerProcess();
    }

    /**
     * Getter for testContainerNodeType.
     * 
     * @return the testContainerNodeType
     */
    public ETestContainerNodeType getTestContainerNodeType() {
        return this.testConType;
    }

    /**
     * Sets the testContainerNodeType.
     * 
     * @param testContainerNodeType the testContainerNodeType to set
     */
    public void setTestContainerNodeType(ETestContainerNodeType testContainerNodeType) {
        this.testConType = testContainerNodeType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isMultipleOutput()
     */
    @Override
    public boolean isMultipleOutput() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#useLookup()
     */
    @Override
    public boolean useLookup() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#useImport()
     */
    @Override
    public boolean useImport() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getComponentType()
     */
    @Override
    public EComponentType getComponentType() {
        return EComponentType.JOBLET_INPUT_OUTPUT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isHashComponent()
     */
    @Override
    public boolean isHashComponent() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isTechnical()
     */
    @Override
    public boolean isTechnical() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isVisibleInComponentDefinition()
     */
    @Override
    public boolean isVisibleInComponentDefinition() {
        return true;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isMainCodeCalled()
     */
    @Override
    public boolean isMainCodeCalled() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#canParallelize()
     */
    @Override
    public boolean canParallelize() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getShortName()
     */
    @Override
    public String getShortName() {
        return "jio";
    }

    @Override
    public String getCombine() {
        return null;
    }

    @Override
    public IProcess getProcess() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Jsdoc)
     * 
     * @see org.talend.core.model.components.IComponent#getPaletteType()
     */
    @Override
    public String getPaletteType() {
        return paletteType;
    }

    /*
     * (non-Jsdoc)
     * 
     * @see org.talend.core.model.components.IComponent#setPaletteType(java.lang.String)
     */
    @Override
    public void setPaletteType(String paletteType) {
        this.paletteType = paletteType;
    }

    @Override
    public void setImageRegistry(Map<String, ImageDescriptor> imageRegistry) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getRepositoryType() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getType()
     */
    @Override
    public String getType() {
        return ComponentCategory.CATEGORY_4_DI.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#isReduce()
     */
    @Override
    public boolean isReduce() {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#getPartitioning()
     */
    @Override
    public String getPartitioning() {
        return "AUTO"; //$NON-NLS-1$
    }

    @Override
    public boolean isSupportDbType() {
        return false;
    }

    @Override
    public Map<String, ImageDescriptor> getImageRegistry() {
        return null;
    }

    @Override
    public boolean isLog4JEnabled() {
        return false;
    }

}

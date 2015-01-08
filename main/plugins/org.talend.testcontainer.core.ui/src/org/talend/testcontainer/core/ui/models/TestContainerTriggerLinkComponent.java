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
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.talend.core.model.components.EComponentType;
import org.talend.core.model.process.EComponentCategory;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.INodeConnector;
import org.talend.core.model.properties.Property;
import org.talend.designer.core.model.components.EParameterName;
import org.talend.designer.core.model.components.ElementParameter;
import org.talend.testcontainer.core.testConProperties.TestContainerItem;

/**
 * cli class global comment. Detailled comment
 */
public class TestContainerTriggerLinkComponent extends TestContainerInputOutputComponent {

    protected TestContainerTriggerLinkComponent() {
        // icon32 = EJobletImages.getURLImageDescriptor(EJobletImages.JOBLET_TRIGGER_COMPONENT);
        icon24 = ImageDescriptor.createFromImageData(icon32.getImageData().scaledTo(24, 24));
        icon16 = ImageDescriptor.createFromImageData(icon32.getImageData().scaledTo(16, 16));
    }

    public TestContainerTriggerLinkComponent(ETestContainerNodeType testContainerNodeType) {
        this();
        if (testContainerNodeType != ETestContainerNodeType.TRIGGER_INPUT
                && testContainerNodeType != ETestContainerNodeType.TRIGGER_OUTPUT) {
            throw new IllegalArgumentException("The argument is invalid."); //$NON-NLS-1$
        }
        this.testConType = testContainerNodeType;
    }

    public TestContainerTriggerLinkComponent(Property property, ETestContainerNodeType jobletNodeType) {
        this(jobletNodeType);
        this.property = property;
        testContainerProcess = ((TestContainerItem) property.getItem()).getTestContainerProcess();
    }

    @Override
    public boolean isInput() {
        return this.getTestContainerNodeType() == ETestContainerNodeType.TRIGGER_INPUT;
    }

    /**
     * 
     * cli Comment method "getSupportConnectionType".
     */
    public List<EConnectionType> getSupportConnectionType() {
        List<EConnectionType> types = new ArrayList<EConnectionType>();
        types.add(EConnectionType.ON_COMPONENT_OK);
        types.add(EConnectionType.ON_COMPONENT_ERROR);
        types.add(EConnectionType.ON_SUBJOB_OK);
        types.add(EConnectionType.ON_SUBJOB_ERROR);
        // types.add(EConnectionType.RUN_IF);

        return types;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#createConnectors(org.talend.core.model.process.INode)
     */
    @Override
    public List<? extends INodeConnector> createConnectors(INode node) {
        List<? extends INodeConnector> connectors = super.createConnectors(node);
        List<EConnectionType> supportConnectionType = getSupportConnectionType();

        int inputNum = !isInput() ? 1 : 0;
        int outputNum = isInput() ? 1 : 0;
        for (INodeConnector connector : connectors) {
            if (supportConnectionType.contains(connector.getDefaultConnectionType())) {
                connector.setMaxLinkInput(inputNum);
                connector.setMaxLinkOutput(outputNum);
            } else {
                connector.setMaxLinkInput(0);
                connector.setMaxLinkOutput(0);
            }
            connector.setMinLinkInput(0);
            connector.setMinLinkOutput(0);
        }

        return connectors;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.components.IComponent#createElementParameters(org.talend.core.model.process.INode)
     */
    @Override
    public List<? extends IElementParameter> createElementParameters(INode node) {
        List<IElementParameter> listParam = new ArrayList<IElementParameter>();
        ElementParameter param;

        // if (isInput()) { // only for input
        // param = new ElementParameter(node);
        // param.setName(EParameterName.CONNECTION_TYPE.getName());
        // param.setDisplayName(EParameterName.CONNECTION_TYPE.getDisplayName());
        // param.setField(EParameterFieldType.CLOSED_LIST);
        // param.setCategory(EComponentCategory.BASIC);
        // param.setNumRow(1);
        // param.setReadOnly(false);
        // param.setShow(true);
        // listParam.add(param);
        // List<EConnectionType> supportConnectionTypes = this.getSupportConnectionType();
        // List<String> connName = new ArrayList<String>();
        // List<String> connDisplayName = new ArrayList<String>();
        // for (EConnectionType type : supportConnectionTypes) {
        // connName.add(type.getName());
        // connDisplayName.add(type.getDefaultMenuName());
        // }
        // String[] connArrays = connName.toArray(new String[0]);
        // param.setListItemsDisplayCodeName(connArrays);
        // param.setListItemsDisplayName(connDisplayName.toArray(new String[0]));
        // param.setListItemsValue(connArrays);
        // param.setDefaultClosedListValue(connArrays[0]);
        // param.setValue(param.getDefaultClosedListValue());
        // }

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

        param = new ElementParameter(node);
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
        param.setValue(Boolean.TRUE);
        param.setDisplayName(EParameterName.STARTABLE.getDisplayName());
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

    @Override
    public EComponentType getComponentType() {
        return EComponentType.JOBLET_TRIGGER;
    }

}

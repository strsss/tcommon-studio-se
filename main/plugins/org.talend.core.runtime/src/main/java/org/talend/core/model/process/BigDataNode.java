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
package org.talend.core.model.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.talend.core.model.components.IComponent;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTable;

/**
 * Concrete class to instanciate as an AbstractNode for the BigData code generators
 * 
 */
public class BigDataNode extends AbstractNode implements IBigDataNode {

    private String outputType = null;

    private String inputType = null;

    private Map<String, List<IMetadataColumn>> keyList = new java.util.HashMap<String, List<IMetadataColumn>>();

    /**
     * Default constructor for the BigDataNode
     */
    public BigDataNode() {
    }

    /**
     * Constructor for the BigDataNode
     * 
     * @param component
     * @param uniqueName
     */
    public BigDataNode(IComponent component, String uniqueName) {
        setComponentName(component.getName());
        List<IMetadataTable> metaList = new ArrayList<IMetadataTable>();
        IMetadataTable metaTable = new MetadataTable();
        metaTable.setTableName(uniqueName);
        metaList.add(metaTable);
        setMetadataList(metaList);
        setComponent(component);
        setElementParameters(component.createElementParameters(this));
        setListConnector(component.createConnectors(this));
        setUniqueName(uniqueName);
        setHasConditionalOutputs(component.hasConditionalOutputs());
        setIsMultiplyingOutputs(component.isMultiplyingOutputs());
    }

    /*
     * @see org.talend.core.model.process.IBigDataNode#getInputType()
     */
    @Override
    public String getIncomingType() {
        List<? extends IConnection> incomingConnections = getIncomingConnections(EConnectionType.FLOW_MAIN);
        if (incomingConnections.size() > 0) {
            if (incomingConnections.get(0).getSource() instanceof BigDataNode) {
                IBigDataNode node = (IBigDataNode) incomingConnections.get(0).getSource();
                String requiredOutputType = node.getRequiredOutputType();
                return requiredOutputType != null ? requiredOutputType : node.getIncomingType();
            } else {
                // We are on an external node => PairRDD
                // TODO Maybe on the futur we need to handle RDD or DataFrame, but this required a big refactoring of
                // the external nodes.
                return "KEYVALUE"; //$NON-NLS-1$
            }
        }
        return null;
    }

    /*
     * @see org.talend.core.model.process.IBigDataNode#getOutputType()
     */
    @Override
    public String getOutgoingType() {
        List<? extends IConnection> outgoingConnections = getOutgoingConnections(EConnectionType.FLOW_MAIN);
        if (outgoingConnections.size() > 0) {
            if (outgoingConnections.get(0).getSource() instanceof BigDataNode) {
                IBigDataNode node = (IBigDataNode) outgoingConnections.get(0).getTarget();
                String requiredInputType = node.getRequiredInputType();
                return (requiredInputType != null && !node.isIdentity()) ? requiredInputType : node.getOutgoingType();
            } else {
                // We are on an external node => PairRDD
                // TODO Maybe on the futur we need to handle RDD or DataFrame, but this required a big refactoring of
                // the external nodes.
                return "KEYVALUE"; //$NON-NLS-1$
            }
        }
        return null;
    }

    /*
     * @see org.talend.core.model.process.IBigDataNode#getRequiredInputType()
     */
    @Override
    public String getRequiredInputType() {
        return this.inputType == null ? getComponent().getInputType() : this.inputType;
    }

    @Override
    public void setRequiredInputType(String requiredInputType) {
        this.inputType = requiredInputType;
    }

    /*
     * @see org.talend.core.model.process.IBigDataNode#getRequiredOutputType()
     */
    @Override
    public String getRequiredOutputType() {
        return this.outputType == null ? getComponent().getOutputType() : this.outputType;
    }

    /*
     * @see org.talend.core.model.process.IBigDataNode#setRequiredOutputType(String requiredOutputType)
     */
    @Override
    public void setRequiredOutputType(String requiredOutputType) {
        this.outputType = requiredOutputType;
    }

    /*
     * @see org.talend.core.model.process.IBigDataNode#isIdentity()
     */
    @Override
    public boolean isIdentity() {
        return "IDENTITY".equals(getRequiredInputType()) && "IDENTITY".equals(getRequiredOutputType()); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /*
     * @see org.talend.core.model.process.IBigDataNode#setKeyList(IBigDataNode bigDataNode, String direction)
     */
    @Override
    public void setKeyList(IBigDataNode bigDataNode, String direction) {
        // Get the partition key and make sure it's valid. The PARTITIONING parameter must be composed of two elements
        // (ELEMENT1.ELEMENT2)
        String[] partitionKey = bigDataNode.getComponent().getPartitioning().split("\\."); //$NON-NLS-1$
        boolean partitionKeyIsValid = partitionKey.length > 1 ? true : false;

        this.keyList = new HashMap<String, List<IMetadataColumn>>();
        List<IMetadataColumn> columnList = new ArrayList<IMetadataColumn>();
        if (partitionKeyIsValid) {
            // if the partition key is valid, get the first element of the key, which must be a table.
            IElementParameter parTableNode = bigDataNode.getElementParameter(partitionKey[0]);
            if (parTableNode != null) {
                if (parTableNode.getFieldType().equals(EParameterFieldType.TABLE)) {
                    String clumnNodeListName = partitionKey[1];
                    IElementParameter nodeElemForList = null;
                    // Iterate over the table columns and make sure one of them is a COLUMN_LIST or a CHECKBOX.
                    for (Object nodeItemList : parTableNode.getListItemsValue()) {
                        if (((IElementParameter) nodeItemList).getFieldType().equals(EParameterFieldType.PREV_COLUMN_LIST)
                                || ((IElementParameter) nodeItemList).getFieldType().equals(EParameterFieldType.COLUMN_LIST)
                                || ((IElementParameter) nodeItemList).getFieldType().equals(EParameterFieldType.CHECK)) {
                            nodeElemForList = (IElementParameter) nodeItemList;
                            break;
                        }
                    }

                    if (nodeElemForList != null) {
                        // Iterate over the table entries and get the second element of the partition key from that
                        // table.
                        for (Map nodeColumnListMap : (List<Map>) parTableNode.getValue()) {
                            Object value = nodeColumnListMap.get(clumnNodeListName);
                            String colName = ""; //$NON-NLS-1$
                            if (value != null) {
                                if (value instanceof String) {
                                    if (parTableNode.isBasedOnSchema()) {
                                        // if the table content is based on schema, then we suppose that the columns
                                        // which compose the key are defined by another parameter, which must be a
                                        // checkbox.
                                        if ("true".equals(value)) { //$NON-NLS-1$
                                            // SCHEMA_COLUMN is the name of the column in a "based on schema" context.
                                            colName = (String) nodeColumnListMap.get("SCHEMA_COLUMN"); //$NON-NLS-1$
                                        } else {
                                            continue;
                                        }
                                    } else {
                                        // else, the value itself contains the name of the column.
                                        colName = (String) value;
                                    }
                                } else if (value instanceof Integer) {
                                    Integer index = (Integer) value;
                                    if (nodeElemForList.getListItemsDisplayName().length > index) {
                                        colName = nodeElemForList.getListItemsDisplayName()[index];
                                    }
                                }
                                columnList.add(bigDataNode.getMetadataList().get(0).getColumn(colName));
                            } else {
                                throw new RuntimeException(
                                        "The parameter " + partitionKey[0] + "." + partitionKey[1] + " does not exist in the component " + this.getComponentName()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                            }
                        }
                        this.keyList.put(direction, columnList);
                    }
                } else {
                    throw new UnsupportedOperationException(
                            "The parameter " + partitionKey[0] + " type is wrong. It should be a " //$NON-NLS-1$ //$NON-NLS-2$
                                    + EParameterFieldType.TABLE + " parameter"); //$NON-NLS-1$
                }
            } else {
                throw new RuntimeException("The parameter " + partitionKey[0] + " does not exist in the component " //$NON-NLS-1$ //$NON-NLS-2$
                        + this.getComponentName());
            }
        }
    }

    /*
     * @see org.talend.core.model.process.IBigDataNode#getKeyList()
     */
    @Override
    public Map<String, List<IMetadataColumn>> getKeyList() {
        return keyList;
    }
}

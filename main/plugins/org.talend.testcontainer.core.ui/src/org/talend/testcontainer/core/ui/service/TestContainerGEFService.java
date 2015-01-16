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
package org.talend.testcontainer.core.ui.service;

import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.IProcess2;
import org.talend.core.ui.process.IGraphicalNode;
import org.talend.designer.core.ITestContainerGEFService;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainer;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.editor.subjobcontainer.SubjobContainer;
import org.talend.testcontainer.core.ui.model.JunitContainer;
import org.talend.testcontainer.core.ui.model.JunitContainerPart;
import org.talend.testcontainer.core.ui.models.AbstractTestContainer;

/**
 * created by Talend on Jan 12, 2015 Detailled comment
 *
 */
public class TestContainerGEFService implements ITestContainerGEFService {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.ITestContainerGEFService#createEditorPart(java.lang.Object)
     */
    @Override
    public EditPart createEditorPart(Object model) {
        if (model == null) {
            return null;
        }
        if (model instanceof JunitContainer) {
            EditPart part = new JunitContainerPart();
            return part;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ITestContainerGEFService#getJunitContainer(java.lang.Object)
     */
    @Override
    public Element getJunitContainer(IProcess2 process) {
        if (process instanceof AbstractTestContainer) {
            ((AbstractTestContainer) process).getJunitContainer();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ITestContainerGEFService#getJunitContainer(java.util.List)
     */
    @Override
    public NodeContainer createJunitContainer(Node node) {
        return new JunitContainer(node);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ITestContainerGEFService#setTestNodes(java.util.List, java.util.List)
     */
    @Override
    public void setTestNodes(Map<IGraphicalNode, IGraphicalNode> nodeMap, Map<SubjobContainer, List<Node>> subjobMap,
            List<NodeContainer> nodeCons) {
        for (NodeContainer nodeCon : nodeCons) {
            if (nodeCon instanceof JunitContainer) {
                for (IGraphicalNode copiedNode : nodeMap.keySet()) {
                    if (nodeMap.get(copiedNode) == nodeCon.getNode()) {
                        ((JunitContainer) nodeCon).setTestNodes(subjobMap.get(((Node) copiedNode).getNodeContainer()
                                .getSubjobContainer()));
                        break;
                    }
                }

                ((JunitContainer) nodeCon).refreshJunitNodes();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.designer.core.ITestContainerGEFService#getJunitContainerRectangle(org.talend.designer.core.ui.editor
     * .nodecontainer.NodeContainer)
     */
    @Override
    public Rectangle getJunitContainerRectangle(NodeContainer nodeContainer) {
        if (nodeContainer instanceof JunitContainer) {
            return ((JunitContainer) nodeContainer).getJunitContainerRectangle();
        }
        return nodeContainer.getNodeContainerRectangle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ITestContainerGEFService#isTestContainer(org.talend.core.model.process.IProcess2)
     */
    @Override
    public boolean isTestContainer(IProcess2 process) {
        if (process instanceof AbstractTestContainer) {
            return true;
        }
        return false;
    }

}

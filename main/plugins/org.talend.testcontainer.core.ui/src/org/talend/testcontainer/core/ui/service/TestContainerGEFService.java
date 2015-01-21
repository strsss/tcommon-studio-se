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

import java.util.ArrayList;
import java.util.HashMap;
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
import org.talend.designer.core.ui.editor.nodes.NodePart;
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
    public void setTestNodes(Map<IGraphicalNode, IGraphicalNode> nodeMap, Map<SubjobContainer, List<Node>> junitGroup,
            List<NodeContainer> nodeCons) {
        for (NodeContainer nodeCon : nodeCons) {
            if (nodeCon instanceof JunitContainer) {
                for (IGraphicalNode copiedNode : nodeMap.keySet()) {
                    if (nodeMap.get(copiedNode) == nodeCon.getNode()) {
                        ((JunitContainer) nodeCon).setTestNodes(junitGroup.get(((Node) copiedNode).getNodeContainer()
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

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ITestContainerGEFService#caculateJunitGroup(java.util.List)
     */
    @Override
    public Map<SubjobContainer, List<Node>> caculateJunitGroup(List<NodePart> nodeParts) {
        Map<SubjobContainer, List<Node>> subjobMap = new HashMap<SubjobContainer, List<Node>>();
        for (NodePart copiedNodePart : nodeParts) {
            IGraphicalNode copiedNode = (IGraphicalNode) copiedNodePart.getModel();
            if (!(copiedNode instanceof Node)) {
                continue;
            }
            SubjobContainer sub = ((Node) copiedNode).getNodeContainer().getSubjobContainer();
            if (sub == null) {
                continue;
            }
            Node stratNode = sub.getSubjobStartNode();
            if (stratNode == copiedNode) {
                ((Node) copiedNode).setJunitStart(true);
            }

            if (!subjobMap.containsKey(sub)) {
                List<Node> testNodes = new ArrayList<Node>();
                testNodes.add((Node) copiedNode);
                subjobMap.put(sub, testNodes);
            } else {
                subjobMap.get(sub).add((Node) copiedNode);
            }
        }
        for (List<Node> nodeList : subjobMap.values()) {
            boolean haveStart = false;
            for (Node node : nodeList) {
                if (node.isJunitStart()) {
                    haveStart = true;
                }
            }
            if (!haveStart && nodeList.size() > 0) {
                nodeList.get(0).setJunitStart(true);
            }
        }
        return subjobMap;
        // keep the code
        // for (List<Node> nodeList : subjobMap.values()) {
        // List<Node> tempList = new ArrayList<Node>(nodeList);
        // for (Node node : nodeList) {
        // if (tempList.contains(node)) {
        // continue;
        // }
        // Node startNode = null;
        // if (node.isJunitStart()) {
        // startNode = node;
        // }
        // List<Node> groupNodes = new ArrayList<Node>();
        // groupNodes.addAll(caculateInput(node, startNode, nodeList));
        // groupNodes.addAll(caculateOutput(node, nodeList));
        // junitGroup.put(startNode, groupNodes);
        // tempList.removeAll(groupNodes);
        // }
        //
        // }
        // return junitGroup;
        // }
        // keep the code
        // private List<Node> caculateInput(Node node, Node startNode, List<Node> nodes) {
        // List<Node> nodeList = new ArrayList<Node>();
        // for (IConnection conn : node.getIncomingConnections()) {
        // if (nodes.contains(conn.getSource())) {
        // nodeList.add((Node) conn.getSource());
        // } else {
        // if (startNode == null) {
        // node.setJunitStart(true);
        // startNode = node;
        // }
        // }
        // if (conn.getSource().getIncomingConnections().size() > 0) {
        // nodeList.addAll(caculateInput((Node) conn.getSource(), startNode, nodes));
        // }
        // }
        // return nodeList;
        // }
        //
        // private List<Node> caculateOutput(Node node, List<Node> nodes) {
        // List<Node> nodeList = new ArrayList<Node>();
        // for (IConnection conn : node.getOutgoingConnections()) {
        // if (nodes.contains(conn.getTarget())) {
        // nodeList.add((Node) conn.getTarget());
        // }
        // if (conn.getTarget().getOutgoingConnections().size() > 0) {
        // nodeList.addAll(caculateOutput((Node) conn.getTarget(), nodes));
        // }
        // }
        // return nodeList;
    }
}

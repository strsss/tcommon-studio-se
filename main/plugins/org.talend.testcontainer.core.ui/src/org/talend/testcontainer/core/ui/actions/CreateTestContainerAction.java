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
package org.talend.testcontainer.core.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess2;
import org.talend.core.ui.editor.CustomExternalActions;
import org.talend.core.ui.utils.PluginUtil;
import org.talend.designer.core.model.process.AbstractProcessProvider;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainerPart;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.editor.nodes.NodePart;
import org.talend.designer.core.ui.editor.notes.NoteEditPart;
import org.talend.designer.core.ui.editor.process.ProcessPart;
import org.talend.designer.core.ui.editor.subjobcontainer.SubjobContainerPart;
import org.talend.repository.RepositoryWorkUnit;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;
import org.talend.testcontainer.core.ui.NewTestContainerWizard;
import org.talend.testcontainer.core.ui.commands.CreateTestContainerCommand;
import org.talend.testcontainer.core.ui.editor.TestContainerEditorInput;
import org.talend.testcontainer.core.ui.editor.TestContainerMultiPageEditor;

/**
 * created by hwang on Jan 6, 2015 Detailled comment
 *
 */
public class CreateTestContainerAction extends CustomExternalActions {

    public static final String ID = "org.talend.testcontainer.core.ui.actions.CreateTestContainerAction"; //$NON-NLS-1$

    private IProcess2 workProcess;

    /**
     * part of nodes in subjob.
     */
    private List<NodePart> singleNodeParts = new ArrayList<NodePart>();

    private List<NoteEditPart> noteParts = new ArrayList<NoteEditPart>();

    /**
     * all of nodes in subjob.
     */
    private List<NodePart> subjobNodeParts = new ArrayList<NodePart>();

    private List<SubjobContainerPart> subjobParts = new ArrayList<SubjobContainerPart>();

    /**
     * input/output node.
     */

    private List<NodePart> inputNodeParts = new ArrayList<NodePart>();

    private Map<String, List<NodePart>> inputNodePartsMap = new HashMap<String, List<NodePart>>();

    private List<NodePart> outputNodeParts = new ArrayList<NodePart>();

    private Map<String, List<NodePart>> outputNodePartsMap = new HashMap<String, List<NodePart>>();

    private Map<String, IConnection> nonMetaConnectionMap = new HashMap<String, IConnection>();

    /**
     * 
     */
    private List<Node> singleNodes = new ArrayList<Node>();

    private Set<String> allSelectionNodesName = new HashSet<String>();

    public CreateTestContainerAction() {
        setId(ID);
    }

    public IProcess2 getWorkProcess() {
        return this.workProcess;
    }

    private void clearRecords() {
        this.workProcess = null;

        singleNodeParts.clear();
        noteParts.clear();

        subjobNodeParts.clear();
        subjobParts.clear();

        inputNodeParts.clear();
        inputNodePartsMap.clear();
        outputNodeParts.clear();
        outputNodePartsMap.clear();
        nonMetaConnectionMap.clear();

        singleNodes.clear();
    }

    /**
     * 
     * cLi Comment method "retrieveParts".
     */
    @SuppressWarnings("unchecked")
    private boolean retrieveParts() {
        clearRecords();
        for (Object o : getSelectedObjects()) {
            if (o instanceof NodePart) {
                if (!singleNodeParts.contains(o)) {
                    NodePart singleNodepart = (NodePart) o;
                    final Node node = (Node) singleNodepart.getModel();
                    if (node.getJobletNode() != null) {
                        return false;
                    }
                    singleNodeParts.add(singleNodepart);
                    singleNodes.add(node);
                    allSelectionNodesName.add(node.getUniqueName());
                    this.workProcess = (IProcess2) node.getProcess();
                    if (isInputOutputNode(singleNodepart, true)) {
                        inputNodeParts.add(singleNodepart);
                    }
                    if (isInputOutputNode(singleNodepart, false)) {
                        outputNodeParts.add(singleNodepart);
                    }
                }
            } else if (o instanceof NoteEditPart) {
                noteParts.add((NoteEditPart) o);
            } else if (o instanceof SubjobContainerPart) {
                SubjobContainerPart subjob = (SubjobContainerPart) o;
                boolean duplicate = false;
                List<NodePart> tmpSubjobNodePart = new ArrayList<NodePart>();

                for (Iterator iterator = subjob.getChildren().iterator(); iterator.hasNext();) {
                    NodeContainerPart nodeContainerPart = (NodeContainerPart) iterator.next();
                    NodePart nodePart = nodeContainerPart.getNodePart();
                    final Node model = (Node) nodePart.getModel();
                    allSelectionNodesName.add(model.getUniqueName());
                    this.workProcess = (IProcess2) model.getProcess();
                    // duplicate select node and subjob
                    if (nodePart != null) {
                        if (!getSelectedObjects().contains(nodePart)) {
                            if (!tmpSubjobNodePart.contains(nodePart)) {
                                tmpSubjobNodePart.add(nodePart);
                            }
                        } else {
                            duplicate = true;
                            break;
                        }
                    }
                }
                //
                if (!duplicate) {
                    subjobNodeParts.addAll(tmpSubjobNodePart);
                    subjobParts.add(subjob);
                }
            }
        }
        return true;
    }

    /**
     * 
     * cLi Comment method "isInputOutputNode".
     * 
     * check the input or output node.
     */
    private boolean isInputOutputNode(NodePart nodePart, boolean input) {
        if (nodePart == null) {
            return false;
        }
        Node node = (Node) nodePart.getModel();
        if (node != null) {
            List<? extends IConnection> connections = null;
            if (input) {
                connections = node.getIncomingConnections();
            } else {
                connections = node.getOutgoingConnections();
            }
            if (connections != null) {
                boolean found = false;
                for (IConnection conn : connections) {
                    INode otherNode = null;
                    if (input) {
                        otherNode = conn.getSource();
                    } else {
                        otherNode = conn.getTarget();
                    }
                    NodePart otherNodePart = findNodePart(nodePart, otherNode);
                    // don't contain in selection
                    if (otherNodePart != null && !existedInSelection(getSelectedObjects(), otherNodePart)) {
                        // must be metatable, record the non-meta.
                        if (allowConnection(conn)) {
                            Map<String, List<NodePart>> nodePartsMap = null;
                            if (input) {
                                nodePartsMap = inputNodePartsMap;
                            } else {
                                nodePartsMap = outputNodePartsMap;
                            }
                            if (nodePartsMap != null) {
                                List<NodePart> nodeParts = nodePartsMap.get(node.getUniqueName());
                                if (nodeParts == null) {
                                    nodeParts = new ArrayList<NodePart>();
                                    nodePartsMap.put(node.getUniqueName(), nodeParts);
                                }
                                if (!nodeParts.contains(otherNodePart)) {
                                    nodeParts.add(otherNodePart);
                                }
                            }
                            found = true;
                        } else {
                            nonMetaConnectionMap.put(otherNode.getUniqueName(), conn);
                        }
                    }
                }
                return found && nonMetaConnectionMap.isEmpty();
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean existedInSelection(List editParts, NodePart nodePart) {
        if (editParts == null) {
            return false;
        }
        if (editParts.contains(nodePart)) {
            return true;
        }
        for (Object obj : editParts) {
            if (obj instanceof EditPart) {
                if (existedInSelection(((EditPart) obj).getChildren(), nodePart)) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private NodePart findNodePart(NodePart siblingNodePart, INode node) {
        if (node != null && siblingNodePart != null) {
            // first, find in subjob.
            SubjobContainerPart parent = (SubjobContainerPart) siblingNodePart.getParent().getParent();
            NodePart findNodePart = findNodePart(parent, node);
            if (findNodePart == null) {
                // second, find in job.
                final EditPart root = parent.getParent();
                if (root != null && root instanceof ProcessPart) {
                    for (EditPart child : (List<EditPart>) root.getChildren()) {
                        if (child == parent) {
                            continue;
                        }
                        if (child instanceof SubjobContainerPart) {
                            findNodePart = findNodePart((SubjobContainerPart) child, node);
                            if (findNodePart != null) { // found
                                break;
                            }
                        }
                    }
                }

            }
            return findNodePart;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private NodePart findNodePart(SubjobContainerPart subjobPart, INode node) {
        if (subjobPart != null && node != null) {
            for (NodeContainerPart containerPart : (List<NodeContainerPart>) subjobPart.getChildren()) {
                NodePart nodePart = containerPart.getNodePart();
                if (nodePart.getModel() == node) {
                    return nodePart;
                }
            }
        }
        return null;
    }

    private boolean allowConnection(IConnection conn) {
        if (conn != null) {
            if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)
                    && !(conn.getConnectorName().equals("PIGCOMBINE") || conn.getConnectorName().equals("SPARKCOMBINE"))) {//$NON-NLS-1$ 
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected boolean calculateEnabled() {
        final List selectedObjects = getSelectedObjects();
        if (selectedObjects.isEmpty()) {
            return false;
        }
        if (PluginUtil.isMediation()) {
            return false;
        }
        if (isMapReduceEditorActive()) {
            return false;
        }
        // prepare for working.
        boolean canCal = retrieveParts();
        if (!canCal) {
            return canCal;
        }

        if (getWorkProcess() == null || getWorkProcess().isReadOnly()) {
            return false;
        }
        if (singleNodeParts.isEmpty() && subjobParts.isEmpty()) {
            return false;
        }
        if (!nonMetaConnectionMap.isEmpty()) {
            return false;
        }
        // check for ELT component.
        for (NodePart part : singleNodeParts) {
            Node node = (Node) part.getModel();
            if (node.isELTComponent()) {
                return false;
            }
        }

        // joblet
        if (AbstractProcessProvider.isExtensionProcessForJoblet(workProcess)) {
            // joblet input/output
            List<NodePart> allNodeParts = new ArrayList<NodePart>();
            if (singleNodeParts != null) {
                allNodeParts.addAll(singleNodeParts);
            }
            if (subjobNodeParts != null) {
                allNodeParts.addAll(subjobNodeParts);
            }
            if (!checkJobletInputOutput(allNodeParts)) {
                return false;
            }
        }

        // check connection.
        if (!checkConnectionNodes(inputNodePartsMap, true) || !checkConnectionNodes(outputNodePartsMap, false)) {
            return false;
        }
        if (existSubjobOutsideConnections()) {
            return false;
        }

        return true;
    }

    private boolean checkConnectionNodes(Map<String, List<NodePart>> inputOutputNodePartsMap, boolean input) {
        if (inputOutputNodePartsMap == null) {
            return true; // start or end nodes.
        }

        for (String key : inputOutputNodePartsMap.keySet()) {
            List<NodePart> inputOutpulist = inputOutputNodePartsMap.get(key);
            for (NodePart nodePart : inputOutpulist) {
                Node node = (Node) nodePart.getModel();
                // check reconnection to joblet.
                if (!checkCycleConnections(node, input)) {
                    return false;
                }
                // check that one node has more than two links to reconnection to joblet.
                if (!checkMultiConnections(node, input)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkCycleConnections(INode sourceNode, boolean input) {
        if (sourceNode == null) {
            return true; // start or end node.
        }
        List<? extends IConnection> connections = null;
        if (input) {
            connections = sourceNode.getIncomingConnections();
        } else {
            connections = sourceNode.getOutgoingConnections();
        }
        for (IConnection conn : connections) {
            INode node = null;
            if (input) {
                node = conn.getSource();
            } else {
                node = conn.getTarget();
            }
            // reconnection to joblet node.
            if (node != null && singleNodes.contains(node)) {
                return false;
            }

            if (!checkCycleConnections(node, input)) {
                return false;
            }

        }
        return true;
    }

    private boolean checkMultiConnections(INode sourceNode, boolean input) {
        if (sourceNode == null) {
            return true;
        }
        List<? extends IConnection> connections = null;
        if (input) {
            connections = sourceNode.getOutgoingConnections();
        } else {
            connections = sourceNode.getIncomingConnections();
        }
        boolean first = true;
        for (IConnection conn : connections) {
            INode node = null;
            if (input) {
                node = conn.getTarget();
            } else {
                node = conn.getSource();
            }
            if (node != null && singleNodes.contains(node)) {
                if (!first) {
                    return false;
                }
                first = false;
            }
        }
        return true;
    }

    /**
     * 
     * cLi Comment method "checkSubjobOutsideConnections".
     * 
     * work for the connections for one subjob to another subjob. if existed, will return true.
     */
    @SuppressWarnings("unchecked")
    private boolean existSubjobOutsideConnections() {
        if (subjobParts == null) {
            return false;
        }
        for (SubjobContainerPart subjobPart : subjobParts) {

            for (NodeContainerPart nodeContainerPart : (List<NodeContainerPart>) subjobPart.getChildren()) {
                NodePart nodePart = nodeContainerPart.getNodePart();
                Node node = (Node) nodePart.getModel();
                // output
                List<? extends IConnection> connections = node.getOutgoingConnections();
                for (IConnection conn : connections) {
                    INode target = conn.getTarget();
                    if (!allSelectionNodesName.contains(target.getUniqueName())) {
                        return true;
                    }
                }
                // input
                connections = node.getIncomingConnections();
                for (IConnection conn : connections) {
                    INode source = conn.getSource();
                    if (!allSelectionNodesName.contains(source.getUniqueName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 
     * cLi Comment method "checkJobletInputOutput".
     * 
     * work for refactoring the element of joblet, if contain the special input/output, will return false.
     */
    private boolean checkJobletInputOutput(List<NodePart> allNodeParts) {
        AbstractProcessProvider findProcessProvider = AbstractProcessProvider.findProcessProviderFromPID(IComponent.JOBLET_PID);
        if (findProcessProvider != null && allNodeParts != null) {
            for (NodePart nodePart : allNodeParts) {
                Node node = (Node) nodePart.getModel();
                if (findProcessProvider.isJobletInputOrOutputComponent(node)) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("restriction")
    @Override
    public void run() {
        // if null, open the path dialog.
        final Path path = new Path(this.workProcess.getId());

        final NewTestContainerWizard processWizard = new NewTestContainerWizard(path);
        final WizardDialog dlg = new WizardDialog(getWorkbenchPart().getSite().getShell(), processWizard);
        if (dlg.open() == Window.OK) {
            //
            final List<INode> testNodes = new ArrayList<INode>(getAllTestNodes());
            final ProgressMonitorDialog dialog = new ProgressMonitorDialog(null);
            try {
                dialog.run(true, false, new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        monitor.beginTask("CreateTestContainer...", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
                        Display.getDefault().syncExec(new Runnable() {

                            @Override
                            public void run() {
                                // JobletEditorInput fileEditorInput = null;
                                IRepositoryService service = CorePlugin.getDefault().getRepositoryService();
                                IProxyRepositoryFactory factory = service.getProxyRepositoryFactory();
                                RepositoryWorkUnit workUnit = new RepositoryWorkUnit("CreateTestContainer") {

                                    @Override
                                    protected void run() {
                                        // TODO Auto-generated method stub
                                        try {
                                            String originalId = CreateTestContainerAction.this.workProcess.getProperty().getId();
                                            final TestContainerEditorInput fileEditorInput = new TestContainerEditorInput(
                                                    processWizard.getProcessItem(), false, originalId, testNodes);

                                            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                                                    .getActivePage();
                                            IEditorPart openEditor = page.openEditor(fileEditorInput,
                                                    TestContainerMultiPageEditor.TESTCONTAINER_ID, true);
                                            // CommandStack commandStack = (CommandStack)
                                            // openEditor.getAdapter(CommandStack.class);

                                            IProcess2 loadedProcess = fileEditorInput.getLoadedProcess();
                                            if (loadedProcess != null) {
                                                CreateTestContainerCommand refactorCmd = new CreateTestContainerCommand(
                                                        getWorkProcess(), loadedProcess, null);

                                                refactorCmd.setNodesParameters(singleNodeParts, subjobNodeParts, subjobParts);
                                                refactorCmd.setNoteParts(noteParts);
                                                refactorCmd.setInputOutputNodesParameters(inputNodeParts, outputNodeParts);
                                                refactorCmd.setInputOutputNodesMap(inputNodePartsMap, outputNodePartsMap);

                                                getCommandStack().execute(refactorCmd); // work in current process
                                                loadedProcess.getUpdateManager().updateAll();
                                            }
                                        } catch (PartInitException e) {
                                            ExceptionHandler.process(e);
                                        } catch (PersistenceException e) {
                                            ExceptionHandler.process(e);
                                        }
                                    }
                                };
                                workUnit.setAvoidUnloadResources(true);
                                factory.executeRepositoryWorkUnit(workUnit);

                            }

                        });
                        monitor.done();
                    }
                });
            } catch (InvocationTargetException e) {
                ExceptionHandler.process(e);
            } catch (InterruptedException e) {
                ExceptionHandler.process(e);
            }

        }
    }

    private List<INode> getAllTestNodes() {
        List<INode> list = new ArrayList<INode>();
        for (NodePart np : singleNodeParts) {
            if (!list.contains(np.getModel())) {
                list.add((Node) np.getModel());
            }
        }
        for (NodePart np : subjobNodeParts) {
            if (!list.contains(np.getModel())) {
                list.add((Node) np.getModel());
            }
        }
        return list;
    }

    @Override
    public int getComponentType() {
        return 0;
    }

}

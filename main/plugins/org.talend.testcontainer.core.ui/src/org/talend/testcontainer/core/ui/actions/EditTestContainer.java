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
package org.talend.testcontainer.core.ui.actions;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.runtime.model.repository.ERepositoryStatus;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.ui.runtime.exception.MessageBoxExceptionHandler;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.commons.ui.runtime.image.OverlayImageProvider;
import org.talend.core.CorePlugin;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.Property;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.designer.runprocess.ItemCacheManager;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;
import org.talend.repository.model.RepositoryNode;
import org.talend.testcontainer.core.testConProperties.TestContainerItem;
import org.talend.testcontainer.core.ui.editor.TestContainerEditorInput;
import org.talend.testcontainer.core.ui.editor.TestContainerMultiPageEditor;
import org.talend.testcontainer.core.ui.image.ETestContainerImages;
import org.talend.testcontainer.core.ui.util.TestContainerRepositoryObjectType;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class EditTestContainer extends AbstractTestContainertAction {

    private static final String EDIT_LABEL = "Edit TestContainer";

    private static final String OPEN_LABEL = "Read TestContainer";

    /**
     * DOC qzhang EditTestContainer constructor comment.
     */
    public EditTestContainer() {
        super();
        setText(EDIT_LABEL);
        Image folderImg = ImageProvider.getImage(ETestContainerImages.JUNIT_UNKNOWN);
        setImageDescriptor(OverlayImageProvider.getImageWithNew(folderImg));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    protected void doRun() {
        ISelection selection = getSelection();
        Object obj = ((IStructuredSelection) selection).getFirstElement();

        RepositoryNode node = (RepositoryNode) obj;

        Property property = node.getObject().getProperty();
        TestContainerItem junittItem = null;

        ItemCacheManager.clearCache();
        Assert.isTrue(property.getItem() instanceof TestContainerItem);

        Property updatedProperty = null;
        try {

            updatedProperty = ProxyRepositoryFactory.getInstance()
                    .getLastVersion(new Project(ProjectManager.getInstance().getProject(property.getItem())), property.getId())
                    .getProperty();

        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }

        junittItem = (TestContainerItem) updatedProperty.getItem();

        IWorkbenchPage page = getActivePage();
        try {
            Boolean readonly = null;
            final TestContainerEditorInput fileEditorInput = new TestContainerEditorInput(junittItem, true);

            IEditorPart editorPart = page.findEditor(fileEditorInput);
            checkUnLoadedNodeForProcess(fileEditorInput);
            if (editorPart == null) {
                fileEditorInput.setRepositoryNode(node);
                TestContainerMultiPageEditor openEditor = (TestContainerMultiPageEditor) page.openEditor(fileEditorInput,
                        TestContainerMultiPageEditor.TESTCONTAINER_ID, true);
            } else {
                ((TestContainerMultiPageEditor) editorPart).setReadOnly(fileEditorInput.setForceReadOnly(false));
                page.activate(editorPart);
            }
            refresh(obj);
        } catch (PartInitException e) {
            MessageBoxExceptionHandler.process(e);
        } catch (PersistenceException e) {
            MessageBoxExceptionHandler.process(e);
        }
    }

    @Override
    public void init(TreeViewer viewer, IStructuredSelection selection) {
        boolean canWork = !selection.isEmpty() && selection.size() == 1;
        IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        if (factory.isUserReadOnlyOnCurrentProject()) {
            canWork = false;
        }
        if (canWork) {
            Object o = selection.getFirstElement();
            RepositoryNode node = (RepositoryNode) o;
            switch (node.getType()) {
            case REPOSITORY_ELEMENT:
                if (node.getObjectType() != TestContainerRepositoryObjectType.testContainerType) {
                    canWork = false;
                } else {
                    IRepositoryService service = CorePlugin.getDefault().getRepositoryService();
                    IProxyRepositoryFactory repFactory = service.getProxyRepositoryFactory();
                    if (repFactory.isPotentiallyEditable(node.getObject())) {
                        this.setText(EDIT_LABEL);
                    } else {
                        this.setText(OPEN_LABEL);
                    }
                }
                break;
            default:
                canWork = false;
            }
            if (canWork) {
                canWork = (factory.getStatus(node.getObject()) != ERepositoryStatus.DELETED);
            }
            if (canWork) {
                canWork = isLastVersion(node);
            }
        }
        setEnabled(canWork);
    }

    @Override
    public Class getClassForDoubleClick() {
        return TestContainerItem.class;
    }
}

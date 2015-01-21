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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.designer.core.ui.action.AbstractProcessAction;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryNode.EProperties;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.ui.views.IRepositoryView;
import org.talend.testcontainer.core.ui.util.TestContainerRepositoryObjectType;

/**
 * DOC qzhang class global comment. Detailled comment
 */
public abstract class AbstractTestContainertAction extends AbstractProcessAction {

    public static final String PROCESS_EDITOR_ID = "org.talend.designer.core.ui.MultiPageTalendEditor"; //$NON-NLS-1$

    /**
     * DOC qzhang CreateTestContainer constructor comment.
     */
    public AbstractTestContainertAction() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.actions.ITreeContextualAction#init(org.eclipse.jface.viewers.TreeViewer,
     * org.eclipse.jface.viewers.IStructuredSelection)
     */
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
            case SIMPLE_FOLDER:
            case SYSTEM_FOLDER:
                ERepositoryObjectType nodeType = (ERepositoryObjectType) node.getProperties(EProperties.CONTENT_TYPE);
                if (nodeType != TestContainerRepositoryObjectType.testContainerType) {
                    canWork = false;
                }
                if (node.getObject() != null && node.getObject().isDeleted()) {
                    canWork = false;
                }
                break;
            default:
                canWork = false;
            }
            if (canWork && !ProjectManager.getInstance().isInCurrentMainProject(node)) {
                canWork = false;
            }
        }
        setEnabled(canWork);
    }

    public RepositoryNode getTestContainerNode() {
        IRepositoryView viewPart = getViewPart();
        if (viewPart != null) {
            return (RepositoryNode) viewPart.getRoot().getRootRepositoryNode(TestContainerRepositoryObjectType.testContainerType);
        }

        return null;
    }
}

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
package org.talend.testcontainer.job.ui.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.data.container.RootContainer;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.repository.model.ProjectRepositoryNode;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IRepositoryNode.ENodeType;
import org.talend.repository.model.IRepositoryNode.EProperties;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.navigator.RepoViewCommonNavigator;
import org.talend.repository.navigator.RepoViewCommonViewer;
import org.talend.repository.viewer.content.ProjectRepoDirectChildrenNodeContentProvider;
import org.talend.repository.viewer.content.listener.RunnableResourceVisitor;
import org.talend.repository.viewer.content.listener.TopLevelNodeRunnable;
import org.talend.testcontainer.core.ui.util.TestContainerRepositoryObjectType;

/**
 * DOC hwang class global comment. Detailled comment
 */
public class JobTestContainerContentProvider extends ProjectRepoDirectChildrenNodeContentProvider {

    private final class TestDirectChildrenNodeVisitor extends RunnableResourceVisitor {

        @Override
        protected boolean visit(IResourceDelta delta, Collection<Runnable> runnables) {
            Set<RepositoryNode> topLevelNodes = getTopLevelNodes();
            boolean visitChildren = true;
            for (final RepositoryNode repoNode : topLevelNodes) {
                if (repoNode.getContentType() == ERepositoryObjectType.PROCESS) {
                    visitChildren = false; // if valid, don't visit the children any more.
                    if (viewer instanceof RepoViewCommonViewer) {
                        runnables.add(new TopLevelNodeRunnable(repoNode) {

                            @Override
                            public void run() {
                                refreshTopLevelNode(repoNode);
                            }
                        });
                    }
                }
            }
            return visitChildren;
        }
    }

    private TestDirectChildrenNodeVisitor testVisitor;

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.viewer.content.SingleTopLevelContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(Object element) {
        if (!(element instanceof RepositoryNode)) {
            return super.getChildren(element);
        }
        RepositoryNode repoNode = (RepositoryNode) element;

        getTopLevelNodes().add((RepositoryNode) repoNode.getRoot().getRootRepositoryNode(ERepositoryObjectType.PROCESS));

        ERepositoryObjectType type = repoNode.getObjectType();

        IPath path = new Path(type.getFolder());
        path = path.append(repoNode.getId());
        List<RepositoryNode> junitRepoNodes = new ArrayList<RepositoryNode>();
        try {
            RootContainer<String, IRepositoryViewObject> junitObjects = ProxyRepositoryFactory.getInstance().getObjectFromFolder(
                    ProjectManager.getInstance().getCurrentProject(), TestContainerRepositoryObjectType.testContainerType,
                    path.toOSString(), 6);
            if (!junitObjects.isEmpty() && !junitObjects.getMembers().isEmpty()) {
                for (IRepositoryViewObject viewNode : junitObjects.getMembers()) {
                    if (viewNode.isDeleted()) {
                        continue;
                    }
                    RepositoryNode node = new RepositoryNode(viewNode, repoNode, ENodeType.REPOSITORY_ELEMENT);
                    node.setProperties(EProperties.CONTENT_TYPE, TestContainerRepositoryObjectType.testContainerType);
                    node.setProperties(EProperties.LABEL, viewNode.getLabel());
                    repoNode.getChildren().add(node);
                    junitRepoNodes.add(node);
                }
            }

        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        return junitRepoNodes.toArray();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.viewer.content.SingleTopLevelContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.viewer.content.FolderListenerSingleTopContentProvider#addResourceVisitor(org.eclipse.ui
     * .navigator.CommonViewer)
     */
    @Override
    protected void addResourceVisitor(CommonViewer v) {
        // super.addResourceVisitor(v);
        if (v == null) {
            return;
        }
        RepoViewCommonNavigator navigator = null;
        if (v instanceof RepoViewCommonViewer) {
            CommonNavigator commonNavigator = ((RepoViewCommonViewer) v).getCommonNavigator();
            if (commonNavigator instanceof RepoViewCommonNavigator) {
                navigator = ((RepoViewCommonNavigator) commonNavigator);
            }
        }
        if (navigator == null) {
            return;
        }
        if (this.testVisitor != null) {
            navigator.removeVisitor(this.testVisitor);
        }
        this.testVisitor = new TestDirectChildrenNodeVisitor();
        navigator.addVisitor(this.testVisitor);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.viewer.content.ProjectRepoDirectChildrenNodeContentProvider#
     * getTopLevelNodeFromProjectRepositoryNode(org.talend.core.repository.model.ProjectRepositoryNode)
     */
    @Override
    protected RepositoryNode getTopLevelNodeFromProjectRepositoryNode(ProjectRepositoryNode projectNode) {
        return null;
    }

}

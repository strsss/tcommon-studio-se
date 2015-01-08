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
package org.talend.testcontainer.core.ui.content;

import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.repository.model.ProjectRepositoryNode;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.viewer.content.SubEmptyTopNodeContentProvider;
import org.talend.testcontainer.core.ui.tests.TestContainerProcessPropertyTester;
import org.talend.testcontainer.core.ui.util.TestContainerRepositoryObjectType;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class TestContainerContentProvider extends SubEmptyTopNodeContentProvider {

    TestContainerProcessPropertyTester nodeTester = new TestContainerProcessPropertyTester();

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.viewer.content.SingleTopLevelContentProvider#isRootNodeType(java.lang.Object)
     */
    @Override
    protected boolean isRootNodeType(Object element) {
        if (element instanceof RepositoryNode) {
            return nodeTester.isProcessTopNode((RepositoryNode) element);
        }
        return false;

    }

    @Override
    protected ProjectRepositoryNode getProjectRepositoryNode(RepositoryNode element) {
        return (ProjectRepositoryNode) element.getRoot();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.viewer.content.ProjectRepoAbstractContentProvider#getTopLevelNodeFromProjectRepositoryNode
     * (org.talend.repository.model.ProjectRepositoryNode)
     */
    @Override
    protected RepositoryNode getTopLevelNodeFromProjectRepositoryNode(ProjectRepositoryNode projectNode) {
        return projectNode.getRootRepositoryNode(TestContainerRepositoryObjectType.testContainerType);
    }

    @Override
    protected RepositoryNode getAndStoreTopLevelNode(RepositoryNode repositoryNode) {
        // must init the topLevelNodes first.
        RepositoryNode andStoreTopLevelNode = super.getAndStoreTopLevelNode(repositoryNode);
        // must have mr job, else, won't display the root node.
        if (true) {
            return andStoreTopLevelNode;
        }
        // MapReduceJobUtil.hasMapReduceJobs(repositoryNode.getRoot().getProject())
        return null;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.repository.viewer.content.FolderListenerSingleTopContentProvider#refreshTopLevelNode(org.talend.repository
     * .model.RepositoryNode)
     */
    @Override
    protected void refreshTopLevelNode(RepositoryNode repoNode) {
        super.refreshTopLevelNode(repoNode);

        ProjectRepositoryNode root = (ProjectRepositoryNode) repoNode.getRoot();
        // if (repoNode.equals(getTopLevelNodeFromProjectRepositoryNode(root))) {
        RepositoryNode processNode = root.getRootRepositoryNode(ERepositoryObjectType.PROCESS);
        if (processNode != null) {
            super.refreshTopLevelNode(processNode);
        }
        // }
    }

}

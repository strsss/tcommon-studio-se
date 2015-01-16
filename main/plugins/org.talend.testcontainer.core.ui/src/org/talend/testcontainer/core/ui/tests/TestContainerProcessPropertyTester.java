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
package org.talend.testcontainer.core.ui.tests;

import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.tester.AbstractNodeTester;
import org.talend.testcontainer.core.ui.util.TestContainerRepositoryObjectType;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class TestContainerProcessPropertyTester extends AbstractNodeTester {

    private static final String IS_PROCESS_TOP_NODE = "isProcessTopNode"; //$NON-NLS-1$

    private static final String IS_TEST_CONTAINER_PROCESS_NODE = "isTestContainerProcessNode"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.tester.AbstractNodeTester#testProperty(java.lang.Object, java.lang.String,
     * java.lang.Object[], java.lang.Object)
     */
    @Override
    protected Boolean testProperty(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof RepositoryNode) {
            RepositoryNode repositoryNode = (RepositoryNode) receiver;
            if (IS_TEST_CONTAINER_PROCESS_NODE.equals(property)) {
                return isTestContainerProcessNode(repositoryNode);
            } else if (IS_PROCESS_TOP_NODE.equals(property)) {
                return isProcessTopNode(repositoryNode);
            }
        }
        return null;
    }

    public boolean isTestContainerProcessNode(RepositoryNode repositoryNode) {
        return isTypeNode(repositoryNode, TestContainerRepositoryObjectType.testContainerType);
    }

    public boolean isProcessTopNode(RepositoryNode repositoryNode) {
        return isTypeTopNode(repositoryNode, ERepositoryObjectType.PROCESS);
    }

}

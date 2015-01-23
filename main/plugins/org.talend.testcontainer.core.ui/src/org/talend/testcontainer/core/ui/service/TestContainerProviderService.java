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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.core.ui.ITestContainerProviderService;
import org.talend.testcontainer.core.testConProperties.TestContainerItem;
import org.talend.testcontainer.core.ui.util.TestContainerRepositoryObjectType;

/**
 * created by Talend on Jan 7, 2015 Detailled comment
 *
 */
public class TestContainerProviderService implements ITestContainerProviderService {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ITestContainerGEFService#isMatchedPath(org.eclipse.core.runtime.IPath,
     * org.eclipse.core.runtime.IPath)
     */
    @Override
    public boolean isMatchedPath(IPath topLevelNodeWorkspaceRelativePath, IPath path) {
        int count = path.segmentCount();
        if (count < 2) {
            return false;
        }
        String jobID = path.segment(count - 2);
        if (jobID == null) {
            return false;
        }
        try {
            IRepositoryViewObject jobNode = ProxyRepositoryFactory.getInstance().getLastVersion(jobID);
            if (jobNode == null) {
                return false;
            }
            if (jobNode.getRepositoryObjectType() == null) {
                return false;
            }
            IPath workspaceRelativePath = null;
            String projectName = jobNode.getProjectLabel();
            String topLevelNodeProjectRelativePath = jobNode.getRepositoryObjectType().getFolder();
            if (projectName != null && (topLevelNodeProjectRelativePath != null && !"".equals(topLevelNodeProjectRelativePath))) { //$NON-NLS-1$
                workspaceRelativePath = Path.fromPortableString('/' + projectName).append(topLevelNodeProjectRelativePath);
            }
            if (workspaceRelativePath == null) {
                return false;
            }

            if (workspaceRelativePath.toOSString().equals(topLevelNodeWorkspaceRelativePath)) {
                return true;
            }
        } catch (PersistenceException e) {
            return false;
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.ITestContainerProviderService#isTestContainerType(org.talend.core.model.repository.
     * ERepositoryObjectType)
     */
    @Override
    public boolean isTestContainerType(ERepositoryObjectType type) {
        if (type == TestContainerRepositoryObjectType.testContainerType) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.ITestContainerProviderService#isTestContainerItem(org.talend.core.model.properties.Item)
     */
    @Override
    public boolean isTestContainerItem(Item item) {
        if (item instanceof TestContainerItem) {
            return true;
        }
        return false;
    }
}

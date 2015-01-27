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
package org.talend.testcontainer.core.ui.listener;

import org.eclipse.core.runtime.Path;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.core.repository.model.listeners.AbstractJobDeleteListener;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.testcontainer.core.ui.util.TestContainerRepositoryObjectType;

/**
 * ftang class global comment. Detailled comment
 */
public class TestContainerJobDeleteListener extends AbstractJobDeleteListener {

    private IProxyRepositoryFactory factory = null;

    private String junitFolder;

    private ERepositoryObjectType junitRepositoryObjectType;

    /**
     * ftang DeleteDocumentationAction constructor comment.
     */
    public TestContainerJobDeleteListener() {
        factory = CorePlugin.getDefault().getProxyRepositoryFactory();
    }

    @Override
    public void execute(IRepositoryObject jobObjectToDelete, int deleteType) {
        this.junitFolder = jobObjectToDelete.getRepositoryObjectType().getFolder() + "/"
                + jobObjectToDelete.getProperty().getId();
        this.junitRepositoryObjectType = TestContainerRepositoryObjectType.testContainerType;

        try {
            if (AbstractJobDeleteListener.DELETE_PHISICAL == deleteType) {
                deleteDocObjectPhysical(jobObjectToDelete);
            }
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }

    }

    /**
     * ftang method "deleteDocObjectPhysical".
     * 
     * @param jobObjectToDelete2
     * @throws PersistenceException
     */
    private void deleteDocObjectPhysical(IRepositoryObject object) throws PersistenceException {
        factory.deleteFolder(junitRepositoryObjectType, new Path(junitFolder));
    }

}

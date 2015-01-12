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
package org.talend.testcontainer.core.ui.editor;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.commons.exception.LoginException;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.process.INode;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.core.ui.editor.JobEditorInput;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.repository.RepositoryWorkUnit;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;
import org.talend.testcontainer.core.testConProperties.TestContainerItem;
import org.talend.testcontainer.core.testcontainer.TestContainer;
import org.talend.testcontainer.core.ui.models.JobTestContainerProcess;

/**
 * created by Talend on Jan 6, 2015 Detailled comment
 *
 */
public class TestContainerEditorInput extends JobEditorInput {

    private Boolean openedInJob;

    private String originalJobID;

    private List<INode> testNodes;

    /**
     * DOC qzhang TestContainerEditorInput constructor comment.
     * 
     * @param processItem
     * @param load
     * @throws PersistenceException
     */
    public TestContainerEditorInput(TestContainerItem processItem, boolean load, String originalJobID, List<INode> testNodes)
            throws PersistenceException {
        this(processItem, load, originalJobID, testNodes, null, null);
    }

    // public TestContainerEditorInput(TestContainerItem processItem, boolean load, Boolean lastVersion) throws
    // PersistenceException {
    // this(processItem, load, lastVersion, null);
    // }

    public TestContainerEditorInput(TestContainerItem processItem, boolean load, String originalJobID, List<INode> testNodes,
            Boolean lastVersion, Boolean readonly) throws PersistenceException {
        super(processItem, load, lastVersion, readonly);
        this.originalJobID = originalJobID;
        this.testNodes = testNodes;
    }

    // public TestContainerEditorInput(TestContainerItem processItem, boolean load, Boolean lastVersion, Boolean
    // readonly,
    // Boolean openedInJob) throws PersistenceException {
    // super(processItem, load, lastVersion, readonly);
    // this.openedInJob = openedInJob;
    // }

    @Override
    protected void saveProcessBefore() {
        //
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.editor.JobEditorInput#saveProcess(org.eclipse.core.runtime.IProgressMonitor,
     * org.eclipse.core.runtime.IPath, boolean)
     */
    @Override
    public boolean saveProcess(final IProgressMonitor monitor, IPath path, boolean avoidSaveRelations) {
        getLoadedProcess().setOriginalJobID(this.originalJobID);
        getLoadedProcess().setTestNodes(this.testNodes);
        try {
            if (monitor != null) {
                monitor.beginTask("save process", 100); //$NON-NLS-1$
            }

            IRepositoryService service = CoreRuntimePlugin.getInstance().getRepositoryService();
            final IProxyRepositoryFactory factory = service.getProxyRepositoryFactory();

            if (path != null) {
                // factory.createProcess(project, loadedProcess, path);
            } else {

                RepositoryWorkUnit rwu = new RepositoryWorkUnit("save process") {

                    @Override
                    protected void run() throws LoginException, PersistenceException {
                        resetItem();
                        ProcessType processType;
                        try {
                            processType = loadedProcess.saveXmlFile();
                        } catch (IOException e) {
                            throw new PersistenceException(e);
                        }
                        if (monitor != null) {
                            monitor.worked(40);
                        }
                        if (getItem() instanceof TestContainerItem) {
                            ((TestContainerItem) getItem()).setTestContainerProcess((TestContainer) processType);
                        }
                        factory.save(getItem());
                        loadedProcess.setProperty(getItem().getProperty());
                    }
                };
                rwu.setAvoidUnloadResources(true);
                rwu.setAvoidSvnUpdate(true);
                factory.executeRepositoryWorkUnit(rwu);
                if (monitor != null) {
                    monitor.worked(50);
                }
            }
            if (monitor != null) {
                monitor.worked(10);
            }
            return true;
        } catch (Exception e) {
            ExceptionHandler.process(e);
            if (monitor != null) {
                monitor.setCanceled(true);
            }
            return false;
        } finally {
            if (monitor != null) {
                monitor.done();
            }
        }

    }

    @Override
    protected JobTestContainerProcess createProcess() {
        return new JobTestContainerProcess(getItem().getProperty());
    }

    @Override
    public JobTestContainerProcess getLoadedProcess() {
        return (JobTestContainerProcess) loadedProcess;
    }

    public boolean isOpenedInJob() {
        if (openedInJob == null) {
            return false;
        }
        return this.openedInJob;
    }

    public void setOpenedInJob(Boolean openedInJob) {
        this.openedInJob = openedInJob;
    }

}

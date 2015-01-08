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

import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.model.context.JobContextManager;
import org.talend.core.model.process.IProcess2;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.core.services.ISVNProviderService;
import org.talend.core.ui.images.CoreImageProvider;
import org.talend.designer.core.ui.AbstractMultiPageTalendEditor;
import org.talend.designer.core.ui.editor.AbstractTalendEditor;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * created by Talend on Jan 6, 2015 Detailled comment
 *
 */
public class TestContainerMultiPageEditor extends AbstractMultiPageTalendEditor {

    public static final String TESTCONTAINER_ID = "org.talend.testcontainer.core.ui.editor.TestContainerMultiPageEditor"; //$NON-NLS-1$

    /**
     * DOC qzhang TalendJobletEditor constructor comment.
     */
    public TestContainerMultiPageEditor() {
        super();
        designerEditor = new TestContainerTalendEditor();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.AbstractMultiPageTalendEditor#createPages()
     */
    @Override
    protected void createPages() {
        super.createPages();
        // setPartName("Joblet " + designerEditor.getProcess().getName());
        setTitleImage(CoreImageProvider.getImage(ERepositoryObjectType.JOBLET));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.AbstractMultiPageTalendEditor#getEditorId()
     */
    @Override
    public String getEditorId() {
        return TESTCONTAINER_ID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.AbstractMultiPageTalendEditor#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void doSave(IProgressMonitor monitor) {
        if (haveDirtyJoblet()) {
            return;
        }
        // (bug 4231)
        try {

            TestContainerTalendEditor talendEdtor = (TestContainerTalendEditor) this.designerEditor;
            // boolean updateAll = UpdateJobletUtils.needUpdateAllJobs(talendEdtor);

            super.doSave(monitor);

            // UpdateJobletUtils.updateRelationship(getProcess(), talendEdtor.getOldInputMetadata(),
            // talendEdtor.getOldOutputMetadata(), updateAll);

            // talendEdtor.setOldInputMetadata(talendEdtor.getJobletNodeMetadata(ETestContainerNodeType.INPUT));
            // talendEdtor.setOldOutputMetadata(talendEdtor.getJobletNodeMetadata(ETestContainerNodeType.OUTPUT));
        } catch (Exception e) {
            ExceptionHandler.process(e);
        } finally {
            final JobContextManager manager = (JobContextManager) getProcess().getContextManager();
            manager.getNameMap().clear();
        }
    }

    @Override
    public void setName() {
        if (getEditorInput() == null) {
            return;
        }
        super.setName();
        IProcess2 process2 = this.getProcess();
        if (process2 == null) {
            return;
        }
        String label = process2.getProperty().getDisplayName();
        String jobVersion = process2.getVersion();

        setPartName("Joblet " + label + " " + jobVersion); //$NON-NLS-1$  //$NON-NLS-2$
        ISVNProviderService service = null;
        if (PluginChecker.isSVNProviderPluginLoaded()) {
            service = (ISVNProviderService) GlobalServiceRegister.getDefault().getService(ISVNProviderService.class);
            if (revisionChanged && service.isProjectInSvnMode()) {
                revisionNumStr = service.getCurrentSVNRevision(this.getProcess());
                revisionChanged = false;
                if (revisionNumStr != null) {
                    revisionNumStr = ".r" + revisionNumStr;
                }
            }
        }
        if (revisionNumStr != null) {
            if (getEditorInput() instanceof TestContainerEditorInput) {
                if (((TestContainerEditorInput) getEditorInput()).isOpenedInJob()) {
                    setPartName("Joblet " + label + " " + jobVersion + revisionNumStr + " (Opened in a job)"); //$NON-NLS-1$
                    return;
                }
            }
            setPartName("Joblet " + label + " " + jobVersion + revisionNumStr); //$NON-NLS-1$
        } else {
            if (getEditorInput() instanceof TestContainerEditorInput) {
                if (((TestContainerEditorInput) getEditorInput()).isOpenedInJob()) {
                    setPartName("Joblet " + label + " " + jobVersion + " (Opened in a job)"); //$NON-NLS-1$
                    return;
                }
            }
            setPartName("Joblet " + label + " " + jobVersion); //$NON-NLS-1$
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.AbstractMultiPageTalendEditor#updateTitleImage()
     */
    @Override
    public void updateTitleImage() {
        // TODO Auto-generated method stub

    }

    /**
     * Getter for designerEditor.
     * 
     * @return the designerEditor
     */
    @Override
    public AbstractTalendEditor getDesignerEditor() {
        return this.designerEditor;
    }

    @Override
    public boolean isSaveAsAllowed() {
        IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        if (factory.isUserReadOnlyOnCurrentProject()) {
            return false;
        }
        return true;
    }
}

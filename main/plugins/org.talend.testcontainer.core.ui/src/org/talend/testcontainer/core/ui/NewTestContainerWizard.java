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
package org.talend.testcontainer.core.ui;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.talend.commons.exception.LoginException;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.commons.utils.VersionUtils;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.routines.RoutinesUtil;
import org.talend.designer.core.model.utils.emf.talendfile.ParametersType;
import org.talend.designer.core.model.utils.emf.talendfile.RoutinesParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
import org.talend.repository.RepositoryWorkUnit;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;
import org.talend.testcontainer.core.testConProperties.TestConPropertiesFactory;
import org.talend.testcontainer.core.testConProperties.TestContainerItem;
import org.talend.testcontainer.core.testcontainer.TestContainer;
import org.talend.testcontainer.core.testcontainer.TestcontainerFactory;
import org.talend.testcontainer.core.ui.image.ETestContainerImages;

/**
 * created by Talend on Jan 6, 2015 Detailled comment
 *
 */
public class NewTestContainerWizard extends Wizard {

    private IPath path;

    private Property property;

    private TestContainerItem processItem;

    private IProxyRepositoryFactory repositoryFactory;

    private NewTestContainerPage mainPage;

    /**
     * DOC qzhang NewJobletWizard constructor comment.
     * 
     * @param path
     */
    public NewTestContainerWizard(IPath path) {
        this.path = path;

        this.property = PropertiesFactory.eINSTANCE.createProperty();
        this.property.setAuthor(((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                .getUser());
        this.property.setVersion(VersionUtils.DEFAULT_VERSION);
        this.property.setStatusCode(""); //$NON-NLS-1$

        processItem = TestConPropertiesFactory.eINSTANCE.createTestContainerItem();

        processItem.setProperty(property);

        repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();

        setDefaultPageImageDescriptor(ImageProvider.getImageDesc(ETestContainerImages.JUNIT_WIZ));

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        IRepositoryService service = CorePlugin.getDefault().getRepositoryService();
        IProxyRepositoryFactory factory = service.getProxyRepositoryFactory();
        factory.executeRepositoryWorkUnit(new RepositoryWorkUnit("create TestContainer") {

            @Override
            protected void run() throws LoginException, PersistenceException {
                // TODO Auto-generated method stub
                try {
                    property.setId(repositoryFactory.getNextId());
                    // changed by hqzhang for TDI-19527, label=displayName
                    property.setLabel(property.getDisplayName());
                    // property.getAdditionalProperties()
                    /* bug 16972,need to set a parameterType for new create joblet */
                    ParametersType parameterType = TalendFileFactory.eINSTANCE.createParametersType();
                    // add depended routines.
                    List<RoutinesParameterType> dependenciesInPreference = RoutinesUtil.createDependenciesInPreference();
                    parameterType.getRoutinesParameter().addAll(dependenciesInPreference);
                    TestContainer process = TestcontainerFactory.eINSTANCE.createTestContainer();
                    process.setParameters(parameterType);
                    processItem.setTestContainerProcess(process);
                    repositoryFactory.create(processItem, NewTestContainerWizard.this.path);
                } catch (PersistenceException e) {
                    MessageDialog.openError(getShell(), "Create test Container", e.getMessage());
                }

            }
        });

        return processItem != null;
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        mainPage = new NewTestContainerPage(property, path);
        addPage(mainPage);
        setWindowTitle("Create test Container");
    }

    /**
     * Getter for processItem.
     * 
     * @return the processItem
     */
    public TestContainerItem getProcessItem() {
        return this.processItem;
    }

}

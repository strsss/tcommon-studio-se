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

import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.metadata.managment.ui.i18n.Messages;
import org.talend.metadata.managment.ui.wizard.PropertiesWizardPage;
import org.talend.repository.model.RepositoryConstants;
import org.talend.testcontainer.core.ui.util.TestContainerRepositoryObjectType;

/**
 * created by Talend on Jan 6, 2015 Detailled comment
 *
 */
public class NewTestContainerPage extends PropertiesWizardPage {

    private static String CLASS = ".class"; //$NON-NLS-1$

    /**
     * DOC qzhang NewJobjetWizardPage constructor comment.
     * 
     * @param pageName
     * @param property
     * @param destinationPath
     */
    protected NewTestContainerPage(Property property, IPath destinationPath) {
        super("WizardPage", property, destinationPath); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.wizards.PropertiesWizardPage#getRepositoryObjectType()
     */
    @Override
    public ERepositoryObjectType getRepositoryObjectType() {
        return TestContainerRepositoryObjectType.testContainerType;
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        super.createControl(container);

        setControl(container);
        updateContent();
        addListeners();
        setPageComplete(false);

        setTitle("Create test Container");
    }

    @Override
    protected void updateContent() {
        super.updateContent();
    }

    @Override
    protected void evaluateFields() {
        super.evaluateFields();
    }

    @Override
    protected void evaluateTextField() {
        if (super.readOnly) {
            return;
        }
        if (nameText == null || nameText.isDisposed()) {
            return;
        }
        if (nameText.getText().length() == 0) {
            nameStatus = createStatus(IStatus.ERROR, Messages.getString("PropertiesWizardPage.NameEmptyError")); //$NON-NLS-1$
        } else if (!Pattern.matches(RepositoryConstants.getPattern(getRepositoryObjectType()), nameText.getText())
                || nameText.getText().trim().contains(" ")) { //$NON-NLS-1$
            nameStatus = createStatus(IStatus.ERROR, Messages.getString("PropertiesWizardPage.NameFormatError")); //$NON-NLS-1$
        } else if (JavaConventions.validateClassFileName(nameText.getText() + CLASS,
                JavaCore.getOption(JavaCore.COMPILER_SOURCE), JavaCore.getOption(JavaCore.COMPILER_COMPLIANCE)).getSeverity() == IStatus.ERROR
                || "java".equalsIgnoreCase(nameText.getText())) {//$NON-NLS-1$
            nameStatus = createStatus(IStatus.ERROR, Messages.getString("PropertiesWizardPage.KeywordsError")); //$NON-NLS-1$
        } else if (super.nameModifiedByUser) {
            if (super.retrieveNameFinished) {
                nameStatus = createOkStatus();
            } else {
                nameStatus = createStatus(IStatus.ERROR, "Looking for current items name list"); //$NON-NLS-1$
            }
        } else {
            nameStatus = createOkStatus();
        }
        if (property != null && nameStatus.getSeverity() == IStatus.OK) {
            property.setLabel(getPropertyLabel(StringUtils.trimToNull(nameText.getText())));
            property.setDisplayName(StringUtils.trimToNull(nameText.getText()));
            property.setModificationDate(new Date());
        }
        updatePageStatus();
        if (nameStatus.getSeverity() == IStatus.OK) {
            evaluateNameInRoutine();
        }
    }

}

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
import org.talend.testcontainer.core.testConProperties.TestContainerItem;

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

    /**
     * DOC qzhang NewJobjetWizardPage constructor comment.
     * 
     * @param pageName
     * @param property
     * @param destinationPath
     * @param readOnly
     * @param editPath
     */
    public NewTestContainerPage(String pageName, Property property, IPath destinationPath, boolean readOnly, boolean editPath) {
        super(pageName, property, destinationPath, readOnly, editPath);
    }

    public NewTestContainerPage(String pageName, Property property, IPath destinationPath, boolean readOnly, boolean editPath,
            String lastVersionFound) {
        super(pageName, property, destinationPath, readOnly, editPath, lastVersionFound);
    }

    /**
     * DOC qzhang NewJobjetWizardPage constructor comment.
     * 
     * @param pageName
     * @param property
     * @param destinationPath
     */
    public NewTestContainerPage(String pageName, Property property, IPath destinationPath) {
        super(pageName, property, destinationPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.wizards.PropertiesWizardPage#getRepositoryObjectType()
     */
    @Override
    public ERepositoryObjectType getRepositoryObjectType() {
        return ERepositoryObjectType.JOBLET;
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
        // createIconPart(container);

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
                // if (!isValid(nameText.getText())) {
                //                    nameStatus = createStatus(IStatus.ERROR, Messages.getString("PropertiesWizardPage.ItemExistsError")); //$NON-NLS-1$
                // } else {
                nameStatus = createOkStatus();
                // }
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

    private TestContainerItem getTestContainerItem() {
        return (TestContainerItem) getProperty().getItem();
    }

    // private void createIconPart(Composite controlContainer) {
    // // icon
    // Label iconLabel = new Label(controlContainer, SWT.NONE);
    //        iconLabel.setText(Messages.getString("NewJobjetWizardPage.icon")); //$NON-NLS-1$
    //
    // final Composite iconContainer = new Composite(controlContainer, SWT.NONE);
    // iconContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    // GridLayout versionLayout = new GridLayout(3, false);
    // versionLayout.marginHeight = 10;
    // versionLayout.marginWidth = 10;
    // versionLayout.horizontalSpacing = 10;
    // iconContainer.setLayout(versionLayout);
    //
    // GridData gd = new GridData();
    // gd.widthHint = 36;
    // gd.heightHint = 36;
    //
    // // Button iconButton = new Button(iconContainer, SWT.PUSH);
    // // iconButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
    //        //        iconButton.setText("..."); //$NON-NLS-1$ 
    // // iconButton.setEnabled(!isReadOnly());
    //
    // // Button iconButtondef = new Button(iconContainer, SWT.PUSH);
    // // iconButtondef.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
    //        //        iconButtondef.setText(Messages.getString("NewJobjetWizardPage.revert")); //$NON-NLS-1$
    // // iconButtondef.setEnabled(!isReadOnly());
    // // // iconButtondef.addSelectionListener(new SelectionListener() {
    // //
    // // @Override
    // // public void widgetDefaultSelected(SelectionEvent e) {
    // //
    // // }
    // //
    // // @Override
    // // public void widgetSelected(SelectionEvent e) {
    // // Image defaultIcon = RepositoryLabelProvider.getDefaultJobletImage();
    // // refreshIcon(defaultIcon);
    // // }
    // //
    // // });
    //
    // // iconButton.addSelectionListener(new SelectionAdapter() {
    // //
    // // @Override
    // // public void widgetSelected(SelectionEvent e) {
    // // FileDialog fd = new FileDialog(getShell());
    //        //                fd.setFilterExtensions(new String[] { "*.jpg", "*.png", "*.gif" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    // // String filename = fd.open();
    // // if (filename != null) {
    // // ImageData imageData = new ImageData(filename);
    // // Image image = new Image(getShell().getDisplay(), imageData);
    // // refreshIcon(image);
    // // updatePageStatus();
    // // }
    // // }
    // // });
    //
    // }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.wizards.PropertiesWizardPage#isValid(java.lang.String)
     */
    @Override
    public boolean isValid(String itemName) {
        boolean valid = super.isValid(itemName);
        // List<IRepositoryViewObject> listExistingObjects = getListExistingObjects();
        // if (valid) {
        // Set<IComponent> components = ComponentsFactoryProvider.getInstance().getComponents();
        // for (IComponent component : components) {
        // }
        // }
        return valid;
    }

    // /**
    // * DOC Administrator Comment method "refreshIcon".
    // *
    // * @param imageData
    // */
    // private void refreshIcon(Image image) {
    // if (!ImageUtils.checkSize(image, ImageUtils.ICON_SIZE.ICON_32)) {
    //            nameStatus = createStatus(IStatus.ERROR, Messages.getString("PropertiesWizardPage.ImageSizeError")); //$NON-NLS-1$
    //
    // } else {
    // evaluateFields();
    // // nameStatus = createOkStatus();
    // }
    // iconShowLabel.setImage(image);
    // TestContainerItem item = getJobletItem();
    // byte[] data = ImageUtils.saveImageToData(ImageDescriptor.createFromImageData(image.getImageData()));
    // ByteArray icon = item.getIcon();
    // if (icon == null) {
    // icon = TestConPropertiesFactory.eINSTANCE.createByteArray();
    // item.setIcon(icon);
    // }
    // icon.setInnerContent(data);
    // }
}

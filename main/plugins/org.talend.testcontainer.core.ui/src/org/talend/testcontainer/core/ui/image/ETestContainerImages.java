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
package org.talend.testcontainer.core.ui.image;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;
import org.talend.commons.ui.runtime.image.IImage;
import org.talend.testcontainer.core.ui.Messages;
import org.talend.testcontainer.core.ui.TestContainerPlugin;

/**
 * DOC stephane class global comment. Detailled comment
 */
public enum ETestContainerImages implements IImage {
    JUNIT_ICON("/icons/joblet_icon.png"), //$NON-NLS-1$
    JUNIT_ICON_NB("/icons/joblet_icon_nb.png"), //$NON-NLS-1$
    JUNIT_WIZ("/icons/defaultWizard.png"), //$NON-NLS-1$

    JUNIT_INPUT_OUTPUT_COMPONENT("/icons/jobletInputOutput.png"), //$NON-NLS-1$
    JUNIT_COMPONENT_32("/icons/joblet_palette.png"), //$NON-NLS-1$
    JUNIT_COMPONENT_16("/icons/joblet_icon.png"), //$NON-NLS-1$
    JUNIT_TRIGGER_COMPONENT("/icons/jobletInputOutput.png"), //$NON-NLS-1$
    JUNIT_TRIGGER_COMPONENT_SMALL("/icons/joblet_icon_nb.png"), //$NON-NLS-1$
    ;

    private String path;

    ETestContainerImages(String path) {
        this.path = path;
    }

    /**
     * Getter for path.
     * 
     * @return the path
     */
    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * Getter for clazz.
     * 
     * @return the clazz
     */
    @Override
    public Class getLocation() {
        return TestContainerPlugin.class;
    }

    /**
     * DOC qzhang Comment method "getURLImageDescriptor".
     * 
     * @param images
     * @return
     */
    public static ImageDescriptor getURLImageDescriptor(ETestContainerImages images) {
        Bundle b = TestContainerPlugin.getDefault().getBundle();
        URL url = null;
        try {
            url = FileLocator.toFileURL(FileLocator.find(b, new Path(images.getPath()), null));
            return ImageDescriptor.createFromURL(url);
        } catch (IOException e) {
            throw new RuntimeException(Messages.ETestContainerNodeType_imageNotExist);
        }
    }
}

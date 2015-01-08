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

import org.eclipse.osgi.util.NLS;

/**
 * created by Talend on Jan 7, 2015 Detailled comment
 *
 */
public class Messages extends NLS {

    private static final String BUNDLE_NAME = "org.talend.testcontainer.core.messages"; //$NON-NLS-1$

    public static String ETestContainerNodeType_INPUT;

    public static String ETestContainerNodeType_INPUT_MESS;

    public static String ETestContainerNodeType_imageNotExist;
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}

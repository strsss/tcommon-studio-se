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
package org.talend.testcontainer.core.ui.models;

import org.talend.testcontainer.core.ui.Messages;

/**
 * DOC qzhang class global comment. Detailled comment
 */
public enum ETestContainerNodeType {
    INPUT(Messages.ETestContainerNodeType_INPUT, Messages.ETestContainerNodeType_INPUT_MESS),
    OUTPUT("Output", "this is output"), //$NON-NLS-1$ //$NON-NLS-2$
    TRIGGER_INPUT("Trigger Input", "this is trigger input"), //$NON-NLS-1$ //$NON-NLS-2$
    TRIGGER_OUTPUT("Trigger Output", "this is trigger output"), //$NON-NLS-1$ //$NON-NLS-2$
    ELEMENT("", ""); //$NON-NLS-1$ //$NON-NLS-2$

    String displayName;

    String longName;

    ETestContainerNodeType(String displayName, String longName) {
        this.displayName = displayName;
        this.longName = longName;
    }

    /**
     * Getter for displayName.
     * 
     * @return the displayName
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Getter for longName.
     * 
     * @return the longName
     */
    public String getLongName() {
        return this.longName;
    }
}

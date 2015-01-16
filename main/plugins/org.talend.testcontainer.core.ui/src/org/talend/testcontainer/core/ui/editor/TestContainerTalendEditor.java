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
package org.talend.testcontainer.core.ui.editor;

import java.util.List;

import org.eclipse.ui.IEditorInput;
import org.talend.core.model.components.IComponentsHandler;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.designer.core.ui.editor.AbstractTalendEditor;
import org.talend.designer.core.ui.editor.ProcessComponentsHandler;

/**
 * DOC qzhang class global comment. Detailled comment
 */
public class TestContainerTalendEditor extends AbstractTalendEditor {

    private List<IMetadataTable> oldInputMetadata;

    private List<IMetadataTable> oldOutputMetadata;

    /**
     * DOC qzhang TestContainerTalendEditor constructor comment.
     */
    public TestContainerTalendEditor() {
    }

    /**
     * DOC qzhang TestContainerTalendEditor constructor comment.
     * 
     * @param readOnly
     */
    public TestContainerTalendEditor(boolean readOnly) {
        super(readOnly);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.AbstractTalendEditor#setInput(org.eclipse.ui.IEditorInput)
     */
    @Override
    protected void setInput(IEditorInput input) {
        super.setInput(input);
    }

    /**
     * Getter for oldInputMetadata.
     * 
     * @return the oldInputMetadata
     */
    public List<IMetadataTable> getOldInputMetadata() {
        return this.oldInputMetadata;
    }

    /**
     * Sets the oldInputMetadata.
     * 
     * @param oldInputMetadata the oldInputMetadata to set
     */
    public void setOldInputMetadata(List<IMetadataTable> oldInputMetadata) {
        this.oldInputMetadata = oldInputMetadata;
    }

    /**
     * Getter for oldOutputMetadata.
     * 
     * @return the oldOutputMetadata
     */
    public List<IMetadataTable> getOldOutputMetadata() {
        return this.oldOutputMetadata;
    }

    /**
     * Sets the oldOutputMetadata.
     * 
     * @param oldOutputMetadata the oldOutputMetadata to set
     */
    public void setOldOutputMetadata(List<IMetadataTable> oldOutputMetadata) {
        this.oldOutputMetadata = oldOutputMetadata;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.AbstractTalendEditor#initComponentsHandler()
     */
    @Override
    protected IComponentsHandler initComponentsHandler() {
        return new ProcessComponentsHandler();
    }

}

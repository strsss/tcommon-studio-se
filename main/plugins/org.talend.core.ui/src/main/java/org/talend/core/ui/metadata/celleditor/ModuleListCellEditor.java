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
package org.talend.core.ui.metadata.celleditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView;
import org.talend.commons.ui.swt.extended.table.AbstractExtendedTableViewer;
import org.talend.commons.ui.swt.formtools.LabelledFileField;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.process.IElement;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.IGEFProcess;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess2;
import org.talend.core.service.IDesignerCoreUIService;
import org.talend.core.ui.CoreUIPlugin;
import org.talend.core.ui.i18n.Messages;

/**
 * ggu class global comment. Detailled comment
 */
public class ModuleListCellEditor extends DialogCellEditor {

    private final IElementParameter param, tableParam;

    private AbstractDataTableEditorView<?> tableEditorView;

    public ModuleListCellEditor(Composite parent, IElementParameter param, IElementParameter tableParam) {
        super(parent, SWT.NONE);
        this.param = param;
        this.tableParam = tableParam;
    }

    private void executeCommand(Command cmd) {
        IProcess2 process = null;
        if (tableParam != null) {
            if (tableParam.getElement() instanceof IProcess2) {
                process = (IProcess2) tableParam.getElement();
            } else if (tableParam.getElement() instanceof INode) {
                if (((INode) tableParam.getElement()).getProcess() instanceof IProcess2) {
                    process = (IProcess2) ((INode) tableParam.getElement()).getProcess();
                }
            }
        }

        boolean executed = false;
        if (process != null && process instanceof IGEFProcess) {
            IDesignerCoreUIService designerCoreUIService = CoreUIPlugin.getDefault().getDesignerCoreUIService();
            if (designerCoreUIService != null) {
                executed = designerCoreUIService.executeCommand((IGEFProcess) process, cmd);
            }
        }
        if (!executed) {
            cmd.execute();
        }
    }

    public AbstractDataTableEditorView<?> getTableEditorView() {
        return this.tableEditorView;
    }

    private TableViewer getTableViewer() {
        if (this.tableEditorView != null) {
            AbstractExtendedTableViewer<?> extendedTableViewer = this.tableEditorView.getExtendedTableViewer();
            if (extendedTableViewer != null) {
                TableViewerCreator<?> tableViewerCreator = extendedTableViewer.getTableViewerCreator();
                if (tableViewerCreator != null) {
                    return tableViewerCreator.getTableViewer();
                }
            }
        }
        return null;
    }

    public void setTableEditorView(AbstractDataTableEditorView<?> tableEditorView) {
        this.tableEditorView = tableEditorView;
    }

    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        String value = (String) getValue();
        ModuleListDialog dialog = new ModuleListDialog(cellEditorWindow.getShell(), value);
        if (dialog.open() == Window.OK) {
            String selecteModule = dialog.getSelecteModule();
            if (selecteModule != null && (value == null || !value.equals(selecteModule))) {
                int index = 0;
                if (getTableViewer() != null) {
                    index = getTableViewer().getTable().getSelectionIndex();
                }
                // enable to refresh component setting after change modules.
                IElement element = this.tableParam.getElement();
                if (element != null) {
                    IElementParameter updateComponentsParam = element.getElementParameter("UPDATE_COMPONENTS"); //$NON-NLS-1$
                    if (updateComponentsParam != null) {
                        updateComponentsParam.setValue(Boolean.TRUE);
                    }
                }
                //
                executeCommand(new ModelChangeCommand(tableParam, param.getName(), selecteModule, index));
                if (getTableViewer() != null) {
                    getTableViewer().refresh(true);
                }
                return selecteModule;
            }
        }
        return null;
    }

    /**
     * 
     * ggu ModelChangeCommand class global comment. Detailled comment
     */
    class ModelChangeCommand extends Command {

        private final IElementParameter param;

        private final String columnName;

        private final String value;

        private final int index;

        public ModelChangeCommand(IElementParameter param, String columnName, String value, int index) {
            super();
            this.param = param;
            this.columnName = columnName;
            this.value = value;
            this.index = index;
        }

        @Override
        public void execute() {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> values = (List<Map<String, Object>>) param.getValue();
            if (values == null) {
                values = new ArrayList<Map<String, Object>>();
                param.setValue(values);
            }
            if (values.size() > index) {
                Map<String, Object> line = values.get(index);
                if (line != null) {
                    line.put(columnName, value);
                }
            }
            // CorePlugin.getDefault().getLibrariesService().resetModulesNeeded();
        }

        @Override
        public void undo() {
            super.undo();
        }

    }

    /**
     * 
     * ggu ModuleListDialog class global comment. Detailled comment
     */
    class ModuleListDialog extends Dialog {

        private String selecteModule;

        private Button innerBtn, extBtn;

        private ListViewer jarsViewer;

        private LabelledFileField selectField;

        private boolean isInner;

        protected ModuleListDialog(Shell parentShell, String selecteModule) {
            super(parentShell);
            this.selecteModule = selecteModule;
            this.setShellStyle(getShellStyle() | SWT.RESIZE);
        }

        @Override
        protected void configureShell(Shell newShell) {
            super.configureShell(newShell);
            newShell.setText(Messages.getString("ModuleListCellEditor.title")); //$NON-NLS-1$
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.dialogs.Dialog#create()
         */
        @Override
        public void create() {
            super.create();
            checkField(true); // init
        }

        @Override
        protected Control createDialogArea(Composite parent) {
            Composite composite = (Composite) super.createDialogArea(parent);

            Group group = new Group(composite, SWT.NONE);
            group.setLayout(new GridLayout(2, false));
            group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            innerBtn = new Button(group, SWT.RADIO);
            innerBtn.setText(Messages.getString("ModuleListCellEditor.innerLabel")); //$NON-NLS-1$
            extBtn = new Button(group, SWT.RADIO);
            extBtn.setText(Messages.getString("ModuleListCellEditor.externalLabel")); //$NON-NLS-1$

            Composite comp = new Composite(composite, SWT.NONE);
            comp.setLayout(new GridLayout());
            GridData layoutData = new GridData(GridData.FILL_BOTH);
            layoutData.minimumHeight = 150;
            layoutData.minimumWidth = 450;
            layoutData.heightHint = 150;
            layoutData.widthHint = 450;
            comp.setLayoutData(layoutData);

            //
            jarsViewer = new ListViewer(comp, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
            jarsViewer.setContentProvider(new ArrayContentProvider());
            jarsViewer.setLabelProvider(new LabelProvider() {

                @Override
                public Image getImage(Object element) {
                    String text = getText(element);
                    if (text != null && !text.equals(selecteModule)) {
                        return ImageProvider.getImage(EImage.LOCK_ICON);
                    }
                    return super.getImage(element);
                }

            });
            jarsViewer.setInput(param.getListItemsDisplayName());
            jarsViewer.getList().setLayoutData(new GridData(GridData.FILL_BOTH));

            Composite c = new Composite(comp, SWT.NONE);
            c.setLayout(new GridLayout(3, false));
            c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            selectField = new LabelledFileField(c,
                    Messages.getString("ModuleListCellEditor.selectLabel"), FilesUtils.getAcceptJARFilesSuffix()); //$NON-NLS-1$

            addListeners();
            // checkField(true); // init
            jarsViewer.getList().setSelection(new String[] { selecteModule });
            return composite;
        }

        private void addListeners() {
            innerBtn.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    checkField(true);
                }

            });
            extBtn.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    checkField(false);
                }

            });
            jarsViewer.addSelectionChangedListener(new ISelectionChangedListener() {

                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    if (jarsViewer.getList().getSelection().length <= 0) {
                        getOKButton().setEnabled(false);
                    } else {
                        getOKButton().setEnabled(true);
                    }
                }
            });
            selectField.addModifyListener(new ModifyListener() {

                @Override
                public void modifyText(ModifyEvent e) {
                    if (selectField.getText().trim().length()<=0) {
                        getOKButton().setEnabled(false);
                    } else {
                        getOKButton().setEnabled(true);
                    }
                }
            });
        }

        private void checkField(boolean inner) {
            innerBtn.setSelection(inner);
            extBtn.setSelection(!inner);
            jarsViewer.getList().setVisible(inner);
            ((GridData) jarsViewer.getList().getLayoutData()).exclude = !inner;
            selectField.getTextControl().getParent().setVisible(!inner);
            ((GridData) selectField.getTextControl().getParent().getLayoutData()).exclude = inner;
            jarsViewer.getList().getParent().layout();
            boolean canFinish = true;
            if (innerBtn.getSelection()) {
                if (jarsViewer.getList().getSelection().length <= 0) {
                    canFinish = false;
                }

            } else {
                String fileName = selectField.getText().trim();
                if (fileName.length() <= 0) {
                    canFinish = false;
                }
            }
            if (getOKButton() != null) {
                getOKButton().setEnabled(canFinish);
            }
        }

        public String getSelecteModule() {
            return this.selecteModule;
        }

        public boolean isInner() {
            return isInner;
        }

        @Override
        protected void okPressed() {
            isInner = innerBtn.getSelection();
            if (isInner) {
                if (jarsViewer.getList().getSelection().length > 0) {
                    selecteModule = jarsViewer.getList().getSelection()[0];
                }
            } else {
                IPath path = Path.fromOSString(selectField.getText());
                File source = path.toFile();
                if (!source.exists()) {
                    MessageDialog.openWarning(getParentShell(), "File Not Found", path
                            + " is not Found,Please make sure the file is exist!");
                    return;
                }
                String lastSegment = path.lastSegment();
                try {
                    if (GlobalServiceRegister.getDefault().isServiceRegistered(ILibrariesService.class)) {
                        ILibrariesService service = (ILibrariesService) GlobalServiceRegister.getDefault().getService(
                                ILibrariesService.class);
                        service.deployLibrary(path.toFile().toURI().toURL());
                    }
                } catch (IOException ee) {
                    ExceptionHandler.process(ee);
                }
                selecteModule = lastSegment;
            }
            super.okPressed();
        }
    }
}

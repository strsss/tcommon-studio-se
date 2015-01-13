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
package org.talend.testcontainer.core.ui.service;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.talend.core.model.process.Element;
import org.talend.core.model.process.IProcess2;
import org.talend.designer.core.ITestContainerGEFService;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainer;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.testcontainer.core.ui.model.JunitContainer;
import org.talend.testcontainer.core.ui.model.JunitContainerPart;
import org.talend.testcontainer.core.ui.models.AbstractTestContainer;

/**
 * created by Talend on Jan 12, 2015 Detailled comment
 *
 */
public class TestContainerGEFService implements ITestContainerGEFService {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.ui.ITestContainerGEFService#createEditorPart(java.lang.Object)
     */
    @Override
    public EditPart createEditorPart(Object model) {
        if (model == null) {
            return null;
        }
        if (model instanceof JunitContainer) {
            EditPart part = new JunitContainerPart();
            return part;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ITestContainerGEFService#getJunitContainer(java.lang.Object)
     */
    @Override
    public Element getJunitContainer(IProcess2 process) {
        if (process instanceof AbstractTestContainer) {
            ((AbstractTestContainer) process).getJunitContainer();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ITestContainerGEFService#getJunitContainer(java.util.List)
     */
    @Override
    public NodeContainer createJunitContainer(List<Node> nodes) {
        return new JunitContainer(nodes);
    }

}

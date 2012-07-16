// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.model.actions;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.RepositoryViewObject;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.RepositoryNode;

/**
 * 
 * DOC hcyi class global comment. Detailled comment
 */
public class MoveObjectActionTest {

    @Test
    public void TestValidateAction() {
        // mock
        RepositoryNode sourceNode = mock(RepositoryNode.class);
        RepositoryViewObject object = mock(RepositoryViewObject.class);
        Property property = mock(Property.class);
        Item item = mock(Item.class);
        // when
        when(sourceNode.getObject()).thenReturn(object);
        when(object.getRepositoryObjectType()).thenReturn(ERepositoryObjectType.SERVICESOPERATION);
        when(object.getProperty()).thenReturn(property);
        when(object.getRepositoryStatus()).thenReturn(ERepositoryStatus.DEFAULT);
        when(property.getItem()).thenReturn(item);
        //
        MoveObjectAction moveObjectAction = MoveObjectAction.getInstance();
        assertFalse(moveObjectAction.validateAction(sourceNode, null, true));
    }
}

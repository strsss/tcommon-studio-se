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
package org.talend.core.ui;

import org.eclipse.core.runtime.IPath;
import org.talend.core.IService;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 * created by hwang on Jan 7, 2015 Detailled comment
 *
 */
public interface ITestContainerProviderService extends IService {

    public boolean isMatchedPath(IPath topLevelNodeWorkspaceRelativePath, IPath path);

    public boolean isTestContainerType(ERepositoryObjectType type);

    public boolean isTestContainerItem(Item item);
}

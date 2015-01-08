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
package org.talend.testcontainer.core.ui.content;

import org.talend.commons.ui.runtime.image.IImage;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.viewer.label.RepositoryViewLabelProvider;
import org.talend.testcontainer.core.ui.image.ETestContainerImages;
import org.talend.testcontainer.core.ui.util.TestContainerRepositoryObjectType;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class TestContainerLabelProvider extends RepositoryViewLabelProvider {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.viewer.label.AbstractRepoViewLabelProvider#getIcon(org.talend.core.model.repository.
     * ERepositoryObjectType)
     */
    @Override
    protected IImage getIcon(ERepositoryObjectType itemType) {
        if (itemType != null && itemType.equals(TestContainerRepositoryObjectType.TEST_CONTAINER)) {
            return ETestContainerImages.JOBLET_WIZ;
        }
        return null;
    }

}

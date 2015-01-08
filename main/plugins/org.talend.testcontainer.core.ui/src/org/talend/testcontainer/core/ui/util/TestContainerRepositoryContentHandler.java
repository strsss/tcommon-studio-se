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
package org.talend.testcontainer.core.ui.util;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.image.IImage;
import org.talend.core.model.properties.Item;
import org.talend.core.model.repository.AbstractRepositoryContentHandler;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.repository.utils.XmiResourceManager;
import org.talend.testcontainer.core.testConProperties.TestConPropertiesFactory;
import org.talend.testcontainer.core.testConProperties.TestConPropertiesPackage;
import org.talend.testcontainer.core.testConProperties.TestContainerItem;

/**
 * DOC ggu class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class TestContainerRepositoryContentHandler extends AbstractRepositoryContentHandler {

    private XmiResourceManager xmiResourceManager = new XmiResourceManager();

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#create(org.eclipse.core.resources.IProject,
     * org.talend.core.model.properties.Item, int, org.eclipse.core.runtime.IPath)
     */
    @Override
    public Resource create(IProject project, Item item, int classifierID, IPath path) throws PersistenceException {
        Resource itemResource = null;
        ERepositoryObjectType type;
        if (item.eClass() == TestConPropertiesPackage.Literals.TEST_CONTAINER_ITEM) {
            type = TestContainerRepositoryObjectType.testContainerType;
            itemResource = create(project, (TestContainerItem) item, path, type);
            return itemResource;
        }
        return null;
    }

    private Resource create(IProject project, TestContainerItem item, IPath path, ERepositoryObjectType type)
            throws PersistenceException {
        Resource itemResource = xmiResourceManager.createItemResource(project, item, path, type, false);
        itemResource.getContents().add(item.getTestContainerProcess());
        return itemResource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.repository.AbstractRepositoryContentHandler#createScreenShotResource(org.eclipse.core.resources
     * .IProject, org.talend.core.model.properties.Item, int, org.eclipse.core.runtime.IPath)
     */
    @Override
    public Resource createScreenShotResource(IProject project, Item item, int classifierID, IPath path)
            throws PersistenceException {
        Resource screenShotResource = null;
        if (item.eClass() == TestConPropertiesPackage.Literals.TEST_CONTAINER_ITEM) {
            return xmiResourceManager.createScreenshotResource(project, item, path, getProcessType(), false);
        }
        return screenShotResource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#save(org.talend.core.model.properties.Item)
     */
    @Override
    public Resource save(Item item) throws PersistenceException {
        if (item instanceof TestContainerItem) {
            // Avoid to save the screenshot resources to item file.
            ((TestContainerItem) item).getTestContainerProcess().setScreenshot(null);
        }
        Resource itemResource = null;
        if (item.eClass() == TestConPropertiesPackage.Literals.TEST_CONTAINER_ITEM) {
            itemResource = xmiResourceManager.getItemResource(item);
            itemResource.getContents().clear();
            itemResource.getContents().add(((TestContainerItem) item).getTestContainerProcess());
        }
        return itemResource;
    }

    @Override
    public Resource saveScreenShots(Item item) throws PersistenceException {
        if (item instanceof TestContainerItem) {
            Resource itemResource = xmiResourceManager.getScreenshotResource(item, true);
            EMap screenshots = null;
            screenshots = ((TestContainerItem) item).getTestContainerProcess().getScreenshots();
            if (screenshots != null && !screenshots.isEmpty()) {
                itemResource.getContents().clear();
                itemResource.getContents().addAll(EcoreUtil.copyAll(screenshots));
            }
            return itemResource;
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.AbstractRepositoryContentHandler#getIcon(org.talend.core.model.repository.
     * ERepositoryObjectType)
     */
    @Override
    public IImage getIcon(ERepositoryObjectType type) {
        if (isRepObjType(type)) {
            return null;// EMRProcessImage.PROCESS_MR_ICON;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.repository.AbstractRepositoryContentHandler#getIcon(org.talend.core.model.properties.Item)
     */
    @Override
    public IImage getIcon(Item item) {
        return getIcon(getRepositoryObjectType(item));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#createNewItem(org.talend.core.model.repository.
     * ERepositoryObjectType)
     */
    @Override
    public Item createNewItem(ERepositoryObjectType type) {
        if (isRepObjType(type)) {
            return TestConPropertiesFactory.eINSTANCE.createTestContainerItem();
        }
        return null;
    }

    @Override
    public boolean isProcess(Item item) {
        return isRepObjType(getRepositoryObjectType(item));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.model.repository.IRepositoryContentHandler#isRepObjType(org.talend.core.model.repository.
     * ERepositoryObjectType)
     */
    @Override
    public boolean isRepObjType(ERepositoryObjectType type) {
        return type != null && type.equals(TestContainerRepositoryObjectType.testContainerType);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.repository.IRepositoryContentHandler#getRepositoryObjectType(org.talend.core.model.properties
     * .Item)
     */
    @Override
    public ERepositoryObjectType getRepositoryObjectType(Item item) {
        if (item.eClass() == TestConPropertiesPackage.Literals.TEST_CONTAINER_ITEM) {
            return TestContainerRepositoryObjectType.testContainerType;
        }
        return null;
    }

    @Override
    public ERepositoryObjectType getProcessType() {
        return TestContainerRepositoryObjectType.testContainerType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.talend.core.model.repository.IRepositoryContentHandler#copyScreenShotFile(org.talend.core.model.properties
     * .Item, org.talend.core.model.properties.Item)
     */
    @Override
    public void copyScreenShotFile(Item originalItem, Item newItem) throws IOException {
        if (newItem.eClass() == TestConPropertiesPackage.Literals.TEST_CONTAINER_ITEM) {
            xmiResourceManager.copyScreenshotFile(originalItem, newItem);
        }
    }
}

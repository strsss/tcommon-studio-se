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
package org.talend.testcontainer.core.ui;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.testcontainer.core.ui.listener.TestContainerJobDeleteListener;

/**
 * The activator class controls the plug-in life cycle
 */
public class TestContainerPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.talend.testcontainer.core"; //$NON-NLS-1$

    // The shared instance
    private static TestContainerPlugin plugin;

    /** Context. */
    private Context userContext;

    private List<PropertyChangeListener> propertyListener = new ArrayList<PropertyChangeListener>();

    /**
     * The constructor
     */
    public TestContainerPlugin() {
        plugin = this;
        userContext = new Context();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        addJunitListener(factory, new TestContainerJobDeleteListener());
    }

    private void addJunitListener(IProxyRepositoryFactory factory, PropertyChangeListener listener) {
        if (factory == null || listener == null) {
            return;
        }
        propertyListener.add(listener);
        factory.addPropertyChangeListener(listener);
    }

    private void removeJunitListener() {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        if (factory == null) {
            return;
        }
        if (propertyListener == null || propertyListener.isEmpty()) {
            return;
        }
        for (PropertyChangeListener listener : propertyListener) {
            factory.removePropertyChangeListener(listener);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
        removeJunitListener();
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static TestContainerPlugin getDefault() {
        return plugin;
    }

    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
}

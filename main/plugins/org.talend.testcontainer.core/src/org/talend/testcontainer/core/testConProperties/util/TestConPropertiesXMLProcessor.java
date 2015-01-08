/**
 */
package org.talend.testcontainer.core.testConProperties.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.talend.testcontainer.core.testConProperties.TestConPropertiesPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TestConPropertiesXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestConPropertiesXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        TestConPropertiesPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the TestConPropertiesResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Map getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new TestConPropertiesResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new TestConPropertiesResourceFactoryImpl());
        }
        return registrations;
    }

} //TestConPropertiesXMLProcessor

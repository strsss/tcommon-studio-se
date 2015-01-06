/**
 */
package testcontainer.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import testcontainer.TestcontainerPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TestcontainerXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestcontainerXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        TestcontainerPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the TestcontainerResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new TestcontainerResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new TestcontainerResourceFactoryImpl());
        }
        return registrations;
    }

} //TestcontainerXMLProcessor

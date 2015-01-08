/**
 */
package org.talend.testcontainer.core.testConProperties.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.talend.testcontainer.core.testConProperties.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestConPropertiesFactoryImpl extends EFactoryImpl implements TestConPropertiesFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static TestConPropertiesFactory init() {
        try {
            TestConPropertiesFactory theTestConPropertiesFactory = (TestConPropertiesFactory)EPackage.Registry.INSTANCE.getEFactory(TestConPropertiesPackage.eNS_URI);
            if (theTestConPropertiesFactory != null) {
                return theTestConPropertiesFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TestConPropertiesFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestConPropertiesFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case TestConPropertiesPackage.TEST_CONTAINER_ITEM: return createTestContainerItem();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestContainerItem createTestContainerItem() {
        TestContainerItemImpl testContainerItem = new TestContainerItemImpl();
        return testContainerItem;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestConPropertiesPackage getTestConPropertiesPackage() {
        return (TestConPropertiesPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    public static TestConPropertiesPackage getPackage() {
        return TestConPropertiesPackage.eINSTANCE;
    }

} //TestConPropertiesFactoryImpl

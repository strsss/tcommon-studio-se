/**
 */
package org.talend.testcontainer.core.testConProperties;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.talend.testcontainer.core.testConProperties.TestConPropertiesPackage
 * @generated
 */
public interface TestConPropertiesFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    TestConPropertiesFactory eINSTANCE = org.talend.testcontainer.core.testConProperties.impl.TestConPropertiesFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Test Container Item</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Test Container Item</em>'.
     * @generated
     */
    TestContainerItem createTestContainerItem();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    TestConPropertiesPackage getTestConPropertiesPackage();

} //TestConPropertiesFactory

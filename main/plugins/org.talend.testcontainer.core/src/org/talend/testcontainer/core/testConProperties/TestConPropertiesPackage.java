/**
 */
package org.talend.testcontainer.core.testConProperties;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.talend.core.model.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.talend.testcontainer.core.testConProperties.TestConPropertiesFactory
 * @model kind="package"
 * @generated
 */
public interface TestConPropertiesPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "testConProperties";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.talend.com/testConProperties.ecore";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "testConProperties";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    TestConPropertiesPackage eINSTANCE = org.talend.testcontainer.core.testConProperties.impl.TestConPropertiesPackageImpl.init();

    /**
     * The meta object id for the '{@link org.talend.testcontainer.core.testConProperties.impl.TestContainerItemImpl <em>Test Container Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.testcontainer.core.testConProperties.impl.TestContainerItemImpl
     * @see org.talend.testcontainer.core.testConProperties.impl.TestConPropertiesPackageImpl#getTestContainerItem()
     * @generated
     */
    int TEST_CONTAINER_ITEM = 0;

    /**
     * The feature id for the '<em><b>Property</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_ITEM__PROPERTY = PropertiesPackage.ITEM__PROPERTY;

    /**
     * The feature id for the '<em><b>State</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_ITEM__STATE = PropertiesPackage.ITEM__STATE;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_ITEM__PARENT = PropertiesPackage.ITEM__PARENT;

    /**
     * The feature id for the '<em><b>Reference Resources</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_ITEM__REFERENCE_RESOURCES = PropertiesPackage.ITEM__REFERENCE_RESOURCES;

    /**
     * The feature id for the '<em><b>File Extension</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_ITEM__FILE_EXTENSION = PropertiesPackage.ITEM__FILE_EXTENSION;

    /**
     * The feature id for the '<em><b>Need Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_ITEM__NEED_VERSION = PropertiesPackage.ITEM__NEED_VERSION;

    /**
     * The feature id for the '<em><b>Test Container Process</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_ITEM__TEST_CONTAINER_PROCESS = PropertiesPackage.ITEM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Test Container Item</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_ITEM_FEATURE_COUNT = PropertiesPackage.ITEM_FEATURE_COUNT + 1;


    /**
     * Returns the meta object for class '{@link org.talend.testcontainer.core.testConProperties.TestContainerItem <em>Test Container Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Test Container Item</em>'.
     * @see org.talend.testcontainer.core.testConProperties.TestContainerItem
     * @generated
     */
    EClass getTestContainerItem();

    /**
     * Returns the meta object for the reference '{@link org.talend.testcontainer.core.testConProperties.TestContainerItem#getTestContainerProcess <em>Test Container Process</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Test Container Process</em>'.
     * @see org.talend.testcontainer.core.testConProperties.TestContainerItem#getTestContainerProcess()
     * @see #getTestContainerItem()
     * @generated
     */
    EReference getTestContainerItem_TestContainerProcess();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TestConPropertiesFactory getTestConPropertiesFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.talend.testcontainer.core.testConProperties.impl.TestContainerItemImpl <em>Test Container Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.testcontainer.core.testConProperties.impl.TestContainerItemImpl
         * @see org.talend.testcontainer.core.testConProperties.impl.TestConPropertiesPackageImpl#getTestContainerItem()
         * @generated
         */
        EClass TEST_CONTAINER_ITEM = eINSTANCE.getTestContainerItem();

        /**
         * The meta object literal for the '<em><b>Test Container Process</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TEST_CONTAINER_ITEM__TEST_CONTAINER_PROCESS = eINSTANCE.getTestContainerItem_TestContainerProcess();

    }

} //TestConPropertiesPackage

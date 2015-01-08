/**
 */
package org.talend.testcontainer.core.testConProperties;

import org.talend.core.model.properties.Item;

import org.talend.testcontainer.core.testcontainer.TestContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Container Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.testcontainer.core.testConProperties.TestContainerItem#getTestContainerProcess <em>Test Container Process</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.testcontainer.core.testConProperties.TestConPropertiesPackage#getTestContainerItem()
 * @model
 * @generated
 */
public interface TestContainerItem extends Item {
    /**
     * Returns the value of the '<em><b>Test Container Process</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Test Container Process</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Test Container Process</em>' reference.
     * @see #setTestContainerProcess(TestContainer)
     * @see org.talend.testcontainer.core.testConProperties.TestConPropertiesPackage#getTestContainerItem_TestContainerProcess()
     * @model
     * @generated
     */
    TestContainer getTestContainerProcess();

    /**
     * Sets the value of the '{@link org.talend.testcontainer.core.testConProperties.TestContainerItem#getTestContainerProcess <em>Test Container Process</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Test Container Process</em>' reference.
     * @see #getTestContainerProcess()
     * @generated
     */
    void setTestContainerProcess(TestContainer value);

} // TestContainerItem

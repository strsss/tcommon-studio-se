/**
 */
package org.talend.testcontainer.core.testcontainer;

import org.talend.designer.core.model.utils.emf.talendfile.NodeType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Container Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.TestContainerNode#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.TestContainerNode#isInput <em>Input</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#getTestContainerNode()
 * @model
 * @generated
 */
public interface TestContainerNode extends NodeType {
    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#getTestContainerNode_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.talend.testcontainer.core.testcontainer.TestContainerNode#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Input</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input</em>' attribute.
     * @see #setInput(boolean)
     * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#getTestContainerNode_Input()
     * @model
     * @generated
     */
    boolean isInput();

    /**
     * Sets the value of the '{@link org.talend.testcontainer.core.testcontainer.TestContainerNode#isInput <em>Input</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input</em>' attribute.
     * @see #isInput()
     * @generated
     */
    void setInput(boolean value);

} // TestContainerNode

/**
 */
package org.talend.testcontainer.core.testcontainer;

import org.eclipse.emf.common.util.EList;

import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.TestContainer#getTestContainerNodes <em>Test Container Nodes</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.TestContainer#getOriginalNodes <em>Original Nodes</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.TestContainer#getOriginalJobID <em>Original Job ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#getTestContainer()
 * @model
 * @generated
 */
public interface TestContainer extends ProcessType {
    /**
     * Returns the value of the '<em><b>Test Container Nodes</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.testcontainer.core.testcontainer.TestContainerNode}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Test Container Nodes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Test Container Nodes</em>' containment reference list.
     * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#getTestContainer_TestContainerNodes()
     * @model containment="true"
     * @generated
     */
    EList<TestContainerNode> getTestContainerNodes();

    /**
     * Returns the value of the '<em><b>Original Nodes</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.testcontainer.core.testcontainer.OriginalNode}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Original Nodes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Original Nodes</em>' containment reference list.
     * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#getTestContainer_OriginalNodes()
     * @model containment="true"
     * @generated
     */
    EList<OriginalNode> getOriginalNodes();

    /**
     * Returns the value of the '<em><b>Original Job ID</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Original Job ID</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Original Job ID</em>' attribute.
     * @see #setOriginalJobID(String)
     * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#getTestContainer_OriginalJobID()
     * @model
     * @generated
     */
    String getOriginalJobID();

    /**
     * Sets the value of the '{@link org.talend.testcontainer.core.testcontainer.TestContainer#getOriginalJobID <em>Original Job ID</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Original Job ID</em>' attribute.
     * @see #getOriginalJobID()
     * @generated
     */
    void setOriginalJobID(String value);

} // TestContainer

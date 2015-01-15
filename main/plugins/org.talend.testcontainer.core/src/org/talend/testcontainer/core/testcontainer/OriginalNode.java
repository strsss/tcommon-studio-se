/**
 */
package org.talend.testcontainer.core.testcontainer;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Original Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.OriginalNode#getUniqueName <em>Unique Name</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.OriginalNode#getPosX <em>Pos X</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.OriginalNode#getPosY <em>Pos Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#getOriginalNode()
 * @model
 * @generated
 */
public interface OriginalNode extends EObject {
    /**
     * Returns the value of the '<em><b>Unique Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Unique Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Unique Name</em>' attribute.
     * @see #setUniqueName(String)
     * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#getOriginalNode_UniqueName()
     * @model
     * @generated
     */
    String getUniqueName();

    /**
     * Sets the value of the '{@link org.talend.testcontainer.core.testcontainer.OriginalNode#getUniqueName <em>Unique Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Unique Name</em>' attribute.
     * @see #getUniqueName()
     * @generated
     */
    void setUniqueName(String value);

    /**
     * Returns the value of the '<em><b>Pos X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pos X</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pos X</em>' attribute.
     * @see #setPosX(int)
     * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#getOriginalNode_PosX()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getPosX();

    /**
     * Sets the value of the '{@link org.talend.testcontainer.core.testcontainer.OriginalNode#getPosX <em>Pos X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pos X</em>' attribute.
     * @see #getPosX()
     * @generated
     */
    void setPosX(int value);

    /**
     * Returns the value of the '<em><b>Pos Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pos Y</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pos Y</em>' attribute.
     * @see #setPosY(int)
     * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#getOriginalNode_PosY()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getPosY();

    /**
     * Sets the value of the '{@link org.talend.testcontainer.core.testcontainer.OriginalNode#getPosY <em>Pos Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pos Y</em>' attribute.
     * @see #getPosY()
     * @generated
     */
    void setPosY(int value);

} // OriginalNode

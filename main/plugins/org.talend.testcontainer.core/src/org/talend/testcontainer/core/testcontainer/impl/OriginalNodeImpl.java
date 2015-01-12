/**
 */
package org.talend.testcontainer.core.testcontainer.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.talend.testcontainer.core.testcontainer.OriginalNode;
import org.talend.testcontainer.core.testcontainer.TestcontainerPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Original Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.impl.OriginalNodeImpl#getOriginalJobID <em>Original Job ID</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.impl.OriginalNodeImpl#getUniqueName <em>Unique Name</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.impl.OriginalNodeImpl#getPosX <em>Pos X</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.impl.OriginalNodeImpl#getPosY <em>Pos Y</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OriginalNodeImpl extends MinimalEObjectImpl.Container implements OriginalNode {
    /**
     * The default value of the '{@link #getOriginalJobID() <em>Original Job ID</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOriginalJobID()
     * @generated
     * @ordered
     */
    protected static final String ORIGINAL_JOB_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOriginalJobID() <em>Original Job ID</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOriginalJobID()
     * @generated
     * @ordered
     */
    protected String originalJobID = ORIGINAL_JOB_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getUniqueName() <em>Unique Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUniqueName()
     * @generated
     * @ordered
     */
    protected static final String UNIQUE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUniqueName() <em>Unique Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUniqueName()
     * @generated
     * @ordered
     */
    protected String uniqueName = UNIQUE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getPosX() <em>Pos X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosX()
     * @generated
     * @ordered
     */
    protected static final int POS_X_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPosX() <em>Pos X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosX()
     * @generated
     * @ordered
     */
    protected int posX = POS_X_EDEFAULT;

    /**
     * The default value of the '{@link #getPosY() <em>Pos Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosY()
     * @generated
     * @ordered
     */
    protected static final int POS_Y_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPosY() <em>Pos Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosY()
     * @generated
     * @ordered
     */
    protected int posY = POS_Y_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OriginalNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TestcontainerPackage.Literals.ORIGINAL_NODE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getOriginalJobID() {
        return originalJobID;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOriginalJobID(String newOriginalJobID) {
        String oldOriginalJobID = originalJobID;
        originalJobID = newOriginalJobID;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TestcontainerPackage.ORIGINAL_NODE__ORIGINAL_JOB_ID, oldOriginalJobID, originalJobID));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUniqueName() {
        return uniqueName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUniqueName(String newUniqueName) {
        String oldUniqueName = uniqueName;
        uniqueName = newUniqueName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TestcontainerPackage.ORIGINAL_NODE__UNIQUE_NAME, oldUniqueName, uniqueName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPosX() {
        return posX;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPosX(int newPosX) {
        int oldPosX = posX;
        posX = newPosX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TestcontainerPackage.ORIGINAL_NODE__POS_X, oldPosX, posX));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPosY() {
        return posY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPosY(int newPosY) {
        int oldPosY = posY;
        posY = newPosY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TestcontainerPackage.ORIGINAL_NODE__POS_Y, oldPosY, posY));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TestcontainerPackage.ORIGINAL_NODE__ORIGINAL_JOB_ID:
                return getOriginalJobID();
            case TestcontainerPackage.ORIGINAL_NODE__UNIQUE_NAME:
                return getUniqueName();
            case TestcontainerPackage.ORIGINAL_NODE__POS_X:
                return getPosX();
            case TestcontainerPackage.ORIGINAL_NODE__POS_Y:
                return getPosY();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TestcontainerPackage.ORIGINAL_NODE__ORIGINAL_JOB_ID:
                setOriginalJobID((String)newValue);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__UNIQUE_NAME:
                setUniqueName((String)newValue);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__POS_X:
                setPosX((Integer)newValue);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__POS_Y:
                setPosY((Integer)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case TestcontainerPackage.ORIGINAL_NODE__ORIGINAL_JOB_ID:
                setOriginalJobID(ORIGINAL_JOB_ID_EDEFAULT);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__UNIQUE_NAME:
                setUniqueName(UNIQUE_NAME_EDEFAULT);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__POS_X:
                setPosX(POS_X_EDEFAULT);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__POS_Y:
                setPosY(POS_Y_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TestcontainerPackage.ORIGINAL_NODE__ORIGINAL_JOB_ID:
                return ORIGINAL_JOB_ID_EDEFAULT == null ? originalJobID != null : !ORIGINAL_JOB_ID_EDEFAULT.equals(originalJobID);
            case TestcontainerPackage.ORIGINAL_NODE__UNIQUE_NAME:
                return UNIQUE_NAME_EDEFAULT == null ? uniqueName != null : !UNIQUE_NAME_EDEFAULT.equals(uniqueName);
            case TestcontainerPackage.ORIGINAL_NODE__POS_X:
                return posX != POS_X_EDEFAULT;
            case TestcontainerPackage.ORIGINAL_NODE__POS_Y:
                return posY != POS_Y_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (originalJobID: ");
        result.append(originalJobID);
        result.append(", uniqueName: ");
        result.append(uniqueName);
        result.append(", posX: ");
        result.append(posX);
        result.append(", posY: ");
        result.append(posY);
        result.append(')');
        return result.toString();
    }

} //OriginalNodeImpl

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
 *   <li>{@link org.talend.testcontainer.core.testcontainer.impl.OriginalNodeImpl#getUniqueName <em>Unique Name</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.impl.OriginalNodeImpl#getPosX <em>Pos X</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.impl.OriginalNodeImpl#getPosY <em>Pos Y</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.impl.OriginalNodeImpl#isStart <em>Start</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OriginalNodeImpl extends MinimalEObjectImpl.Container implements OriginalNode {
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
     * The default value of the '{@link #isStart() <em>Start</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isStart()
     * @generated
     * @ordered
     */
    protected static final boolean START_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isStart() <em>Start</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isStart()
     * @generated
     * @ordered
     */
    protected boolean start = START_EDEFAULT;

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
    public boolean isStart() {
        return start;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStart(boolean newStart) {
        boolean oldStart = start;
        start = newStart;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TestcontainerPackage.ORIGINAL_NODE__START, oldStart, start));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TestcontainerPackage.ORIGINAL_NODE__UNIQUE_NAME:
                return getUniqueName();
            case TestcontainerPackage.ORIGINAL_NODE__POS_X:
                return getPosX();
            case TestcontainerPackage.ORIGINAL_NODE__POS_Y:
                return getPosY();
            case TestcontainerPackage.ORIGINAL_NODE__START:
                return isStart();
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
            case TestcontainerPackage.ORIGINAL_NODE__UNIQUE_NAME:
                setUniqueName((String)newValue);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__POS_X:
                setPosX((Integer)newValue);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__POS_Y:
                setPosY((Integer)newValue);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__START:
                setStart((Boolean)newValue);
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
            case TestcontainerPackage.ORIGINAL_NODE__UNIQUE_NAME:
                setUniqueName(UNIQUE_NAME_EDEFAULT);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__POS_X:
                setPosX(POS_X_EDEFAULT);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__POS_Y:
                setPosY(POS_Y_EDEFAULT);
                return;
            case TestcontainerPackage.ORIGINAL_NODE__START:
                setStart(START_EDEFAULT);
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
            case TestcontainerPackage.ORIGINAL_NODE__UNIQUE_NAME:
                return UNIQUE_NAME_EDEFAULT == null ? uniqueName != null : !UNIQUE_NAME_EDEFAULT.equals(uniqueName);
            case TestcontainerPackage.ORIGINAL_NODE__POS_X:
                return posX != POS_X_EDEFAULT;
            case TestcontainerPackage.ORIGINAL_NODE__POS_Y:
                return posY != POS_Y_EDEFAULT;
            case TestcontainerPackage.ORIGINAL_NODE__START:
                return start != START_EDEFAULT;
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
        result.append(" (uniqueName: ");
        result.append(uniqueName);
        result.append(", posX: ");
        result.append(posX);
        result.append(", posY: ");
        result.append(posY);
        result.append(", start: ");
        result.append(start);
        result.append(')');
        return result.toString();
    }

} //OriginalNodeImpl

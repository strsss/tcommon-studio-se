/**
 */
package org.talend.testcontainer.core.testConProperties.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.talend.core.model.properties.impl.ItemImpl;

import org.talend.testcontainer.core.testConProperties.TestConPropertiesPackage;
import org.talend.testcontainer.core.testConProperties.TestContainerItem;

import org.talend.testcontainer.core.testcontainer.TestContainer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Container Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.testcontainer.core.testConProperties.impl.TestContainerItemImpl#getTestContainerProcess <em>Test Container Process</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestContainerItemImpl extends ItemImpl implements TestContainerItem {
    /**
     * The cached value of the '{@link #getTestContainerProcess() <em>Test Container Process</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTestContainerProcess()
     * @generated
     * @ordered
     */
    protected TestContainer testContainerProcess;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TestContainerItemImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TestConPropertiesPackage.Literals.TEST_CONTAINER_ITEM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestContainer getTestContainerProcess() {
        if (testContainerProcess != null && testContainerProcess.eIsProxy()) {
            InternalEObject oldTestContainerProcess = (InternalEObject)testContainerProcess;
            testContainerProcess = (TestContainer)eResolveProxy(oldTestContainerProcess);
            if (testContainerProcess != oldTestContainerProcess) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestConPropertiesPackage.TEST_CONTAINER_ITEM__TEST_CONTAINER_PROCESS, oldTestContainerProcess, testContainerProcess));
            }
        }
        return testContainerProcess;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestContainer basicGetTestContainerProcess() {
        return testContainerProcess;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTestContainerProcess(TestContainer newTestContainerProcess) {
        TestContainer oldTestContainerProcess = testContainerProcess;
        testContainerProcess = newTestContainerProcess;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TestConPropertiesPackage.TEST_CONTAINER_ITEM__TEST_CONTAINER_PROCESS, oldTestContainerProcess, testContainerProcess));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TestConPropertiesPackage.TEST_CONTAINER_ITEM__TEST_CONTAINER_PROCESS:
                if (resolve) return getTestContainerProcess();
                return basicGetTestContainerProcess();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TestConPropertiesPackage.TEST_CONTAINER_ITEM__TEST_CONTAINER_PROCESS:
                setTestContainerProcess((TestContainer)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case TestConPropertiesPackage.TEST_CONTAINER_ITEM__TEST_CONTAINER_PROCESS:
                setTestContainerProcess((TestContainer)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TestConPropertiesPackage.TEST_CONTAINER_ITEM__TEST_CONTAINER_PROCESS:
                return testContainerProcess != null;
        }
        return super.eIsSet(featureID);
    }

} //TestContainerItemImpl

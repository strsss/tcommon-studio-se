/**
 */
package org.talend.testcontainer.core.testcontainer.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.talend.designer.core.model.utils.emf.talendfile.impl.ProcessTypeImpl;

import org.talend.testcontainer.core.testcontainer.OriginalNode;
import org.talend.testcontainer.core.testcontainer.TestContainer;
import org.talend.testcontainer.core.testcontainer.TestContainerNode;
import org.talend.testcontainer.core.testcontainer.TestcontainerPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.impl.TestContainerImpl#getTestContainerNodes <em>Test Container Nodes</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.impl.TestContainerImpl#getOriginalNodes <em>Original Nodes</em>}</li>
 *   <li>{@link org.talend.testcontainer.core.testcontainer.impl.TestContainerImpl#getOriginalJobID <em>Original Job ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestContainerImpl extends ProcessTypeImpl implements TestContainer {
    /**
     * The cached value of the '{@link #getTestContainerNodes() <em>Test Container Nodes</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTestContainerNodes()
     * @generated
     * @ordered
     */
    protected EList<TestContainerNode> testContainerNodes;

    /**
     * The cached value of the '{@link #getOriginalNodes() <em>Original Nodes</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOriginalNodes()
     * @generated
     * @ordered
     */
    protected EList<OriginalNode> originalNodes;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TestContainerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TestcontainerPackage.Literals.TEST_CONTAINER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TestContainerNode> getTestContainerNodes() {
        if (testContainerNodes == null) {
            testContainerNodes = new EObjectContainmentEList<TestContainerNode>(TestContainerNode.class, this, TestcontainerPackage.TEST_CONTAINER__TEST_CONTAINER_NODES);
        }
        return testContainerNodes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<OriginalNode> getOriginalNodes() {
        if (originalNodes == null) {
            originalNodes = new EObjectContainmentEList<OriginalNode>(OriginalNode.class, this, TestcontainerPackage.TEST_CONTAINER__ORIGINAL_NODES);
        }
        return originalNodes;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TestcontainerPackage.TEST_CONTAINER__ORIGINAL_JOB_ID, oldOriginalJobID, originalJobID));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TestcontainerPackage.TEST_CONTAINER__TEST_CONTAINER_NODES:
                return ((InternalEList<?>)getTestContainerNodes()).basicRemove(otherEnd, msgs);
            case TestcontainerPackage.TEST_CONTAINER__ORIGINAL_NODES:
                return ((InternalEList<?>)getOriginalNodes()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TestcontainerPackage.TEST_CONTAINER__TEST_CONTAINER_NODES:
                return getTestContainerNodes();
            case TestcontainerPackage.TEST_CONTAINER__ORIGINAL_NODES:
                return getOriginalNodes();
            case TestcontainerPackage.TEST_CONTAINER__ORIGINAL_JOB_ID:
                return getOriginalJobID();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TestcontainerPackage.TEST_CONTAINER__TEST_CONTAINER_NODES:
                getTestContainerNodes().clear();
                getTestContainerNodes().addAll((Collection<? extends TestContainerNode>)newValue);
                return;
            case TestcontainerPackage.TEST_CONTAINER__ORIGINAL_NODES:
                getOriginalNodes().clear();
                getOriginalNodes().addAll((Collection<? extends OriginalNode>)newValue);
                return;
            case TestcontainerPackage.TEST_CONTAINER__ORIGINAL_JOB_ID:
                setOriginalJobID((String)newValue);
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
            case TestcontainerPackage.TEST_CONTAINER__TEST_CONTAINER_NODES:
                getTestContainerNodes().clear();
                return;
            case TestcontainerPackage.TEST_CONTAINER__ORIGINAL_NODES:
                getOriginalNodes().clear();
                return;
            case TestcontainerPackage.TEST_CONTAINER__ORIGINAL_JOB_ID:
                setOriginalJobID(ORIGINAL_JOB_ID_EDEFAULT);
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
            case TestcontainerPackage.TEST_CONTAINER__TEST_CONTAINER_NODES:
                return testContainerNodes != null && !testContainerNodes.isEmpty();
            case TestcontainerPackage.TEST_CONTAINER__ORIGINAL_NODES:
                return originalNodes != null && !originalNodes.isEmpty();
            case TestcontainerPackage.TEST_CONTAINER__ORIGINAL_JOB_ID:
                return ORIGINAL_JOB_ID_EDEFAULT == null ? originalJobID != null : !ORIGINAL_JOB_ID_EDEFAULT.equals(originalJobID);
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
        result.append(')');
        return result.toString();
    }

} //TestContainerImpl

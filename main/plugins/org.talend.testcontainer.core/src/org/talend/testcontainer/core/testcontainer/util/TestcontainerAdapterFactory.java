/**
 */
package org.talend.testcontainer.core.testcontainer.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;

import org.talend.testcontainer.core.testcontainer.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage
 * @generated
 */
public class TestcontainerAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static TestcontainerPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestcontainerAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = TestcontainerPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TestcontainerSwitch<Adapter> modelSwitch =
        new TestcontainerSwitch<Adapter>() {
            @Override
            public Adapter caseTestContainer(TestContainer object) {
                return createTestContainerAdapter();
            }
            @Override
            public Adapter caseTestContainerNode(TestContainerNode object) {
                return createTestContainerNodeAdapter();
            }
            @Override
            public Adapter caseOriginalNode(OriginalNode object) {
                return createOriginalNodeAdapter();
            }
            @Override
            public Adapter caseProcessType(ProcessType object) {
                return createProcessTypeAdapter();
            }
            @Override
            public Adapter caseNodeType(NodeType object) {
                return createNodeTypeAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link org.talend.testcontainer.core.testcontainer.TestContainer <em>Test Container</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.testcontainer.core.testcontainer.TestContainer
     * @generated
     */
    public Adapter createTestContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.testcontainer.core.testcontainer.TestContainerNode <em>Test Container Node</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.testcontainer.core.testcontainer.TestContainerNode
     * @generated
     */
    public Adapter createTestContainerNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.testcontainer.core.testcontainer.OriginalNode <em>Original Node</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.testcontainer.core.testcontainer.OriginalNode
     * @generated
     */
    public Adapter createOriginalNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.designer.core.model.utils.emf.talendfile.ProcessType <em>Process Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.designer.core.model.utils.emf.talendfile.ProcessType
     * @generated
     */
    public Adapter createProcessTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.talend.designer.core.model.utils.emf.talendfile.NodeType <em>Node Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.talend.designer.core.model.utils.emf.talendfile.NodeType
     * @generated
     */
    public Adapter createNodeTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //TestcontainerAdapterFactory

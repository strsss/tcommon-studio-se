/**
 */
package testcontainer.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import testcontainer.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestcontainerFactoryImpl extends EFactoryImpl implements TestcontainerFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static TestcontainerFactory init() {
        try {
            TestcontainerFactory theTestcontainerFactory = (TestcontainerFactory)EPackage.Registry.INSTANCE.getEFactory(TestcontainerPackage.eNS_URI);
            if (theTestcontainerFactory != null) {
                return theTestcontainerFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TestcontainerFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestcontainerFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case TestcontainerPackage.TEST_CONTAINER: return createTestContainer();
            case TestcontainerPackage.TEST_CONTAINER_NODE: return createTestContainerNode();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestContainer createTestContainer() {
        TestContainerImpl testContainer = new TestContainerImpl();
        return testContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestContainerNode createTestContainerNode() {
        TestContainerNodeImpl testContainerNode = new TestContainerNodeImpl();
        return testContainerNode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestcontainerPackage getTestcontainerPackage() {
        return (TestcontainerPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static TestcontainerPackage getPackage() {
        return TestcontainerPackage.eINSTANCE;
    }

} //TestcontainerFactoryImpl

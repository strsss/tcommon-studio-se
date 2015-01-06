/**
 */
package testcontainer.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage;

import testcontainer.TestContainer;
import testcontainer.TestContainerNode;
import testcontainer.TestcontainerFactory;
import testcontainer.TestcontainerPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestcontainerPackageImpl extends EPackageImpl implements TestcontainerPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass testContainerEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass testContainerNodeEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see testcontainer.TestcontainerPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TestcontainerPackageImpl() {
        super(eNS_URI, TestcontainerFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link TestcontainerPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TestcontainerPackage init() {
        if (isInited) return (TestcontainerPackage)EPackage.Registry.INSTANCE.getEPackage(TestcontainerPackage.eNS_URI);

        // Obtain or create and register package
        TestcontainerPackageImpl theTestcontainerPackage = (TestcontainerPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TestcontainerPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TestcontainerPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        TalendFilePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theTestcontainerPackage.createPackageContents();

        // Initialize created meta-data
        theTestcontainerPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTestcontainerPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(TestcontainerPackage.eNS_URI, theTestcontainerPackage);
        return theTestcontainerPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTestContainer() {
        return testContainerEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTestContainer_TestContainerNodes() {
        return (EReference)testContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTestContainerNode() {
        return testContainerNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestContainerNode_Description() {
        return (EAttribute)testContainerNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestContainerNode_Input() {
        return (EAttribute)testContainerNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestcontainerFactory getTestcontainerFactory() {
        return (TestcontainerFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        testContainerEClass = createEClass(TEST_CONTAINER);
        createEReference(testContainerEClass, TEST_CONTAINER__TEST_CONTAINER_NODES);

        testContainerNodeEClass = createEClass(TEST_CONTAINER_NODE);
        createEAttribute(testContainerNodeEClass, TEST_CONTAINER_NODE__DESCRIPTION);
        createEAttribute(testContainerNodeEClass, TEST_CONTAINER_NODE__INPUT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        TalendFilePackage theTalendFilePackage = (TalendFilePackage)EPackage.Registry.INSTANCE.getEPackage(TalendFilePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        testContainerEClass.getESuperTypes().add(theTalendFilePackage.getProcessType());
        testContainerNodeEClass.getESuperTypes().add(theTalendFilePackage.getNodeType());

        // Initialize classes, features, and operations; add parameters
        initEClass(testContainerEClass, TestContainer.class, "TestContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTestContainer_TestContainerNodes(), this.getTestContainerNode(), null, "testContainerNodes", null, 0, -1, TestContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(testContainerNodeEClass, TestContainerNode.class, "TestContainerNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTestContainerNode_Description(), ecorePackage.getEString(), "description", null, 0, 1, TestContainerNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestContainerNode_Input(), ecorePackage.getEBoolean(), "input", null, 0, 1, TestContainerNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //TestcontainerPackageImpl

/**
 */
package org.talend.testcontainer.core.testcontainer.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage;
import org.talend.testcontainer.core.testcontainer.OriginalNode;
import org.talend.testcontainer.core.testcontainer.TestContainer;
import org.talend.testcontainer.core.testcontainer.TestContainerNode;
import org.talend.testcontainer.core.testcontainer.TestcontainerFactory;
import org.talend.testcontainer.core.testcontainer.TestcontainerPackage;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass originalNodeEClass = null;

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
     * @see org.talend.testcontainer.core.testcontainer.TestcontainerPackage#eNS_URI
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
        XMLTypePackage.eINSTANCE.eClass();

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
    public EReference getTestContainer_OriginalNodes() {
        return (EReference)testContainerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestContainer_OriginalJobID() {
        return (EAttribute)testContainerEClass.getEStructuralFeatures().get(2);
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
    public EClass getOriginalNode() {
        return originalNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOriginalNode_UniqueName() {
        return (EAttribute)originalNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOriginalNode_PosX() {
        return (EAttribute)originalNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getOriginalNode_PosY() {
        return (EAttribute)originalNodeEClass.getEStructuralFeatures().get(2);
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
        createEReference(testContainerEClass, TEST_CONTAINER__ORIGINAL_NODES);
        createEAttribute(testContainerEClass, TEST_CONTAINER__ORIGINAL_JOB_ID);

        testContainerNodeEClass = createEClass(TEST_CONTAINER_NODE);
        createEAttribute(testContainerNodeEClass, TEST_CONTAINER_NODE__DESCRIPTION);
        createEAttribute(testContainerNodeEClass, TEST_CONTAINER_NODE__INPUT);

        originalNodeEClass = createEClass(ORIGINAL_NODE);
        createEAttribute(originalNodeEClass, ORIGINAL_NODE__UNIQUE_NAME);
        createEAttribute(originalNodeEClass, ORIGINAL_NODE__POS_X);
        createEAttribute(originalNodeEClass, ORIGINAL_NODE__POS_Y);
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
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        testContainerEClass.getESuperTypes().add(theTalendFilePackage.getProcessType());
        testContainerNodeEClass.getESuperTypes().add(theTalendFilePackage.getNodeType());

        // Initialize classes and features; add operations and parameters
        initEClass(testContainerEClass, TestContainer.class, "TestContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTestContainer_TestContainerNodes(), this.getTestContainerNode(), null, "testContainerNodes", null, 0, -1, TestContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTestContainer_OriginalNodes(), this.getOriginalNode(), null, "OriginalNodes", null, 0, -1, TestContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestContainer_OriginalJobID(), ecorePackage.getEString(), "originalJobID", null, 0, 1, TestContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(testContainerNodeEClass, TestContainerNode.class, "TestContainerNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTestContainerNode_Description(), ecorePackage.getEString(), "description", null, 0, 1, TestContainerNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestContainerNode_Input(), ecorePackage.getEBoolean(), "input", null, 0, 1, TestContainerNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(originalNodeEClass, OriginalNode.class, "OriginalNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getOriginalNode_UniqueName(), ecorePackage.getEString(), "uniqueName", null, 0, 1, OriginalNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getOriginalNode_PosX(), theXMLTypePackage.getInt(), "posX", null, 0, 1, OriginalNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getOriginalNode_PosY(), theXMLTypePackage.getInt(), "posY", null, 0, 1, OriginalNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //TestcontainerPackageImpl

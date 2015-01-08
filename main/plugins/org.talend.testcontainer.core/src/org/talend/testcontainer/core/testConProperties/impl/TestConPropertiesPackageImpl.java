/**
 */
package org.talend.testcontainer.core.testConProperties.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.talend.core.model.properties.PropertiesPackage;

import org.talend.testcontainer.core.testConProperties.TestConPropertiesFactory;
import org.talend.testcontainer.core.testConProperties.TestConPropertiesPackage;
import org.talend.testcontainer.core.testConProperties.TestContainerItem;

import org.talend.testcontainer.core.testcontainer.TestcontainerPackage;

import org.talend.testcontainer.core.testcontainer.impl.TestcontainerPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestConPropertiesPackageImpl extends EPackageImpl implements TestConPropertiesPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass testContainerItemEClass = null;

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
     * @see org.talend.testcontainer.core.testConProperties.TestConPropertiesPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TestConPropertiesPackageImpl() {
        super(eNS_URI, TestConPropertiesFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link TestConPropertiesPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TestConPropertiesPackage init() {
        if (isInited) return (TestConPropertiesPackage)EPackage.Registry.INSTANCE.getEPackage(TestConPropertiesPackage.eNS_URI);

        // Obtain or create and register package
        TestConPropertiesPackageImpl theTestConPropertiesPackage = (TestConPropertiesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TestConPropertiesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TestConPropertiesPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        PropertiesPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        TestcontainerPackageImpl theTestcontainerPackage = (TestcontainerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TestcontainerPackage.eNS_URI) instanceof TestcontainerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TestcontainerPackage.eNS_URI) : TestcontainerPackage.eINSTANCE);

        // Create package meta-data objects
        theTestConPropertiesPackage.createPackageContents();
        theTestcontainerPackage.createPackageContents();

        // Initialize created meta-data
        theTestConPropertiesPackage.initializePackageContents();
        theTestcontainerPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTestConPropertiesPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(TestConPropertiesPackage.eNS_URI, theTestConPropertiesPackage);
        return theTestConPropertiesPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTestContainerItem() {
        return testContainerItemEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTestContainerItem_TestContainerProcess() {
        return (EReference)testContainerItemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TestConPropertiesFactory getTestConPropertiesFactory() {
        return (TestConPropertiesFactory)getEFactoryInstance();
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
        testContainerItemEClass = createEClass(TEST_CONTAINER_ITEM);
        createEReference(testContainerItemEClass, TEST_CONTAINER_ITEM__TEST_CONTAINER_PROCESS);
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
        PropertiesPackage thePropertiesPackage = (PropertiesPackage)EPackage.Registry.INSTANCE.getEPackage(PropertiesPackage.eNS_URI);
        TestcontainerPackage theTestcontainerPackage = (TestcontainerPackage)EPackage.Registry.INSTANCE.getEPackage(TestcontainerPackage.eNS_URI);

        // Add supertypes to classes
        testContainerItemEClass.getESuperTypes().add(thePropertiesPackage.getItem());

        // Initialize classes and features; add operations and parameters
        initEClass(testContainerItemEClass, TestContainerItem.class, "TestContainerItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTestContainerItem_TestContainerProcess(), theTestcontainerPackage.getTestContainer(), null, "testContainerProcess", null, 0, 1, TestContainerItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //TestConPropertiesPackageImpl

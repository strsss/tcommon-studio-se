/**
 */
package org.talend.testcontainer.core.testcontainer;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.talend.testcontainer.core.testcontainer.TestcontainerFactory
 * @model kind="package"
 * @generated
 */
public interface TestcontainerPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "testcontainer";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.talend.com/testcontainer.ecore";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "testcontainer";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    TestcontainerPackage eINSTANCE = org.talend.testcontainer.core.testcontainer.impl.TestcontainerPackageImpl.init();

    /**
     * The meta object id for the '{@link org.talend.testcontainer.core.testcontainer.impl.TestContainerImpl <em>Test Container</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.testcontainer.core.testcontainer.impl.TestContainerImpl
     * @see org.talend.testcontainer.core.testcontainer.impl.TestcontainerPackageImpl#getTestContainer()
     * @generated
     */
    int TEST_CONTAINER = 0;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__DESCRIPTION = TalendFilePackage.PROCESS_TYPE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Required</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__REQUIRED = TalendFilePackage.PROCESS_TYPE__REQUIRED;

    /**
     * The feature id for the '<em><b>Context</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__CONTEXT = TalendFilePackage.PROCESS_TYPE__CONTEXT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__PARAMETERS = TalendFilePackage.PROCESS_TYPE__PARAMETERS;

    /**
     * The feature id for the '<em><b>Node</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__NODE = TalendFilePackage.PROCESS_TYPE__NODE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__CONNECTION = TalendFilePackage.PROCESS_TYPE__CONNECTION;

    /**
     * The feature id for the '<em><b>Note</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__NOTE = TalendFilePackage.PROCESS_TYPE__NOTE;

    /**
     * The feature id for the '<em><b>Logs</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__LOGS = TalendFilePackage.PROCESS_TYPE__LOGS;

    /**
     * The feature id for the '<em><b>Author</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__AUTHOR = TalendFilePackage.PROCESS_TYPE__AUTHOR;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__COMMENT = TalendFilePackage.PROCESS_TYPE__COMMENT;

    /**
     * The feature id for the '<em><b>Default Context</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__DEFAULT_CONTEXT = TalendFilePackage.PROCESS_TYPE__DEFAULT_CONTEXT;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__NAME = TalendFilePackage.PROCESS_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Purpose</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__PURPOSE = TalendFilePackage.PROCESS_TYPE__PURPOSE;

    /**
     * The feature id for the '<em><b>Repository Context Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__REPOSITORY_CONTEXT_ID = TalendFilePackage.PROCESS_TYPE__REPOSITORY_CONTEXT_ID;

    /**
     * The feature id for the '<em><b>Status</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__STATUS = TalendFilePackage.PROCESS_TYPE__STATUS;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__VERSION = TalendFilePackage.PROCESS_TYPE__VERSION;

    /**
     * The feature id for the '<em><b>Subjob</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__SUBJOB = TalendFilePackage.PROCESS_TYPE__SUBJOB;

    /**
     * The feature id for the '<em><b>Screenshot</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__SCREENSHOT = TalendFilePackage.PROCESS_TYPE__SCREENSHOT;

    /**
     * The feature id for the '<em><b>Screenshots</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__SCREENSHOTS = TalendFilePackage.PROCESS_TYPE__SCREENSHOTS;

    /**
     * The feature id for the '<em><b>Routines Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__ROUTINES_DEPENDENCIES = TalendFilePackage.PROCESS_TYPE__ROUTINES_DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Test Container Nodes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER__TEST_CONTAINER_NODES = TalendFilePackage.PROCESS_TYPE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Test Container</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_FEATURE_COUNT = TalendFilePackage.PROCESS_TYPE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.testcontainer.core.testcontainer.impl.TestContainerNodeImpl <em>Test Container Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.testcontainer.core.testcontainer.impl.TestContainerNodeImpl
     * @see org.talend.testcontainer.core.testcontainer.impl.TestcontainerPackageImpl#getTestContainerNode()
     * @generated
     */
    int TEST_CONTAINER_NODE = 1;

    /**
     * The feature id for the '<em><b>Element Parameter</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__ELEMENT_PARAMETER = TalendFilePackage.NODE_TYPE__ELEMENT_PARAMETER;

    /**
     * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__METADATA = TalendFilePackage.NODE_TYPE__METADATA;

    /**
     * The feature id for the '<em><b>Binary Data</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__BINARY_DATA = TalendFilePackage.NODE_TYPE__BINARY_DATA;

    /**
     * The feature id for the '<em><b>String Data</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__STRING_DATA = TalendFilePackage.NODE_TYPE__STRING_DATA;

    /**
     * The feature id for the '<em><b>Component Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__COMPONENT_NAME = TalendFilePackage.NODE_TYPE__COMPONENT_NAME;

    /**
     * The feature id for the '<em><b>Component Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__COMPONENT_VERSION = TalendFilePackage.NODE_TYPE__COMPONENT_VERSION;

    /**
     * The feature id for the '<em><b>Offset Label X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__OFFSET_LABEL_X = TalendFilePackage.NODE_TYPE__OFFSET_LABEL_X;

    /**
     * The feature id for the '<em><b>Offset Label Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__OFFSET_LABEL_Y = TalendFilePackage.NODE_TYPE__OFFSET_LABEL_Y;

    /**
     * The feature id for the '<em><b>Pos X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__POS_X = TalendFilePackage.NODE_TYPE__POS_X;

    /**
     * The feature id for the '<em><b>Pos Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__POS_Y = TalendFilePackage.NODE_TYPE__POS_Y;

    /**
     * The feature id for the '<em><b>Size X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__SIZE_X = TalendFilePackage.NODE_TYPE__SIZE_X;

    /**
     * The feature id for the '<em><b>Size Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__SIZE_Y = TalendFilePackage.NODE_TYPE__SIZE_Y;

    /**
     * The feature id for the '<em><b>Screenshot</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__SCREENSHOT = TalendFilePackage.NODE_TYPE__SCREENSHOT;

    /**
     * The feature id for the '<em><b>Node Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__NODE_DATA = TalendFilePackage.NODE_TYPE__NODE_DATA;

    /**
     * The feature id for the '<em><b>Node Container</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__NODE_CONTAINER = TalendFilePackage.NODE_TYPE__NODE_CONTAINER;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__DESCRIPTION = TalendFilePackage.NODE_TYPE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Input</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE__INPUT = TalendFilePackage.NODE_TYPE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Test Container Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEST_CONTAINER_NODE_FEATURE_COUNT = TalendFilePackage.NODE_TYPE_FEATURE_COUNT + 2;


    /**
     * Returns the meta object for class '{@link org.talend.testcontainer.core.testcontainer.TestContainer <em>Test Container</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Test Container</em>'.
     * @see org.talend.testcontainer.core.testcontainer.TestContainer
     * @generated
     */
    EClass getTestContainer();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.testcontainer.core.testcontainer.TestContainer#getTestContainerNodes <em>Test Container Nodes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Test Container Nodes</em>'.
     * @see org.talend.testcontainer.core.testcontainer.TestContainer#getTestContainerNodes()
     * @see #getTestContainer()
     * @generated
     */
    EReference getTestContainer_TestContainerNodes();

    /**
     * Returns the meta object for class '{@link org.talend.testcontainer.core.testcontainer.TestContainerNode <em>Test Container Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Test Container Node</em>'.
     * @see org.talend.testcontainer.core.testcontainer.TestContainerNode
     * @generated
     */
    EClass getTestContainerNode();

    /**
     * Returns the meta object for the attribute '{@link org.talend.testcontainer.core.testcontainer.TestContainerNode#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.talend.testcontainer.core.testcontainer.TestContainerNode#getDescription()
     * @see #getTestContainerNode()
     * @generated
     */
    EAttribute getTestContainerNode_Description();

    /**
     * Returns the meta object for the attribute '{@link org.talend.testcontainer.core.testcontainer.TestContainerNode#isInput <em>Input</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Input</em>'.
     * @see org.talend.testcontainer.core.testcontainer.TestContainerNode#isInput()
     * @see #getTestContainerNode()
     * @generated
     */
    EAttribute getTestContainerNode_Input();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TestcontainerFactory getTestcontainerFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.talend.testcontainer.core.testcontainer.impl.TestContainerImpl <em>Test Container</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.testcontainer.core.testcontainer.impl.TestContainerImpl
         * @see org.talend.testcontainer.core.testcontainer.impl.TestcontainerPackageImpl#getTestContainer()
         * @generated
         */
        EClass TEST_CONTAINER = eINSTANCE.getTestContainer();

        /**
         * The meta object literal for the '<em><b>Test Container Nodes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TEST_CONTAINER__TEST_CONTAINER_NODES = eINSTANCE.getTestContainer_TestContainerNodes();

        /**
         * The meta object literal for the '{@link org.talend.testcontainer.core.testcontainer.impl.TestContainerNodeImpl <em>Test Container Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.testcontainer.core.testcontainer.impl.TestContainerNodeImpl
         * @see org.talend.testcontainer.core.testcontainer.impl.TestcontainerPackageImpl#getTestContainerNode()
         * @generated
         */
        EClass TEST_CONTAINER_NODE = eINSTANCE.getTestContainerNode();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_CONTAINER_NODE__DESCRIPTION = eINSTANCE.getTestContainerNode_Description();

        /**
         * The meta object literal for the '<em><b>Input</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEST_CONTAINER_NODE__INPUT = eINSTANCE.getTestContainerNode_Input();

    }

} //TestcontainerPackage

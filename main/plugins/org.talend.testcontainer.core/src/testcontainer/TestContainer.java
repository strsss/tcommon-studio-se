/**
 */
package testcontainer;

import org.eclipse.emf.common.util.EList;

import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link testcontainer.TestContainer#getTestContainerNodes <em>Test Container Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @see testcontainer.TestcontainerPackage#getTestContainer()
 * @model
 * @generated
 */
public interface TestContainer extends ProcessType {
    /**
     * Returns the value of the '<em><b>Test Container Nodes</b></em>' containment reference list.
     * The list contents are of type {@link testcontainer.TestContainerNode}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Test Container Nodes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Test Container Nodes</em>' containment reference list.
     * @see testcontainer.TestcontainerPackage#getTestContainer_TestContainerNodes()
     * @model containment="true"
     * @generated
     */
    EList<TestContainerNode> getTestContainerNodes();

} // TestContainer

package org.talend.testcontainer.core.ui.model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.rulers.RulerProvider;
import org.talend.designer.core.model.components.EParameterName;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainer;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainerLayoutEditPolicy;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainerPart;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.editor.nodes.NodePart;
import org.talend.designer.core.ui.editor.process.NodeSnapToGeometry;
import org.talend.designer.core.ui.editor.subjobcontainer.SubjobContainer;

public class JunitContainerPart extends NodeContainerPart {

    @Override
    protected IFigure createFigure() {
        JunitContainerFigure figure = new JunitContainerFigure((JunitContainer) this.getModel());
        Node node = ((NodeContainer) getModel()).getNode();
        figure.updateStatus(node.getStatus());

        return figure;
    }

    @Override
    protected void unregisterVisuals() {
        super.unregisterVisuals();
        ((JunitContainerFigure) getFigure()).disposeColors();
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    @Override
    public void activate() {
        super.activate();
        ((JunitContainer) getModel()).addPropertyChangeListener(this);
        Node node = ((JunitContainer) getModel()).getNode();
        node.addPropertyChangeListener(this);
    }

    @Override
    public void deactivate() {
        super.deactivate();
        ((JunitContainer) getModel()).removePropertyChangeListener(this);
    }

    @Override
    public void setSelected(int value) {
        super.setSelected(SELECTED_NONE);
    }

    @Override
    public NodePart getNodePart() {
        Object o = this.getChildren().get(0);
        if (o instanceof NodePart) {
            return (NodePart) o;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new NodeContainerLayoutEditPolicy());
    }

    @Override
    protected void refreshVisuals() {
        if (getParent() == null) {
            return;
        }

        Rectangle rectangle = ((JunitContainer) this.getModel()).getJunitContainerRectangle();
        if (rectangle == null) {
            return;
        }
        if (this.getParent() != null) {
            if (this.getParent().getModel() instanceof SubjobContainer) {
                ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
            }
        }
        ((JunitContainerFigure) getFigure()).initializejunitContainer(rectangle);
    }

    @Override
    protected List getModelChildren() {
        return ((JunitContainer) this.getModel()).getElements();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent changeEvent) {
        String prop = changeEvent.getPropertyName();
        if (JunitContainer.UPDATE_JUNIT_CONTENT.equals(prop)) {
            refresh();
            List<AbstractGraphicalEditPart> childrens = getChildren();
            for (AbstractGraphicalEditPart part : childrens) {
                part.refresh();
            }
            refreshVisuals();
        } else if (prop.equals(EParameterName.ACTIVATE.getName())) {
            ((JunitContainerFigure) figure).repaint();
            refreshVisuals();
        } else if (prop.equals(Node.PERFORMANCE_DATA)) {
            refreshVisuals();
        } else if (prop.equals(Node.UPDATE_STATUS)) {
            Node node = ((NodeContainer) getModel()).getNode();
            ((JunitContainerFigure) getFigure()).updateStatus(node.getStatus());
        } else {
            if (getFigure() instanceof JunitContainerFigure) {
                ((JunitContainerFigure) getFigure()).updateData();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getAdapter(java.lang.Class)
     */
    @Override
    public Object getAdapter(Class key) {
        if (key == SnapToHelper.class) {
            List<Object> snapStrategies = new ArrayList<Object>();
            Boolean val = (Boolean) getViewer().getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);

            val = (Boolean) getViewer().getProperty(NodeSnapToGeometry.PROPERTY_SNAP_ENABLED);
            if (val != null && val.booleanValue()) {
                snapStrategies.add(new NodeSnapToGeometry(this));
            }

            val = (Boolean) getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
            if (val != null && val.booleanValue()) {
                snapStrategies.add(new SnapToGrid(this));
            }

            if (snapStrategies.size() == 0) {
                return null;
            }
            if (snapStrategies.size() == 1) {
                return snapStrategies.get(0);
            }

            SnapToHelper[] ss = new SnapToHelper[snapStrategies.size()];
            for (int i = 0; i < snapStrategies.size(); i++) {
                ss[i] = (SnapToHelper) snapStrategies.get(i);
            }
            return new CompoundSnapToHelper(ss);
        }

        return super.getAdapter(key);
    }

}

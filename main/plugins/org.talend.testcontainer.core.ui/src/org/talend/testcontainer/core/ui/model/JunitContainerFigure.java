package org.talend.testcontainer.core.ui.model;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.talend.commons.ui.runtime.image.EImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.commons.ui.utils.workbench.gef.SimpleHtmlFigure;
import org.talend.core.model.process.Problem.ProblemStatus;
import org.talend.designer.core.ui.editor.process.Process;
import org.talend.designer.core.ui.views.problems.Problems;

public class JunitContainerFigure extends Figure {

    private ImageFigure errorFigure, warningFigure;

    private SimpleHtmlFigure htmlStatusHint;

    private JunitContainer jobletContainer;

    private RoundedRectangle rectFig;

    private RGB mainColor;

    private boolean isSubjobDisplay = true;

    private RGB green = new RGB(130, 240, 100);

    /**
     * DOC hwang JobletContainerFigure constructor comment.
     * 
     * @param model
     */
    public JunitContainerFigure(final JunitContainer jobletContainer) {
        setLayoutManager(new FreeformLayout());
        this.jobletContainer = jobletContainer;
        if (this.jobletContainer.getSubjobContainer() != null) {
            isSubjobDisplay = this.jobletContainer.getSubjobContainer().isDisplayed();
        }

        rectFig = new GreenRectangle();
        errorFigure = new ImageFigure();
        errorFigure.setImage(ImageProvider.getImage(EImage.ERROR_SMALL));
        errorFigure.setVisible(false);
        errorFigure.setSize(errorFigure.getPreferredSize());
        this.add(errorFigure);

        warningFigure = new ImageFigure();
        warningFigure.setImage(ImageProvider.getImage(EImage.WARNING_SMALL));
        warningFigure.setVisible(false);
        warningFigure.setSize(warningFigure.getPreferredSize());
        this.add(warningFigure);

        htmlStatusHint = new SimpleHtmlFigure();
        updateData();

        initializejobletContainer(jobletContainer.getJobletContainerRectangle());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.Figure#paint(org.eclipse.draw2d.Graphics)
     */
    @Override
    public void paint(Graphics graphics) {
        graphics.setAlpha(100);
        if (errorFigure.isVisible()) {
            errorFigure.setLocation(jobletContainer.getErrorLocation());
        }
        if (warningFigure.isVisible()) {
            warningFigure.setLocation(jobletContainer.getWarningLocation());
        }
        super.paint(graphics);
        // refreshNodes();
    }

    boolean lastJobletRedState = false;

    private void refreshNodes() {
        rectFig.setBackgroundColor(new Color(Display.getDefault(), green));
    }

    public void initializejobletContainer(Rectangle rectangle) {
        disposeColors();
        Point location = this.getLocation();

        rectFig.setLocation(new Point(location.x, /* preferedSize.height + */location.y));
        rectFig.setSize(new Dimension(rectangle.width, rectangle.height /*- preferedSize.height*/));
        rectFig.setBackgroundColor(new Color(Display.getDefault(), green));

        rectFig.setForegroundColor(new Color(Display.getDefault(), new RGB(220, 120, 120)));
    }

    public void disposeColors() {
        if (rectFig.getForegroundColor() != null && !rectFig.getForegroundColor().isDisposed()) {
            rectFig.getForegroundColor().dispose();
        }
        if (rectFig.getBackgroundColor() != null && !rectFig.getBackgroundColor().isDisposed()) {
            rectFig.getBackgroundColor().dispose();
        }
    }

    /**
     * hwang Comment method "updateData".
     */
    public void updateData() {

        this.getChildren().remove(rectFig);
        rectFig.getChildren().clear();
        add(rectFig, null, 0);
    }

    public void updateStatus(int status) {
        if ((status & Process.ERROR_STATUS) != 0) {

            warningFigure.setVisible(false);
            errorFigure.setVisible(true);
        } else {
            errorFigure.setVisible(false);
            errorFigure.setToolTip(null);
        }

        if (((status & Process.WARNING_STATUS) != 0) && !errorFigure.isVisible()) {
            warningFigure.setVisible(true);
        } else {
            warningFigure.setVisible(false);
            warningFigure.setToolTip(null);
        }

        if (warningFigure.isVisible() || errorFigure.isVisible()) {

            List<String> problemsList;

            String text = "<b>" + jobletContainer.getNode().getUniqueName() + "</b><br><br>"; //$NON-NLS-1$ //$NON-NLS-2$

            if ((status & Process.WARNING_STATUS) != 0) {
                text += "<i>Warnings:</i><br>"; //$NON-NLS-1$

                problemsList = Problems.getStatusList(ProblemStatus.WARNING, jobletContainer.getNode());
                for (String str : problemsList) {
                    text += "\t- " + str + "<br>"; //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
            if ((status & Process.ERROR_STATUS) != 0) {
                text += "<i>Errors:</i><br>"; //$NON-NLS-1$
                problemsList = Problems.getStatusList(ProblemStatus.ERROR, jobletContainer.getNode());
                for (String str : problemsList) {
                    text += "\t- " + str + "<br>"; //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
            htmlStatusHint.setText(text);
            if (errorFigure.isVisible()) {
                errorFigure.setToolTip(htmlStatusHint);
            } else if (warningFigure.isVisible()) {
                warningFigure.setToolTip(htmlStatusHint);
            }
        } else {
            errorFigure.setVisible(false);
            errorFigure.setToolTip(null);
        }
    }

    class GreenRectangle extends RoundedRectangle {

        @Override
        protected void fillShape(Graphics graphics) {
            if (isSubjobDisplay) {
                super.fillShape(graphics);
            }
        }

        @Override
        protected void outlineShape(Graphics graphics) {
            if (isSubjobDisplay) {
                super.outlineShape(graphics);
            }
        }
    }

}

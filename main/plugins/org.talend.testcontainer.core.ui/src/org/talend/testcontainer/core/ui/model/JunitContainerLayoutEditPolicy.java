package org.talend.testcontainer.core.ui.model;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.talend.designer.core.ui.editor.cmd.CreateNodeContainerCommand;
import org.talend.designer.core.ui.editor.cmd.CreateNoteCommand;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainerLayoutEditPolicy;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.editor.notes.Note;
import org.talend.designer.core.ui.editor.process.Process;

public class JunitContainerLayoutEditPolicy extends NodeContainerLayoutEditPolicy {

    @Override
    protected Command getCreateCommand(CreateRequest request) {
        Rectangle constraint = (Rectangle) getConstraintFor(request);
        Process linkedProcess = (Process) ((JunitContainer) getHost().getModel()).getNode().getProcess();

        Command command = null;
        if (Note.class.equals(request.getNewObjectType())) {
            command = new CreateNoteCommand(linkedProcess, (Note) request.getNewObject(), constraint.getLocation());
        } else if (request.getNewObject() instanceof Node) {
            JunitContainer nodeContainer = new JunitContainer((Node) request.getNewObject());
            command = new CreateNodeContainerCommand(linkedProcess, nodeContainer, constraint.getLocation());
        }

        return command;
    }
}

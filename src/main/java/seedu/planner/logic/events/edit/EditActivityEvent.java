package seedu.planner.logic.events.edit;

import java.util.List;

import seedu.planner.commons.core.Messages;
import seedu.planner.commons.core.index.Index;
import seedu.planner.logic.commands.UndoableCommand;
import seedu.planner.logic.commands.editcommand.EditActivityCommand;
import seedu.planner.logic.commands.editcommand.EditActivityCommand.EditActivityDescriptor;
import seedu.planner.logic.events.Event;
import seedu.planner.logic.events.exceptions.EventException;
import seedu.planner.model.Model;
import seedu.planner.model.activity.Activity;

/**
 * An event representing a 'edit activity' command.
 */
public class EditActivityEvent implements Event {
    private final Index index;
    private final EditActivityDescriptor editInfo;
    private final EditActivityDescriptor reverseEditInfo;

    public EditActivityEvent(Index index, EditActivityDescriptor editInfo, Model model) throws EventException {
        this.index = index;
        this.editInfo = editInfo;
        this.reverseEditInfo = generateReverseEditInfo(model);
    }

    public UndoableCommand undo() {
        return new EditActivityCommand(index, reverseEditInfo, true);
    }

    public UndoableCommand redo() {
        return new EditActivityCommand(index, editInfo);
    }

    /**
     * A method to construct an EditActivityDescriptor based on the current Activity to edit in the model.
     * @param model Current model in the application.
     * @return the EditActivityDescriptor containing information of the original Activity to be edited.
     */
    private EditActivityDescriptor generateReverseEditInfo(Model model) throws EventException {
        EditActivityDescriptor result = new EditActivityDescriptor();

        List<Activity> lastShownList = model.getFilteredActivityList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new EventException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        Activity originalActivity = lastShownList.get(index.getZeroBased());

        result.setName(originalActivity.getName());
        result.setAddress(originalActivity.getAddress());
        if (originalActivity.getContact().isPresent()) {
            result.setPhone(originalActivity.getContact().get().getPhone());
        } else {
            result.setPhone(null);
        }
        result.setTags(originalActivity.getTags());

        return result;
    }
}

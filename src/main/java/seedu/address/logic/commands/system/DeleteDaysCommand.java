package seedu.address.logic.commands.system;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.UndoableCommand;
import seedu.address.logic.commands.result.CommandResult;
import seedu.address.model.Model;

/**
 * A command that is not usable by a User, only exist to assist in undoing the effects of  Clear command.
 */
public class DeleteDaysCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "deletedays";
    public static final String MESSAGE_SUCCESS = "Add days command successfully undone.";

    private final int numberOfDays;

    public DeleteDaysCommand(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.deleteDays(numberOfDays);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

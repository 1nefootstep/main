package seedu.planner.logic.commands.deletecommand;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.planner.commons.core.Messages;
import seedu.planner.commons.core.index.Index;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.logic.commands.result.CommandResult;
import seedu.planner.logic.commands.result.UiFocus;
import seedu.planner.logic.commands.util.HelpExplanation;
import seedu.planner.model.Model;
import seedu.planner.model.day.Day;

/**
 * Removes a Day from the itinerary.
 */
public class DeleteDayCommand extends DeleteCommand {
    public static final String SECOND_COMMAND_WORD = "day";

    public static final HelpExplanation MESSAGE_USAGE = new HelpExplanation(
            COMMAND_WORD + " " + SECOND_COMMAND_WORD,
            "Deletes the day identified by the index "
                    + "number used in the displayed day list.",
            COMMAND_WORD + " " + SECOND_COMMAND_WORD + " INDEX(must be a positive integer)",
            COMMAND_WORD + " " + SECOND_COMMAND_WORD + " 4"
    );

    public static final String MESSAGE_DELETE_DAY_SUCCESS = "Deleted day: %1$d";

    private final Index targetIndex;

    public DeleteDayCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public Index getTargetIndex() {
        return targetIndex;
    }

    @Override
    public String getSecondCommandWord() {
        return SECOND_COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Day> lastShownList = model.getFilteredItinerary();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX);
        }

        Day dayToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteDay(dayToDelete);
        return new CommandResult(
            String.format(MESSAGE_DELETE_DAY_SUCCESS, targetIndex.getOneBased()),
            new UiFocus[] {UiFocus.AGENDA}
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDayCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteDayCommand) other).targetIndex)); // state check
    }
}

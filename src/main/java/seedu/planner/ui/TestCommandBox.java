package seedu.planner.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import seedu.planner.logic.commands.exceptions.CommandException;
import seedu.planner.logic.commands.result.CommandResult;
import seedu.planner.logic.events.exceptions.EventException;
import seedu.planner.logic.parser.exceptions.ParseException;
import seedu.planner.ui.autocomplete.AutoCompleteTextField;

import java.util.Arrays;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class TestCommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "errorCommandBox";
    private static final String FXML = "TestCommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private AutoCompleteTextField commandTextField;

    public TestCommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandTextField.getEntries().addAll(Arrays.asList("add activity", "AB", "ABC"));
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        // commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            commandExecutor.execute(commandTextField.getText());
            commandTextField.setText("");
            setStyleToDefault();
        } catch (CommandException | ParseException | EventException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.planner.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException, EventException;
    }

}
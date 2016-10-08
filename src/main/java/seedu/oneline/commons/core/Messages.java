package seedu.oneline.commons.core;

import static seedu.oneline.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.oneline.logic.commands.DeleteCommand;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";

    public static String getInvalidCommandFormatMessage(String usage) {
        return String.format(MESSAGE_INVALID_COMMAND_FORMAT, usage);
    }
    
}

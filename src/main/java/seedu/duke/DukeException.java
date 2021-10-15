package seedu.duke;

import seedu.duke.commons.core.Messages;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String message = getLocalizedMessage();
        return (message != null) ? message : Messages.ERROR_UNKNOWN;
    }
}


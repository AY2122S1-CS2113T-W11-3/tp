package seedu.duke.commons.core;

public enum CommandType {
    ADD,
    ALL,
    DELETE,
    DONE,
    EXIT,
    FIND,
    INVALID,
    LESSON,
    LIST,
    TASK;

    /**
     * Determines the correct command type from the input parameter.
     *
     * @param param the input parameter
     * @return the command type of the input parameter
     */
    public static CommandType of(String param) {
        for (CommandType commandType : CommandType.values()) {
            if (param.equalsIgnoreCase(commandType.toString())) {
                return commandType;
            }
        }
        return INVALID;
    }
}

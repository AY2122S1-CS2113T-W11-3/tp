package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Messages;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.DeleteLessonCommand;
import seedu.duke.logic.commands.task.DeleteTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.parseToZeroIndex;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

public class DeleteCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case LESSON:
            simplifiedUserResponse = removeFirstParam(userResponse, "lesson");
            return parseDeleteLessonCommand(simplifiedUserResponse);
        case TASK:
            simplifiedUserResponse = removeFirstParam(userResponse, "task");
            return parseDeleteTaskCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    private static Command parseDeleteLessonCommand(String userResponse) throws ParseException {
        try {
            int lessonIndex = parseToZeroIndex(Integer.parseInt(userResponse));
            return new DeleteLessonCommand(lessonIndex);
        } catch (NumberFormatException e) {
            throw new ParseException(Messages.ERROR_INVALID_NUMBER);
        }
    }

    private static Command parseDeleteTaskCommand(String userResponse) throws ParseException {
        try {
            int taskIndex = parseToZeroIndex(Integer.parseInt(userResponse));
            return new DeleteTaskCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new ParseException(Messages.ERROR_INVALID_NUMBER);
        }
    }
}

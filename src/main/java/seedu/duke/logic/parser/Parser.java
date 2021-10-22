package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Messages;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.module.ModuleInfoCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

public class Parser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case ADD:
            simplifiedUserResponse = removeFirstParam(userResponse, "add");
            return AddCommandParser.parse(simplifiedUserResponse);
        case DELETE:
            simplifiedUserResponse = removeFirstParam(userResponse, "delete");
            return DeleteCommandParser.parse(simplifiedUserResponse);
        case DONE:
            simplifiedUserResponse = removeFirstParam(userResponse, "done");
            return DoneCommandParser.parse(simplifiedUserResponse);
        case FIND:
            simplifiedUserResponse = removeFirstParam(userResponse, "find");
            return FindCommandParser.parse(simplifiedUserResponse);
        case LIST:
            simplifiedUserResponse = removeFirstParam(userResponse, "list");
            return ListCommandParser.parse(simplifiedUserResponse);
        case MODULEINFO:
            simplifiedUserResponse = removeFirstParam(userResponse, "moduleinfo");
            return ModuleInfoCommandParser.parse(simplifiedUserResponse);
        case EXIT:
            return ExitCommandParser.parse();
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }
}

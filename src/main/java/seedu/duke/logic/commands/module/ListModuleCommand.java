package seedu.duke.logic.commands.module;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class ListModuleCommand extends Command {
    public final String argument;

    public ListModuleCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException, IOException {
        assert (argument.equalsIgnoreCase("all") | argument.isEmpty())
                : "list module command currently only accepts empty or \"all\"";
        if (argument.equalsIgnoreCase("all")) {
            ui.printModulesWithDetails(moduleList);
        } else {
            ui.printModuleList(moduleList);
        }
    }
}

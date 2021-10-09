package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DeleteTaskCommand extends DeleteCommand {
    private int taskIndex;

    public DeleteTaskCommand() {
        this.isDeleteAll = true;
    }

    public DeleteTaskCommand(int taskIndex) {
        this.isDeleteAll = false;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, LessonList lessonList, Storage storage) throws DukeException {
        if (isDeleteAll) {
            taskList.clearTaskList();
            ui.printDeletedAllTasks();
        } else {
            Task deletedTask = taskList.getTask(taskIndex);
            taskList.deleteTask(taskIndex);
            ui.printDeletedTask(deletedTask, taskList.getSize());
        }
        try {
            storage.saveData(taskList, lessonList);
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }
}

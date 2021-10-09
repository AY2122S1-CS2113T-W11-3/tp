package seedu.duke.command;

import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DeleteAllCommand extends DeleteCommand {
    @Override
    public void execute(Ui ui, TaskList taskList, LessonList lessonList, Storage storage) throws IOException {
        taskList.clearTaskList();
        lessonList.clearLessonList();
        ui.printDeletedAll();
        storage.saveData(taskList, lessonList);
    }
}

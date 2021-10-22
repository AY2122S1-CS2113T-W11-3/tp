package seedu.duke.ui;

import java.util.Scanner;

import seedu.duke.Duke;
import seedu.duke.DukeException;
import seedu.duke.model.lesson.Lesson;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.Module;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;

import static seedu.duke.commons.util.DayUtil.getToday;
import static seedu.duke.commons.util.DayUtil.getTomorrow;
import static seedu.duke.commons.util.DayUtil.isToday;
import static seedu.duke.commons.util.DayUtil.isTomorrow;

public class Ui {
    public static final String LINE =
            "    _______________________________________________________________________________"
                    + System.lineSeparator();

    public static final String PADDING = "     ";

    /**
     * Temporary logo.
     */
    public static final String LOGO = PADDING
            + "  _   _       _   _   ____           ____     _   _   ____     ____   __   __ "
            + System.lineSeparator() + PADDING
            + " | \\ |\"|   U |\"|u| | / __\"| u     U | __\")uU |\"|u| | |  _\"\\   |  _\"\\  \\ \\ / / "
            + System.lineSeparator() + PADDING
            + "<|  \\| |>   \\| |\\| |<\\___ \\/       \\|  _ \\/ \\| |\\| |/| | | | /| | | |  \\ V /  "
            + System.lineSeparator() + PADDING
            + "U| |\\  |u    | |_| | u___) |        | |_) |  | |_| |U| |_| |\\U| |_| |\\U_|\"|_u "
            + System.lineSeparator() + PADDING
            + " |_| \\_|    <<\\___/  |____/>>       |____/  <<\\___/  |____/ u |____/ u  |_|   "
            + System.lineSeparator() + PADDING
            + " ||   \\\\,-.(__) )(    )(  (__)     _|| \\\\_ (__) )(    |||_     |||_ .-,//|(_  "
            + System.lineSeparator() + PADDING
            + " (_\")  (_/     (__)  (__)         (__) (__)    (__)  (__)_)   (__)_) \\_) (__) "
            + System.lineSeparator();

    private final Scanner sc = new Scanner(System.in);

    /**
     * Reads user response from the standard input.
     *
     * @return the user response, with leading and trailing whitespaces removed
     */
    public String readUserResponse() {
        return sc.nextLine().strip();
    }

    /**
     * Greets user by displaying the logo.
     */
    public void printGreeting() {
        System.out.print(LINE);
        System.out.print(LOGO);
        System.out.println(LINE);
    }

    /**
     * Displays goodbye message to user on exit.
     */
    public void printExit() {
        System.out.print(LINE);
        System.out.println(PADDING + "Bye!");
        System.out.print(LINE);
    }

    /**
     * Displays the specified custom message.
     *
     * @param message the specified message
     */
    public void printMessage(String message) {
        System.out.print(LINE);
        System.out.println(PADDING + message);
        System.out.println(LINE);
    }

    // Task-related methods

    /**
     * Displays a message to inform user that the specified task
     * has been successfully added.
     *
     * @param task the specified task
     * @param size the number of tasks in the list
     */
    public void printTaskAdded(Task task, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Noted. I've added this task:");
        System.out.println(PADDING + "  " + task);
        System.out.println(PADDING + "Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a message to inform user that the specified task
     * has been successfully deleted.
     *
     * @param task the Task type object that has been deleted
     * @param size the number of remaining tasks in the list
     */
    public void printTaskDeleted(Task task, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Ok. The following task has been deleted:");
        System.out.println(PADDING + "  " + task);
        System.out.println(PADDING + "Now you have " + size + " task(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a message to inform user that the specified task
     * has been successfully marked as done.
     *
     * @param taskList the list of tasks
     * @param task the task that had been marked as done
     */
    public void printTaskMarkedAsDone(TaskList taskList, Task task) {
        System.out.print(LINE);
        System.out.println(PADDING + "Nice! I've marked this task as done: ");
        System.out.println(PADDING + "  " + task);
        System.out.println(PADDING + "Now you have " + taskList.getNumberOfPendingTasks() + " pending tasks.");
        System.out.println(LINE);
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList the list of tasks
     */
    public void printTaskList(TaskList taskList) {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the tasks in your list:");
        System.out.print(taskList);
        System.out.println(LINE);
    }

    // TODO: Combine the two methods below

    /**
     * Displays the list of tasks filtered based on the specified keyword.
     *
     * @param taskList the unfiltered list of tasks
     * @param keyword the specified keyword
     */
    public void printTasksWithKeyword(TaskList taskList, String keyword) {
        TaskList filteredTaskList = taskList.filterTasksByKeyword(keyword);

        System.out.print(LINE);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "There is no matching task in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching tasks in your list:");
            System.out.print(filteredTaskList);
        }
        System.out.println(LINE);
    }

    /**
     * Displays the list of tasks filtered based on the specified period.
     *
     * @param taskList the initial task list
     * @param period the specified period
     */
    public void printTasksWithPeriod(TaskList taskList, String period) {
        if (isToday(period)) {
            period = getToday();
        } else if (isTomorrow(period)) {
            period = getTomorrow();
        }

        TaskList filteredTaskList = taskList.filterTasksByPeriod(period);

        System.out.print(LINE);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "There is no task on " + period.toUpperCase() + ".");
        } else {
            System.out.println(PADDING + "Here are the tasks on " + period.toUpperCase() + ":");
            System.out.print(filteredTaskList);
        }
        System.out.println(LINE);
    }

    // Lesson-related methods

    /**
     * Displays a message to inform user that the specified lesson
     * has been successfully added.
     *
     * @param lesson the specified lesson
     * @param size the number of lessons in the list
     */
    public void printLessonAdded(Lesson lesson, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Noted. I've added this lesson:");
        System.out.println(PADDING + "  " + lesson);
        System.out.println(PADDING + "Now you have " + size + " lessons in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a message to inform user that the specified lesson
     * has been successfully deleted.
     *
     * @param lesson the Lesson type object that has been deleted
     * @param size the number of remaining lessons in the list
     */
    public void printLessonDeleted(Lesson lesson, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Ok. The following lesson has been deleted:");
        System.out.println(PADDING + "  " + lesson);
        System.out.println(PADDING + "Now you have " + size + " lesson(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays the list of lessons.
     *
     * @param lessonList the list of lessons
     */
    public void printLessonList(LessonList lessonList) {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the lessons in your list:");
        System.out.print(lessonList);
        System.out.println(LINE);
    }

    // TODO: Combine the two methods below

    /**
     * Displays the list of lessons filtered based on the specified keyword.
     *
     * @param lessonList the unfiltered list of lessons
     * @param keyword the specified keyword
     */
    public void printLessonsWithKeyword(LessonList lessonList, String keyword) {
        LessonList filteredLessonList = lessonList.filterLessonsByKeyword(keyword);

        System.out.print(LINE);
        if (filteredLessonList.isEmpty()) {
            System.out.println(PADDING + "There is no matching lesson in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching lessons in your list:");
            System.out.print(filteredLessonList);
        }
        System.out.println(LINE);
    }

    /**
     * Displays the list of lessons filtered based on the specified period.
     *
     * @param lessonList the unfiltered list of lessons
     * @param period the specified period
     */
    public void printLessonsWithPeriod(LessonList lessonList, String period) {
        if (isToday(period)) {
            period = getToday();
        } else if (isTomorrow(period)) {
            period = getTomorrow();
        }

        LessonList filteredLessonList = lessonList.filterLessonsByPeriod(period);

        System.out.print(LINE);
        if (filteredLessonList.isEmpty()) {
            System.out.println(PADDING + "There is no lesson on " + period.toUpperCase() + ".");
        } else {
            System.out.println(PADDING + "Here are the lessons on " + period.toUpperCase() + ":");
            System.out.print(filteredLessonList);
        }
        System.out.println(LINE);
    }

    // Module-related methods

    /**
     * Displays a message to inform user that the specified module
     * has been successfully added.
     *
     * @param module the specified module added
     * @param size the number of modules in the list
     */
    public void printModuleAdded(Module module, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Noted. I've added this module:");
        System.out.println(PADDING + "  " + module);
        System.out.println(PADDING + "Now you have " + size + " modules in the list.");
        System.out.println(LINE);
    }

    public void printModuleDeleted(Module module, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Ok. The following module has been deleted:");
        System.out.println(PADDING + "  " + module);
        System.out.println(PADDING + "Now you have " + size + " module(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays the list of modules with only the basic module information.
     *
     * @param moduleList the list of modules
     */
    public void printModuleList(ModuleList moduleList) {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the modules in your list:");
        System.out.print(moduleList);
        System.out.print(LINE);
    }

    /**
     * Displays the list of modules with the full module information.
     *
     * @param moduleList the list of modules
     * @throws DukeException when there is an error in retrieving the full module information of a module in the list
     */
    public void printModulesWithDetails(ModuleList moduleList) throws DukeException {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the detailed information of your modules:");
        System.out.print(Duke.fullModuleList.getModulesFull(moduleList));
        System.out.print(LINE);
    }

    // All-related methods

    /**
     * Displays both the list of tasks and lessons.
     *
     * @param taskList the list of tasks
     * @param lessonList the list of lessons
     */
    public void printAllList(TaskList taskList, LessonList lessonList) {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the tasks in your list:");
        System.out.println(taskList);
        System.out.println(PADDING + "Here are the lessons in your list:");
        System.out.print(lessonList);
        System.out.println(LINE);
    }

    // TODO: Combine the two methods below

    /**
     * Displays the list of tasks and lessons filtered based on the
     * specified keyword.
     *
     * @param taskList the unfiltered list of tasks
     * @param lessonList the unfiltered list of lessons
     * @param keyword the specified keyword
     */
    public void printAllWithKeyword(TaskList taskList, LessonList lessonList, String keyword) {
        System.out.print(LINE);

        TaskList filteredTaskList = taskList.filterTasksByKeyword(keyword);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "There is no matching task in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching tasks in your list:");
            System.out.print(filteredTaskList);
        }
        System.out.println();

        LessonList filteredLessonList = lessonList.filterLessonsByKeyword(keyword);
        if (filteredLessonList.isEmpty()) {
            System.out.println(PADDING + "There is no matching lesson in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching lessons in your list:");
            System.out.print(filteredLessonList);
        }

        System.out.println(LINE);
    }

    /**
     * Displays the list of tasks and lessons filtered based on the
     * specified period.
     *
     * @param taskList the unfiltered list of tasks
     * @param lessonList the unfiltered list of lessons
     * @param period the specified period
     */
    public void printAllWithPeriod(TaskList taskList, LessonList lessonList, String period) {
        System.out.print(LINE);

        TaskList filteredTaskList = taskList.filterTasksByPeriod(period);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "There is no task on " + period.toUpperCase() + ".");
        } else {
            System.out.println(PADDING + "Here are the tasks on " + period.toUpperCase() + ":");
            System.out.print(filteredTaskList);
        }

        LessonList filteredLessonList = lessonList.filterLessonsByPeriod(period);
        if (filteredLessonList.isEmpty()) {
            System.out.println(PADDING + "There is no lesson on " + period.toUpperCase() + ".");
        } else {
            System.out.println(PADDING + "Here are the lessons on " + period.toUpperCase() + ":");
            System.out.print(filteredLessonList);
        }

        System.out.println(LINE);
    }
}

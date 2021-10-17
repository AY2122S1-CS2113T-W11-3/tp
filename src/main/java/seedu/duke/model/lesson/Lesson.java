package seedu.duke.model.lesson;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.commons.core.Messages;
import seedu.duke.commons.util.TimeUtil;
import seedu.duke.commons.util.exceptions.TimeParseException;
import seedu.duke.model.lesson.exceptions.DeserializeLessonException;
import seedu.duke.ui.Ui;

import static seedu.duke.commons.core.DayOfTheWeek.is;
import static seedu.duke.commons.core.DayOfTheWeek.toProper;

public class Lesson {
    private final String title;
    private final String dayOfTheWeek;
    private final String startTime;
    private final String endTime;
    private final String meetingUrl;

    public Lesson(String title, String dayOfTheWeek, String startTime, String endTime, String meetingUrl) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingUrl = meetingUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public String endTime() {
        return endTime;
    }

    public String getMeetingUrl() {
        return meetingUrl;
    }

    /**
     * Serializes the lesson data into the correct format for storage file.
     *
     * @return serialized lesson data
     */
    public String serialize() {
        return title + " | " + dayOfTheWeek + " | " + startTime + " | " + endTime + " | " + meetingUrl;
    }

    /**
     * Deserializes the lesson data from the storage file.
     *
     * @param line a line of string representing the serialized data
     * @return deserialized lesson data
     */
    public static Lesson deserialize(Ui ui, String line) {
        try {
            String[] params = line.split("\\s*[|]\\s*");

            String title = params[0];

            String dayOfTheWeek = params[1];
            if (!is(dayOfTheWeek)) {
                throw new DeserializeLessonException(Messages.ERROR_DESERIALIZING_LESSON);
            }
            dayOfTheWeek = toProper(dayOfTheWeek);

            String startTime = LocalTime.parse(TimeUtil.parseTwelveHourClock(params[2]))
                    .format(DateTimeFormatter.ofPattern("hh:mm a"));

            String endTime = LocalTime.parse(TimeUtil.parseTwelveHourClock(params[3]))
                    .format(DateTimeFormatter.ofPattern("hh:mm a"));

            String meetingUrl = params[4];

            return new Lesson(title, dayOfTheWeek, startTime, endTime, meetingUrl);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | DeserializeLessonException
                | TimeParseException e) {
            // Ignoring the particular line
            ui.printMessage(Messages.ERROR_DESERIALIZING_LESSON);
            return null;
        }
    }

    @Override
    public String toString() {
        return title + System.lineSeparator()
                + Ui.PADDING + "   " + dayOfTheWeek + ", " + startTime + " - " + endTime + System.lineSeparator()
                + Ui.PADDING + "   " + "Meeting URL: " + meetingUrl;
    }
}

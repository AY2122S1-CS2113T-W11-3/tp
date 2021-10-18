package seedu.duke.commons.core;

public enum DayOfTheWeek {
    MON, TUE, WED, THU, FRI, SAT, SUN;

    /**
     * Determines if the input parameter is a valid day of the week.
     *
     * @param param the input parameter
     * @return true if the input is valid, false otherwise
     */
    public static boolean is(String param) {
        for (DayOfTheWeek day : DayOfTheWeek.values()) {
            if (param.substring(0, 3).equalsIgnoreCase(day.toString())) {
                return true;
            }
        }
        return false;
    }

    public static String toProper(String param) {
        switch (param.substring(0, 3).toLowerCase()) {
        case "mon":
            return "Monday";
        case "tue":
            return "Tuesday";
        case "wed":
            return "Wednesday";
        case "thu":
            return "Thursday";
        case "fri":
            return "Friday";
        case "sat":
            return "Saturday";
        case "sun":
            return "Sunday";
        default:
            return "";
        }
    }
}

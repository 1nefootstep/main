package seedu.address.model.day;

import java.util.List;

/**
 * Represents a Day in the travel planner's planner.
 * Guarantees: timetable is present and not null, field values are validated, immutable.
 */
public class Day {
    public static final String MESSAGE_CONSTRAINTS = "Number of days should be an integer greater than 0.";
    public static final String VALIDATION_REGEX = "^[1-9]\\d*$\n";

    private final Timetable timetable;

    public Day() {
        this.timetable = new Timetable();
    }

    public Day(List<ActivityWithTime> activitiesForDay) {
        this.timetable = new Timetable(activitiesForDay);
    }

    /**
     * Returns true if both days contain the same activities.
     */
    public boolean isSameDay(Day otherDay) {
        if (otherDay == this) {
            return true;
        }
        return timetable.equals(otherDay.timetable);
    }
    /**
     * Returns true if a given string is a valid integer.
     */
    public static boolean isValidDayNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Day)) {
            return false;
        }

        Day otherDay = (Day) other;
        return otherDay.timetable.equals(this.timetable);
    }
}

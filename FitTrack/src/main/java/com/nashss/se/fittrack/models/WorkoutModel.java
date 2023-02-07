package com.nashss.se.fittrack.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Represents a workout object.
 */
public class WorkoutModel {
    private String name;
    private Date date;
    private List<String> notes;

    /**
     * Instantiates a WorkoutModel object.
     * @param name the name of the workout.
     * @param date the date the workout was created.
     * @param notes the notes for each exercise added into the workout.
     */
    public WorkoutModel(String name, Date date, List<String> notes) {
        this.name = name;
        this.date = date;
        this.notes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public List<String> getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkoutModel that = (WorkoutModel) o;
        return Objects.equals(name, that.name) && Objects.equals(date, that.date) &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, notes);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Date date;
        private List<String> notes;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withNotes(List<String> notes) {
            this.notes = notes;
            return this;
        }

        public WorkoutModel build() {
            return new WorkoutModel(name, date, notes);
        }
    }


}

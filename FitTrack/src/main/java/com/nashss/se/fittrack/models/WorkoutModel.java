package com.nashss.se.fittrack.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.nashss.se.fittrack.utils.CollectionUtils.copyToList;

/**
 * Represents a workout object.
 */
public class WorkoutModel {
    private String name;
    private String date;
    private String email;
    private List<String> notes;

    /**
     * Instantiates a WorkoutModel object.
     * @param name the name of the workout.
     * @param date the date the workout was created.
     * @param notes the notes for each exercise added into the workout.
     */
    public WorkoutModel(String name, String date, List<String> notes, String email) {
        this.name = name;
        this.date = date;
        this.notes = new ArrayList<>();
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public List<String> getNotes() {
        return copyToList(notes);
    }

    public String getEmail() {
        return email;
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
                Objects.equals(notes, that.notes) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, notes, email);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String date;
        private String email;
        private List<String> notes;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public Builder withNotes(List<String> notes) {
            this.notes = copyToList(notes);
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public WorkoutModel build() {
            return new WorkoutModel(name, date, notes, email);
        }
    }


}

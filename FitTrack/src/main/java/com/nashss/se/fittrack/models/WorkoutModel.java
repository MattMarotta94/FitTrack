package com.nashss.se.fittrack.models;

import java.util.Objects;

/**
 * Represents a workout object.
 */
public class WorkoutModel {
    private String name;
    private String date;
    private String exercises;
    private String notes;
    private String email;


    /**
     * Instantiates a WorkoutModel object.
     * @param name the name of the workout.
     * @param date the date the workout was created.
     * @param exercises the exercises entered for the workout.
     * @param notes the notes for each exercise added into the workout.
     * @param email the email for the users profile determined using Cognito.
     */
    public WorkoutModel(String name, String date, String exercises, String notes, String email) {
        this.name = name;
        this.date = date;
        this.exercises = exercises;
        this.notes = notes;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public String getEmail() {
        return email;
    }

    public String getExercises() {
        return exercises;
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
        private String notes;
        private String exercises;
        private String email;



        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public Builder withExercises(String exercises) {
            this.exercises = exercises;
            return this;
        }

        public Builder withNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public WorkoutModel build() {
            return new WorkoutModel(name, date, exercises, notes, email);
        }
    }


}

package com.nashss.se.fittrack.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Implementation of the CreateWorkoutRequest.
 */
@JsonDeserialize(builder = CreateWorkoutRequest.Builder.class)
public class CreateWorkoutRequest {
    private final String name;
    private final String date;
    private final String exercises;
    private final String notes;

    private final String email;

    /**
     * Instantiates a new CreateWorkoutRequest object.
     * @param name the name of the workout.
     * @param date the date the workout was created.
     * @param notes any notes logged by the user.
     */
    private CreateWorkoutRequest(String name, String date, String exercises, String notes, String email) {
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

    public String getExercises() {
        return exercises;
    }

    public String getNotes() {
        return notes;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "CreateWorkoutRequest{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", exercises='" + exercises + '\'' +
                ", notes='" + notes + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private String date;
        private String exercises;
        private String notes;

        private String email;

        //CHECKSTYLE:OFF:Builder
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

        public CreateWorkoutRequest build() {
            return new CreateWorkoutRequest(name, date, exercises, notes, email);
        }
    }
}

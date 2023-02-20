package com.nashss.se.fittrack.activity.requests;
import com.nashss.se.fittrack.dynamodb.models.Exercise;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

/**
 * Implementation of the UpdateWorkoutRequest.
 */
@JsonDeserialize(builder = UpdateWorkoutRequest.Builder.class)
public class UpdateWorkoutRequest {
    private final String name;
    private final String date;
    private final String notes;
    private final String email;
    private final String exercises;

    private UpdateWorkoutRequest(String name, String date, String notes, String email, String exercises) {
        this.name = name;
        this.date = date;
        this.notes = notes;
        this.email = email;
        this.exercises = exercises;
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
    public String toString() {
        return "UpdateWorkoutRequest{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", notes='" + notes + '\'' +
                ", email='" + email + '\'' +
                ", exercises=" + exercises +
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
        private String notes;
        private String email;
        private String exercises;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
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

        public Builder withExercises(String exercises) {
            this.exercises= exercises;
            return this;
        }

        public UpdateWorkoutRequest build() {
            return new UpdateWorkoutRequest(name, date, notes, email, exercises);
        }
    }
}

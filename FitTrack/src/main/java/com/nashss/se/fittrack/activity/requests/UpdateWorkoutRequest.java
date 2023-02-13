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
    private final List<Exercise> exerciseList;

    private UpdateWorkoutRequest(String name, String date, String notes, String email, List<Exercise> exerciseList) {
        this.name = name;
        this.date = date;
        this.notes = notes;
        this.email = email;
        this.exerciseList = exerciseList;
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

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }


    @Override
    public String toString() {
        return "UpdateWorkoutRequest{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", notes='" + notes + '\'' +
                ", email='" + email + '\'' +
                ", exerciseList=" + exerciseList +
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
        private List<Exercise> exerciseList;

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

        public Builder withExerciseList(List<Exercise> exerciseList) {
            this.exerciseList = exerciseList;
            return this;
        }

        public UpdateWorkoutRequest build() {
            return new UpdateWorkoutRequest(name, date, notes, email, exerciseList);
        }
    }
}

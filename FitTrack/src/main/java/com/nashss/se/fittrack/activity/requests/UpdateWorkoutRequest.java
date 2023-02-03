package com.nashss.se.fittrack.activity.requests;
import com.nashss.se.fittrack.dynamodb.models.Exercise;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;
import java.util.List;

@JsonDeserialize(builder = UpdateWorkoutRequest.Builder.class)
public class UpdateWorkoutRequest {
    private final String name;
    private final Date date;
    private final String notes;
    private final List<Exercise> exerciseList;

    private UpdateWorkoutRequest(String name, Date date, String notes, List<Exercise> exerciseList) {
        this.name = name;
        this.date = date;
        this.notes = notes;
        this.exerciseList = exerciseList;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }


    @Override
    public String toString() {
        return "UpdateWorkoutRequest{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                ", exerciseList=" + exerciseList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private Date date;
        private String notes;
        private List<Exercise> exerciseList;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder withExerciseList(List<Exercise> exerciseList) {
            this.exerciseList = exerciseList;
            return this;
        }

        public UpdateWorkoutRequest build() {
            return new UpdateWorkoutRequest(name, date, notes, exerciseList);
        }
    }
}

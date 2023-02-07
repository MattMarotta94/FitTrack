package com.nashss.se.fittrack.activity.requests;
import com.nashss.se.fittrack.models.ExerciseModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;
import java.util.List;

import static com.nashss.se.fittrack.utils.CollectionUtils.copyToList;

/**
 * Implementation of the CreateWorkoutRequest.
 */
@JsonDeserialize(builder = CreateWorkoutRequest.Builder.class)
public class CreateWorkoutRequest {
    private final String name;
    private final Date date;
    private final String notes;
    private final List<ExerciseModel> exerciseModelList;

    /**
     * Instantiates a new CreateWorkoutRequest object.
     * @param name the name of the workout.
     * @param date the date the workout was created.
     * @param notes any notes logged by the user.
     * @param exerciseModelList a list of exercise objects.
     */
    private CreateWorkoutRequest(String name, Date date, String notes, List<ExerciseModel> exerciseModelList) {
        this.name = name;
        this.date = date;
        this.notes = notes;
        this.exerciseModelList = exerciseModelList;
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

    public List<ExerciseModel> getExerciseList() {
        return copyToList(exerciseModelList);
    }

    @Override
    public String toString() {
        return "CreateWorkoutRequest{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                ", exerciseList=" + exerciseModelList +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private Date date;
        private String notes;
        private List<ExerciseModel> exerciseModelList;
        //CHECKSTYLE:OFF:Builder
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

        public Builder withExerciseList(List<ExerciseModel> exerciseModelList) {
            this.exerciseModelList = exerciseModelList;
            return this;
        }

        public CreateWorkoutRequest build() {
            return new CreateWorkoutRequest(name, date, notes, exerciseModelList);
        }
    }
}

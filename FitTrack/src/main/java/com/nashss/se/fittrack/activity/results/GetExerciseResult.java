package com.nashss.se.fittrack.activity.results;

import com.nashss.se.fittrack.models.ExerciseModel;

public class GetExerciseResult {

    private final ExerciseModel exercise;

    private GetExerciseResult(ExerciseModel exerciseModel) {
        this.exercise = exerciseModel;
    }

    public ExerciseModel getExercise() {
        return exercise;
    }

    @Override
    public String toString() {
        return "GetExerciseResult{" +
                "exerciseModel=" + exercise +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ExerciseModel exercise;

        public Builder withExercise(ExerciseModel exercise) {
            this.exercise = exercise;
            return this;
        }

        public GetExerciseResult build() {
            return new GetExerciseResult(exercise);
        }
    }
}

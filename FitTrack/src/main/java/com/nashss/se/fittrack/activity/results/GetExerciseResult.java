package com.nashss.se.fittrack.activity.results;

import com.nashss.se.fittrack.models.ExerciseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * GetExerciseResult.
 */
public class GetExerciseResult {

    private final List<ExerciseModel> exerciseList;

    private GetExerciseResult(List<ExerciseModel> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public List<ExerciseModel> getExerciseList() {
        return exerciseList;
    }

    @Override
    public String toString() {
        return "GetExerciseResult{" +
                "exerciseList=" + exerciseList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ExerciseModel> exerciseList;

        public Builder withExercise(List<ExerciseModel> exerciseList) {
            this.exerciseList = new ArrayList<>(exerciseList);
            return this;
        }

        public GetExerciseResult build() {
            return new GetExerciseResult(exerciseList);
        }
    }
}

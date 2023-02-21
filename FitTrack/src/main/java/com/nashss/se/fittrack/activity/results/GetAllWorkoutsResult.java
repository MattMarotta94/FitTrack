package com.nashss.se.fittrack.activity.results;

import com.nashss.se.fittrack.models.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

public class GetAllWorkoutsResult {
    private final List<WorkoutModel> workoutModelList;

    public GetAllWorkoutsResult(List<WorkoutModel> workoutModelList) {
        this.workoutModelList = workoutModelList;
    }

    public List<WorkoutModel> getWorkoutModelList() {
        return workoutModelList;
    }

    @Override
    public String toString() {
        return "GetAllWorkoutsResult{" +
                "workoutModelList=" + workoutModelList +
                '}';
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<WorkoutModel> workoutModelList;

        public Builder withWorkout(List<WorkoutModel> workoutModelList) {
            this.workoutModelList = new ArrayList<>(workoutModelList);
            return this;
        }

        public GetAllWorkoutsResult build() {
            return new GetAllWorkoutsResult(workoutModelList);
        }
    }
}

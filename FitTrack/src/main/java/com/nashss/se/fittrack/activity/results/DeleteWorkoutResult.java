package com.nashss.se.fittrack.activity.results;

import com.nashss.se.fittrack.models.WorkoutModel;

public class DeleteWorkoutResult {
    private final WorkoutModel workout;

    public DeleteWorkoutResult(WorkoutModel workout) {
        this.workout = workout;
    }

    public WorkoutModel getWorkout() {
        return workout;
    }

    @Override
    public String toString() {
        return "DeleteWorkoutResult{" +
                "workout=" + workout +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private WorkoutModel workout;

        public Builder withWorkout(WorkoutModel workout) {
            this.workout = workout;
            return this;
        }
        public DeleteWorkoutResult build() {
            return new DeleteWorkoutResult(workout);
        }
    }
}

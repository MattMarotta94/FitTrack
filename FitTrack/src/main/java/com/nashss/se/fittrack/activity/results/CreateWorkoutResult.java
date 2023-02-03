package com.nashss.se.fittrack.activity.results;

import com.nashss.se.fittrack.models.WorkoutModel;

public class CreateWorkoutResult {
    private final WorkoutModel workout;

    private CreateWorkoutResult(WorkoutModel workout) {
        this.workout = workout;
    }

    public WorkoutModel getWorkout() {
        return this.workout;
    }

    @Override
    public String toString() {
        return "CreateWorkoutResult{" +
                "workout=" + workout +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private WorkoutModel workout;

        public Builder withWorkout(WorkoutModel workout) {
            this.workout = workout;
            return this;
        }

        public CreateWorkoutResult build() {
            return new CreateWorkoutResult(workout);
        }
    }
}

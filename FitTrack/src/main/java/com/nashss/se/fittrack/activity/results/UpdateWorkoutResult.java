package com.nashss.se.fittrack.activity.results;
import com.nashss.se.fittrack.models.WorkoutModel;

/**
 * Implementation of the UpdateWorkoutResult.
 */
public class UpdateWorkoutResult {
    private final WorkoutModel workout;

    private UpdateWorkoutResult(WorkoutModel workout) {
        this.workout = workout;
    }

    public WorkoutModel getWorkout() {
        return workout;
    }

    @Override
    public String toString() {
        return "UpdateWorkoutResult{" +
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

        public UpdateWorkoutResult build() {
            return new UpdateWorkoutResult(workout);
        }
    }
}

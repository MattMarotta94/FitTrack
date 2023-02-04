package com.nashss.se.fittrack.activity.results;
import com.nashss.se.fittrack.models.WorkoutModel;

/**
 * Implementation of the GetWorkoutResult.
 */
public class GetWorkoutResult {
    private final WorkoutModel workout;

    private GetWorkoutResult(WorkoutModel workout) {
        this.workout = workout;
    }

    public WorkoutModel getWorkout() {
        return workout; }

    @Override
    public String toString() {
        return "GetWorkoutResult{" +
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
        public GetWorkoutResult build() {
            return new GetWorkoutResult(workout);
        }
    }
}

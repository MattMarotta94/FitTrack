package com.nashss.se.fittrack.converters;

import com.nashss.se.fittrack.dynamodb.models.Workout;
import com.nashss.se.fittrack.models.WorkoutModel;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {

    /**
     * Converts a provided Workout into a WorkoutModel representation.
     * @param workout the workout to convert.
     * @return the converted workout.
     */
    public WorkoutModel toWorkoutModel(Workout workout) {
        return WorkoutModel.builder()
                .withDate(workout.getDate())
                .withName(workout.getName())
                .withNotes(workout.getNotes())
                .withExerciseList(workout.getExerciseList())
                .build();
    }
}

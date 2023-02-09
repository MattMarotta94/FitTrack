package com.nashss.se.fittrack.converters;

import com.nashss.se.fittrack.dynamodb.models.Exercise;
import com.nashss.se.fittrack.dynamodb.models.Workout;
import com.nashss.se.fittrack.models.ExerciseModel;
import com.nashss.se.fittrack.models.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

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
        List<String> notes = null;
        if (workout.getNotes() != null) {
            notes = new ArrayList<>(workout.getNotes());
        }
        return WorkoutModel.builder()
                .withDate(workout.getDate())
                .withName(workout.getName())
                .withNotes(notes)
                .build();
    }

    /**
     * Converts a provided Exercise into an ExerciseModel representation.
     * @param exercise the Exercise to convert to ExerciseModel.
     * @return the converted ExerciseModel with fields mapped from exercise.
     */
    public ExerciseModel toExerciseModel(Exercise exercise) {
        return ExerciseModel.builder()
                .withName(exercise.getExerciseName())
                .withType(exercise.getExerciseType())
                .withDescription(exercise.getDescription())
                .build();
    }
}

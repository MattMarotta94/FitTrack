package com.nashss.se.fittrack.converters;

import com.nashss.se.fittrack.dynamodb.models.Workout;
import com.nashss.se.fittrack.models.WorkoutModel;

public class ModelConverter {

    public WorkoutModel toWorkoutModel(Workout workout) {
        return WorkoutModel.builder()
                .withDate(workout.getDate())
                .withName(workout.getName())
                .withNotes(workout.getNotes())
                .withExerciseList(workout.getExerciseList())
                .build();
    }
}

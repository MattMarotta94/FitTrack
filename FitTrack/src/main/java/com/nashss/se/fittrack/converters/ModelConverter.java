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

        return WorkoutModel.builder()
                .withName(workout.getName())
                .withDate(workout.getDate())
                .withExercises(workout.getExercises())
                .withNotes(workout.getNotes())
                .build();
    }

    public List<WorkoutModel> toWorkoutModel(List<Workout> workouts) {
        List<WorkoutModel> workoutModelList = new ArrayList<>();
        for (Workout workout : workouts) {
            workoutModelList.add(toWorkoutModel(workout));
        }
        return workoutModelList;
    }

    /**
     * Converts a provided Exercise into an ExerciseModel representation.
     * @param exercise the exercise to be converted.
     * @return the converted exercise.
     */
    public ExerciseModel toExerciseModel(Exercise exercise) {
        return ExerciseModel.builder()
                .withType(exercise.getExerciseType())
                .withName(exercise.getExerciseName())
                .withDescription(exercise.getDescription())
                .build();
    }

    /**
     * Converts a provided list of Exercises into a list of ExerciseModels.
     * @param exercises the list of Exercises to convert to list of ExerciseModels.
     * @return the converted ExerciseModel list.
     */
    public List<ExerciseModel> toExerciseModel(List<Exercise> exercises) {
        List<ExerciseModel> exerciseModelList = new ArrayList<>();
        for (Exercise exercise : exercises) {
            exerciseModelList.add(toExerciseModel(exercise));
        }
        return exerciseModelList;
    }
}

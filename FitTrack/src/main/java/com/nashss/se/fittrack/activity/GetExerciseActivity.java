package com.nashss.se.fittrack.activity;

import com.nashss.se.fittrack.activity.requests.GetExerciseRequest;
import com.nashss.se.fittrack.activity.results.GetExerciseResult;
import com.nashss.se.fittrack.converters.ModelConverter;
import com.nashss.se.fittrack.dynamodb.ExerciseDao;
import com.nashss.se.fittrack.dynamodb.models.Exercise;
import com.nashss.se.fittrack.models.ExerciseModel;

import javax.inject.Inject;

public class GetExerciseActivity {
    private final ExerciseDao exerciseDao;

    @Inject
    public GetExerciseActivity(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    public GetExerciseResult handleRequest(final GetExerciseRequest getExerciseRequest) {
        String requestedType = getExerciseRequest.getType();
        String requestedName = getExerciseRequest.getName();
        Exercise exercise = exerciseDao.getExercise(requestedType, requestedName);
        ExerciseModel exerciseModel = new ModelConverter().toExerciseModel(exercise);

        return GetExerciseResult.builder()
                .withExercise(exerciseModel)
                .build();
    }

}

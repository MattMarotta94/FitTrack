package com.nashss.se.fittrack.dynamodb;
import com.nashss.se.fittrack.dynamodb.models.Exercise;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * ExerciseDao.
 */
@Singleton
public class ExerciseDao {

    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates an exerciseDao object.
     * @param dynamoDBMapper the dynamoDBMapper.
     */
    @Inject
    public ExerciseDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Loads the exercise from the exercise table.
     * @param type the type of exercise.
     * @return the specified exercises.
     */
    public List<Exercise> getExercises(String type) {
        Exercise exercise = new Exercise();
        exercise.setExerciseType(type);
        DynamoDBQueryExpression<Exercise> queryExpression = new DynamoDBQueryExpression<Exercise>()
                .withHashKeyValues(exercise);
        
        return dynamoDBMapper.query(Exercise.class, queryExpression);
    }
}

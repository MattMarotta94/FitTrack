package com.nashss.se.fittrack.dynamodb;
import com.nashss.se.fittrack.dynamodb.models.Exercise;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

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
     * @param type the associated type.
     * @param name the exercise name.
     * @return the specified exercise.
     */
    public Exercise getExercise(String type, String name) {
        return dynamoDBMapper.load(Exercise.class, type, name);
    }
}

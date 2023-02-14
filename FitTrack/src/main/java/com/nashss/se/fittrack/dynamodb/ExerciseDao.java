package com.nashss.se.fittrack.dynamodb;
import com.nashss.se.fittrack.dynamodb.models.Exercise;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ExerciseDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public ExerciseDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Exercise getExercise(String type, String name) {
        return dynamoDBMapper.load(Exercise.class, type, name);
    }
}

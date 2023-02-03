package com.nashss.se.fittrack.dynamodb;
import com.nashss.se.fittrack.dynamodb.models.Workout;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class WorkoutDao {
    private final DynamoDBMapper dynamoDBMapper;


    @Inject
    public WorkoutDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }


    public Workout getWorkout(Date date) {
        Workout workout = this.dynamoDBMapper.load(Workout.class, date);

        return workout;
    }

    public Workout saveWorkout(Workout workout) {
        this.dynamoDBMapper.save(workout);
        return workout;
    }

}

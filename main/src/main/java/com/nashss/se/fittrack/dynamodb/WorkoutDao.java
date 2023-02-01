package com.nashss.se.fittrack.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.fittrack.dynamodb.models.Workout;

import java.util.Date;

public class WorkoutDao {
    private final DynamoDBMapper dynamoDBMapper;



    public WorkoutDao(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }


    public Workout getWorkout(Date date){
        Workout workout = this.dynamoDBMapper.load(Workout.class, date);

        return workout;
    }

    public Workout saveWorkout(Workout workout){
        this.dynamoDBMapper.save(workout);
        return workout;
    }

}

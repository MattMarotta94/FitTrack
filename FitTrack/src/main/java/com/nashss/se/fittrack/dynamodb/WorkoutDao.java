package com.nashss.se.fittrack.dynamodb;
import com.nashss.se.fittrack.dynamodb.models.Workout;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Accesses data for a workout using Workout to represent the model in DynamoDB.
 */
@Singleton
public class WorkoutDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final Logger log = LogManager.getLogger();

    /**
     * Instantiates a WorkoutDao object.
     *
     * @param dynamoDBMapper the DynamoDBMapper used to interact with the workouts table.
     */
    @Inject
    public WorkoutDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;

    }

    /**
     * Returns the workout corresponding to the specified date.
     * @param date the date the workout was created.
     * @param email the users email.
     * @return the workout.
     */
    public Workout getWorkout(String email, String date) {
        Workout workout = this.dynamoDBMapper.load(Workout.class, email, date);

        return workout;
    }

    /**
     * Saves (creates or updates) the given workout.
     * @param workout The workout to save.
     * @return The Workout object that was saved.
     */
    public Workout saveWorkout(Workout workout) {
        this.dynamoDBMapper.save(workout);
        return workout;
    }

    /**
     * Deletes the given workout.
     * @param email the email.
     * @param date the date.
     */
    public void deleteWorkout(String date, String email) {
        log.info("email", email);
        log.info("date", date);
        Workout workout = new Workout();
        workout.setEmail(email);
        workout.setDate(date);
        this.dynamoDBMapper.delete(workout);
        log.info("made it past delete");
    }

    /**
     * Gets all workouts under the given email.
     * @param email the email.
     * @return all workouts with the given email as a partition key.
     */
    public List<Workout> getAllWorkouts(String email) {
        Workout workout = new Workout();
        workout.setEmail(email);
        DynamoDBQueryExpression<Workout> queryExpression = new DynamoDBQueryExpression<Workout>()
                .withHashKeyValues(workout);

        return dynamoDBMapper.query(Workout.class, queryExpression);
    }

}

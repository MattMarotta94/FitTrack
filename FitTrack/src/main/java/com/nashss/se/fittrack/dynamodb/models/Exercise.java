package com.nashss.se.fittrack.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * Represents a record in the exercise table.
 */
@DynamoDBTable(tableName = "exercises")
public class Exercise {

    private String exerciseType;
    private String exerciseName;
    private String description;


    @DynamoDBHashKey(attributeName = "exercise_type")
    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    @DynamoDBAttribute(attributeName = "exercise_name")
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Exercise exercise = (Exercise) o;
        return Objects.equals(exerciseType, exercise.exerciseType) &&
                Objects.equals(exerciseName, exercise.exerciseName) &&
                Objects.equals(description, exercise.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseType, exerciseName, description);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "exerciseType='" + exerciseType + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

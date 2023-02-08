package com.nashss.se.fittrack.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

/**
 * Represents a record in the workouts table.
 */
@DynamoDBTable(tableName = "workouts")
public class Workout {

    private String name;
    private String date;
    private List<String> notes;
    private List<Exercise> exerciseList;
    private String email;

    @DynamoDBRangeKey(attributeName = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "notes")
    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    /**
     * Returns the list of exercises associated with this Workout, null if there are none.
     * @return list of exercises for this workout.
     */
    @DynamoDBAttribute(attributeName = "exercises")
    public List<Exercise> getExerciseList() {
        if (null == exerciseList) {
            return null;
        }
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @DynamoDBHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Workout workout = (Workout) o;
        return Objects.equals(name, workout.name) && Objects.equals(date, workout.date) &&
                Objects.equals(notes, workout.notes) && Objects.equals(exerciseList, workout.exerciseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, notes, exerciseList);
    }

    @Override
    public String toString() {
        return "Workout{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                ", exerciseList=" + exerciseList +
                '}';
    }
}

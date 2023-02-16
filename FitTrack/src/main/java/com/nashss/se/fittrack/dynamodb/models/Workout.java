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
    private String exercises;
    private String notes;

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

    @DynamoDBAttribute(attributeName = "exercises")
    public String getExercises() {
        return exercises;
    }
    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    @DynamoDBAttribute(attributeName = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return Objects.equals(name, workout.name) && Objects.equals(date, workout.date) && Objects.equals(exercises, workout.exercises) && Objects.equals(notes, workout.notes) && Objects.equals(email, workout.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, exercises, notes, email);
    }

    @Override
    public String toString() {
        return "Workout{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", exercises='" + exercises + '\'' +
                ", notes='" + notes + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package com.nashss.se.fittrack.dynamodb.models;
import com.nashss.se.fittrack.models.Exercise;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "workouts")
public class Workout {

    private String name;
    private Date date;
    private String notes;
    private List<Exercise> exerciseList;

    @DynamoDBHashKey(attributeName = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @DynamoDBAttribute(attributeName = "exercises")
    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
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

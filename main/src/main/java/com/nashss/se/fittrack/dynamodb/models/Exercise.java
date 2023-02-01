package com.nashss.se.fittrack.dynamodb.models;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.xspec.S;

import java.util.Set;

@DynamoDBTable(tableName = "exercise")
public class Exercise {

    private String exerciseType;
    private String exerciseName;

    private Set<String> metrics;

    @DynamoDBHashKey(attributeName = "exercise_type")
    public String getExerciseType(){return exerciseType;}

    public void setExerciseType(String exerciseType){this.exerciseType = exerciseType;}

    @DynamoDBRangeKey(attributeName = "exercise_name")
    public String getExerciseName(){return exerciseName;}

    public void setExerciseName(String exerciseName){this.exerciseName = exerciseName;}

    @DynamoDBAttribute(attributeName = "metric")
    public Set<String> getMetrics(){return this.metrics;}

    public void setMetrics(Set<String> metrics){this.metrics = metrics;}
}

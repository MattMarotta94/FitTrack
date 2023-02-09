package com.nashss.se.fittrack.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;

import java.util.List;

public class NotesListConverter implements DynamoDBTypeConverter<String, List> {
    private static final Gson GSON = new Gson();


    @Override
    public String convert(List listToBeConverted) {
        return GSON.toJson(listToBeConverted);
    }

    @Override
    public List unconvert(String object) {
        return null;
    }
}

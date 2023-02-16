package com.nashss.se.fittrack.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Implementation of the DeleteWorkoutRequest.
 * Accepts a date parameter.
 */
@JsonDeserialize
public class DeleteWorkoutRequest {
    private final String date;
    private final String email;

    private DeleteWorkoutRequest(String date, String email) {
        this.date = date;
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "DeleteWorkoutRequest{" +
                "date='" + date + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String date;

        private String email;

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public DeleteWorkoutRequest build() {
            return new DeleteWorkoutRequest(date, email);
        }

    }
}

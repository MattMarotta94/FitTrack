package com.nashss.se.fittrack.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * GetAllWorkoutsRequest.
 * The request containing the users email.
 */
@JsonDeserialize
public class GetAllWorkoutsRequest {
    private final String email;

    /**
     * Instantiates a GetAllWorkoutsRequest object.
     * @param email the users email, parameter taken from cognito.
     */
    public GetAllWorkoutsRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "GetAllWorkoutsRequest{" +
                "email='" + email + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String email;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public GetAllWorkoutsRequest build() {
            return new GetAllWorkoutsRequest(email);
        }
    }
}

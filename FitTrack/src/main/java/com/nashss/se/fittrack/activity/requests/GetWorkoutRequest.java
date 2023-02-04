package com.nashss.se.fittrack.activity.requests;

import java.util.Date;

/**
 * Implementation of the GetWorkoutRequest for the FitTrack's GetWorkout API.
 */
public class GetWorkoutRequest {
    private final Date date;

    private GetWorkoutRequest(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "GetWorkoutRequest{" +
                "date=" + date +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Date date;

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public GetWorkoutRequest build() {
            return new GetWorkoutRequest(date);
        }
    }
}

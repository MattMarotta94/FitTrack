package com.nashss.se.fittrack.activity.requests;

/**
 * Implementation of the GetWorkoutRequest for the FitTrack's GetWorkout API.
 */
public class GetWorkoutRequest {
    private final String date;

    private GetWorkoutRequest(String date) {
        this.date = date;
    }

    public String getDate() {
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
        private String date;

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public GetWorkoutRequest build() {
            return new GetWorkoutRequest(date);
        }
    }
}

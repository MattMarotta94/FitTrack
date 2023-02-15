package com.nashss.se.fittrack.activity.requests;

/**
 * GetExerciseRequest.
 */
public class GetExerciseRequest {

    private final String type;
    private final String name;

    private GetExerciseRequest(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "GetExerciseRequest{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String type;
        private String name;

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public GetExerciseRequest build() {
            return new GetExerciseRequest(type, name);
        }
    }
}

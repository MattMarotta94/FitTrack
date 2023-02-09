package com.nashss.se.fittrack.models;

import java.util.Objects;

/**
 * Implementation of the exercise class.
 */
public class ExerciseModel {
    private String name;
    private String type;
    private String description;

    /**
     * Instantiation of an Exercise object.
     * @param name the name of the exercise.
     * @param type the type of exercise.
     * @param description a short description of the exercise.
     */
    public ExerciseModel(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExerciseModel that = (ExerciseModel) o;
        return Objects.equals(name, that.name) && Objects.equals(type, that.type) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, description);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String type;
        private String description;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ExerciseModel build() {
            return new ExerciseModel(name, type, description);
        }
    }
}

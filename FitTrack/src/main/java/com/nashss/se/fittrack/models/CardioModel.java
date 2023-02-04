package com.nashss.se.fittrack.models;

import java.util.Objects;

/**
 * Represents a CardioModel object.
 */
public class CardioModel implements Exercise {
    private String name;
    private double distance;
    private double time;

    /**
     * Instantiates a CardioModel object.
     * @param name the name of the exercise.
     * @param distance the distance covered.
     * @param time the time taken to complete the exercise.
     */
    public CardioModel(String name, double distance, double time) {
        this.name = name;
        this.distance = distance;
        this.time = time;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getDistance() {
        return this.distance;
    }

    @Override
    public double getTime() {
        return this.time;
    }

    @Override
    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardioModel that = (CardioModel) o;
        return Double.compare(that.distance, distance) == 0 && Double.compare(that.time, time) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, distance, time);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private double distance;
        private double time;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDistance(double distance) {
            this.distance = distance;
            return this;
        }

        public Builder withTime(double time) {
            this.time = time;
            return this;
        }

        public CardioModel build() {
            return new CardioModel(name, distance, time);
        }

    }
}

package com.nashss.se.fittrack.models;

import java.util.Objects;

public class WeightLiftingModel implements Exercise {

    private String name;
    private int weight;
    private int sets;
    private int reps;

    public WeightLiftingModel(String name, int weight, int sets, int reps) {
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public int getSets() {
        return this.sets;
    }

    @Override
    public int getReps() {
        return this.reps;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void setSets(int sets) {
        this.sets = sets;
    }

    @Override
    public void setReps(int reps) {
        this.reps = reps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WeightLiftingModel that = (WeightLiftingModel) o;
        return weight == that.weight && sets == that.sets && reps == that.reps && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, sets, reps);
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private int weight;
        private int sets;
        private int reps;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder withSets(int sets) {
            this.sets = sets;
            return this;
        }

        public Builder withReps(int reps) {
            this.reps = reps;
            return this;
        }

        public WeightLiftingModel build() {
            return new WeightLiftingModel(name, weight, sets, reps);
        }
    }
}

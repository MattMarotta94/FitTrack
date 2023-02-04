package com.nashss.se.fittrack.models;

import java.util.Objects;

/**
 * Represents a Calisthenics object.
 */
public class CalisthenicsModel implements Exercise {
    private String name;
    private int sets;
    private int reps;

    /**
     * Instantiates a CalisthenicsModel object.
     * @param name the name of the exercise.
     * @param sets the number of sets.
     * @param reps the number of repetitions.
     */
    public CalisthenicsModel(String name, int sets, int reps) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
    }
    @Override
    public String getName() {
        return this.name;
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
        CalisthenicsModel that = (CalisthenicsModel) o;
        return sets == that.sets && reps == that.reps && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sets, reps);
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private int sets;
        private int reps;

        public Builder withName(String name) {
            this.name = name;
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

        public CalisthenicsModel build() {
            return new CalisthenicsModel(name, sets, reps);
        }

    }
}

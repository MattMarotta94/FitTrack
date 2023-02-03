package com.nashss.se.fittrack.models;

public interface Exercise {

    //Getter Methods

    default String getName() {
        return null;
    }

    default int getWeight() {
        return 0;
    }

    default int getSets() {
        return 0;
    }

    default int getReps() {
        return 0;
    }

    default double getDistance() {
        return 0;
    }

    default double getTime() {
        return 0;
    }

    //Setter Methods

    default void setName(String name) {

    }

    default void setWeight(int weight) {

    }

    default void setSets(int sets) {

    }

    default void setReps(int reps) {

    }

    default void setDistance(double distance) {

    }

    default void setTime(double time) {

    }
}

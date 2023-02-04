package com.nashss.se.fittrack.models;

/**
 * An interface representing a generic exercise class.
 */
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

    /**
     * Sets the name of the exercise.
     * @param name the name of the exercise.
     */
    default void setName(String name) {

    }

    /**
     * Sets the weight used during an exercise.
     * @param weight the weight used.
     */
    default void setWeight(int weight) {

    }

    /**
     * Sets the number of sets completed for an exercise.
     * @param sets the number of sets completed.
     */
    default void setSets(int sets) {

    }

    /**
     * Sets the number of repetitions completed for an exercise.
     * @param reps the number of reps completed.
     */
    default void setReps(int reps) {

    }

    /**
     * Sets the distance travelled during an exercise.
     * @param distance the distance travelled.
     */
    default void setDistance(double distance) {

    }

    /**
     * Sets the time it took for an exercise to be completed.
     * @param time the time to completion.
     */
    default void setTime(double time) {

    }
}

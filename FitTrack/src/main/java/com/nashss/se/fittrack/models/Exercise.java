package com.nashss.se.fittrack.models;

/**
 * Implementation of the exercise class.
 */
public class Exercise {
    private String name;
    private String type;
    private String description;

    /**
     * Instantiation of an Exercise object.
     * @param name the name of the exercise.
     * @param type the type of exercise.
     */
    public Exercise(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.nashss.se.fittrack.models;

public class WeightLiftingModel implements Exercise {

    private String name;
    private int weight;
    private int sets;
    private int reps;

    public WeightLiftingModel(String name, int weight, int sets, int reps){
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
}

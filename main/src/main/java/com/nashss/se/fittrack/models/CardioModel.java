package com.nashss.se.fittrack.models;

import java.util.Objects;

public class CardioModel implements Exercise {
    private String name;
    private double distance;
    private double time;

    public CardioModel(String name, double distance, double time){
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardioModel that = (CardioModel) o;
        return Double.compare(that.distance, distance) == 0 && Double.compare(that.time, time) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, distance, time);
    }
}

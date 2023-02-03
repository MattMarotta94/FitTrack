package com.nashss.se.fittrack.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class WorkoutModel {
    private String name;
    private Date date;
    private String notes;
    private List<Exercise> exerciseList;

    public WorkoutModel(String name, Date date, String notes, List<Exercise> exerciseList) {
        this.name = name;
        this.date = date;
        this.notes = notes;
        this.exerciseList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkoutModel that = (WorkoutModel) o;
        return Objects.equals(name, that.name) && Objects.equals(date, that.date) &&
                Objects.equals(notes, that.notes) && Objects.equals(exerciseList, that.exerciseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, notes, exerciseList);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Date date;
        private String notes;
        private List<Exercise> exerciseList;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder withExerciseList(List<Exercise> exerciseList) {
            this.exerciseList = exerciseList;
            return this;
        }

        public WorkoutModel build() {
            return new WorkoutModel(name, date, notes, exerciseList);
        }
    }


}

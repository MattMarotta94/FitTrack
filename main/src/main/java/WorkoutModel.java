import java.util.*;


public class WorkoutModel {
    private String name;
    private Date date;
    private String notes;
    private List<Exercise> exerciseList;

    public WorkoutModel(String name, Date date, String notes, List<Exercise> exerciseList){
        this.name = name;
        this.date = date;
        this.notes = notes;
        this.exerciseList = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public Date getDate(){
        return date;
    }

    public String getNotes(){
        return notes;
    }

    public List<Exercise> getExerciseList(){
        return exerciseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutModel that = (WorkoutModel) o;
        return Objects.equals(name, that.name) && Objects.equals(date, that.date) && Objects.equals(notes, that.notes) && Objects.equals(exerciseList, that.exerciseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, notes, exerciseList);
    }


}

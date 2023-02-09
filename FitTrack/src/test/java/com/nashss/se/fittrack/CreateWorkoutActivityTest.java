package com.nashss.se.fittrack;

import com.nashss.se.fittrack.activity.CreateWorkoutActivity;
import com.nashss.se.fittrack.activity.requests.CreateWorkoutRequest;
import com.nashss.se.fittrack.activity.results.CreateWorkoutResult;
import com.nashss.se.fittrack.dynamodb.WorkoutDao;
import com.nashss.se.fittrack.dynamodb.models.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateWorkoutActivityTest {

    @Mock
    private WorkoutDao workoutDao;

    private CreateWorkoutActivity createWorkoutActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        createWorkoutActivity = new CreateWorkoutActivity(workoutDao);
    }


    @Test
    public void handleRequest_withNotes_createsAndSavesWorkoutWithNotes() {
        //GIVEN
        String expectedName = "expectedName";
        String expectedDate = "expectedDate";
        String expectedNotes = ("expectedNote");

        CreateWorkoutRequest request = CreateWorkoutRequest.builder()
                .withName(expectedName)
                .withEmail("email")
                .withDate(expectedDate)
                .withNotes(expectedNotes)
                .build();

        //WHEN
        CreateWorkoutResult result = createWorkoutActivity.handleRequest(request);

        //THEN
        verify(workoutDao).saveWorkout(any(Workout.class));

        assertEquals(expectedName, result.getWorkout().getName());
        assertEquals(expectedDate, result.getWorkout().getDate());
        assertEquals(expectedNotes, result.getWorkout().getNotes());
    }
}

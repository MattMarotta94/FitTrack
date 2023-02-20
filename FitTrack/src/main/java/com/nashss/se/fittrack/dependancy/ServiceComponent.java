package com.nashss.se.fittrack.dependancy;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.fittrack.activity.*;

import com.nashss.se.fittrack.activity.requests.GetAllWorkoutsRequest;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the FitTrack service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     *
     * @return CreateWorkoutActivity
     */
    CreateWorkoutActivity provideCreateWorkoutActivity();

    /**
     * Provides the relevant activity.
     *
     * @return GetWorkoutActivity
     */
    GetWorkoutActivity provideGetWorkoutActivity();

    /**
     * Provides the relevant activity.
     *
     * @return UpdateWorkoutActivity
     */
    UpdateWorkoutActivity provideUpdateWorkoutActivity();

    /**
     * Provides the relevant activity.
     *
     * @return GetExerciseActivity
     */
    GetExerciseActivity provideGetExerciseActivity();

    /**
     * Provides the relevant activity.
     * @return DeleteWorkoutActivity
     */
    DeleteWorkoutActivity provideDeleteWorkoutActivity();

    /**
     * Provides the relevant Activity.
     * @return GetAllWorkoutsActivity
     */
   GetAllWorkoutsActivity provideGetAllWorkoutsActivity();
}

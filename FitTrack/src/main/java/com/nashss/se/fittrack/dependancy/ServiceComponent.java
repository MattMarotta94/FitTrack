package com.nashss.se.fittrack.dependancy;

import com.nashss.se.fittrack.activity.CreateWorkoutActivity;
import com.nashss.se.fittrack.activity.GetWorkoutActivity;
import com.nashss.se.fittrack.activity.UpdateWorkoutActivity;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     *
     * @return AddSongToPlaylistActivity
     */
    //AddSongToPlaylistActivity provideAddSongToPlaylistActivity();

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
     * @return GetPlaylistActivity
     */
    //SearchPlaylistsActivity provideSearchPlaylistsActivity();

    /**
     * Provides the relevant activity.
     *
     * @return GetPlaylistSongsActivity
     */
    //GetPlaylistSongsActivity provideGetPlaylistSongsActivity();

    /**
     * Provides the relevant activity.
     *
     * @return UpdateWorkoutActivity
     */
    UpdateWorkoutActivity provideUpdateWorkoutActivity();

}

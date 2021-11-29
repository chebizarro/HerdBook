package com.herdbook.data.source;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.herdbook.data.DAO.HerdDao;
import com.herdbook.data.models.Herd;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Herd.class}, version = 1, exportSchema = false)
public abstract class HerdRoomDatabase extends RoomDatabase {

    public abstract HerdDao herdDao();

    private static volatile HerdRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static HerdRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HerdRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HerdRoomDatabase.class, "herd_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

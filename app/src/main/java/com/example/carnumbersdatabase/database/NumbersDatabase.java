package com.example.carnumbersdatabase.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.carnumbersdatabase.database.dao.NumbersDao;
import com.example.carnumbersdatabase.model.Numbers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Numbers.class, version = 1)
public abstract class NumbersDatabase extends RoomDatabase {

    private static NumbersDatabase instance;
    public abstract NumbersDao numbersDao();
    public static synchronized NumbersDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            NumbersDatabase.class, "numbers_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            ExecutorService service = Executors.newSingleThreadExecutor();
            service.execute(() -> instance.numbersDao());
        }
    };


}

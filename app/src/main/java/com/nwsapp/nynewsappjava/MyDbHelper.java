package com.nwsapp.nynewsappjava;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = OneBookMarkedNewsItem.class, exportSchema = false,version = 2)
public abstract class MyDbHelper extends RoomDatabase {

    private static final String DB_NAME="NewsDatabase";
    private static MyDbHelper instance;

    public static synchronized MyDbHelper getDatabase(Context context){
        if(instance==null){
            instance=Room.databaseBuilder(context,MyDbHelper.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract OneBookMarkedNewsItemDAO newsDao();
}

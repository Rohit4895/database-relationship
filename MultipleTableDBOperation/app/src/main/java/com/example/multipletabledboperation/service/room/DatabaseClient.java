package com.example.multipletabledboperation.service.room;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {
    private Context context;
    private CompanyDatabase database;
    private static DatabaseClient databaseClient;

    public DatabaseClient(Context context){
        this.context = context;
        database = Room.databaseBuilder(context, CompanyDatabase.class,"Company").build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if (databaseClient == null){
            databaseClient = new DatabaseClient(context);
        }
        return databaseClient;
    }

    public CompanyDatabase getDatabase(){
        return database;
    }
}

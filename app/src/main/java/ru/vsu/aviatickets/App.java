package ru.vsu.aviatickets;

import android.app.Application;
import android.arch.persistence.room.Room;

import ru.vsu.aviatickets.bookmarks.logic.AviaTicketsDatabase;
import ru.vsu.aviatickets.searchhistory.SearchHistoryRepository;

public class App extends Application {
    public static App instance;
    private AviaTicketsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this,AviaTicketsDatabase.class,"database").build();
        SearchHistoryRepository.setContext(getApplicationContext());
    }

    public static App getInstance() {
        return instance;
    }

    public AviaTicketsDatabase getDatabase(){
        return database;
    }

}


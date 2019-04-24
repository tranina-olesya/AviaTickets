package ru.vsu.aviatickets.bookmarks.logic;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;

@Database(entities = {BookmarkRoute.class}, version = 1)
public abstract class AviaTicketsDatabase extends RoomDatabase {
    public abstract BookmarkRouteDao bookmarkRouteDao();
}

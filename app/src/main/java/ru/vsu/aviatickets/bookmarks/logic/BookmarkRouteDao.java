package ru.vsu.aviatickets.bookmarks.logic;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;

@Dao
public interface BookmarkRouteDao {
    @Query("SELECT * FROM bookmarkroute")
    List<BookmarkRoute> getAll();
    @Query("SELECT * FROM bookmarkroute WHERE id = :id")
    BookmarkRoute getById(Long id);
    @Insert
    void insert(BookmarkRoute bookmarkRoute);
    @Delete
    void delete(BookmarkRoute bookmarkRoute);
}

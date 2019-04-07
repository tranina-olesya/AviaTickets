package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import data.AviaContract.BookmarksEntry;

import static data.AviaContract.BookmarksEntry.COLUMN_ADULT_COUNT;
import static data.AviaContract.BookmarksEntry.COLUMN_CHILD_COUNT;
import static data.AviaContract.BookmarksEntry.COLUMN_CLASS_TYPE;
import static data.AviaContract.BookmarksEntry.COLUMN_DESTINATION;
import static data.AviaContract.BookmarksEntry.COLUMN_INFANT_COUNT;
import static data.AviaContract.BookmarksEntry.COLUMN_ORIGIN;
import static data.AviaContract.BookmarksEntry.TABLE_NAME;
import static data.AviaContract.BookmarksEntry._ID;

public class AviaDBHelper extends SQLiteOpenHelper {

    public static final String LOQ_TAG = AviaDBHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "aviaTickets.db";
    private static final int DATABASE_VERSION = 1;

    public AviaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_BOOKMARKS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ORIGIN + " TEXT NOT NULL, "
                + COLUMN_DESTINATION + " TEXT NOT NULL, "
                + COLUMN_ADULT_COUNT + " INTEGER, "
                + COLUMN_CHILD_COUNT + " INTEGER, "
                + COLUMN_INFANT_COUNT + " INTEGER, "
                + COLUMN_CLASS_TYPE + " INTEGER);";

        // Запускаем создание таблицы
        db.execSQL(SQL_CREATE_BOOKMARKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

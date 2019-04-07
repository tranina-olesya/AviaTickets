package data;

import android.provider.BaseColumns;

public class AviaContract {
    private AviaContract() {
    }

    public static final class BookmarksEntry implements BaseColumns {
        public final static String TABLE_NAME = "bookmarks";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ORIGIN = "origin";
        public final static String COLUMN_DESTINATION = "destination";
        public final static String COLUMN_ADULT_COUNT = "adult_count";
        public final static String COLUMN_CHILD_COUNT = "child_count";
        public final static String COLUMN_INFANT_COUNT = "infant_count";
        public final static String COLUMN_CLASS_TYPE = "class_type";
    }
}
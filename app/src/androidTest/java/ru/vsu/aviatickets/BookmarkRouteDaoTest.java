package ru.vsu.aviatickets;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.bookmarks.logic.AviaTicketsDatabase;
import ru.vsu.aviatickets.bookmarks.logic.BookmarkRouteDao;
import ru.vsu.aviatickets.helpers.BookmarkRouteTestHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class BookmarkRouteDaoTest {
    BookmarkRouteDao bookmarkRouteDao;
    private AviaTicketsDatabase database;

    @Before
    public void createDB() {
        database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AviaTicketsDatabase.class)
                .build();
        bookmarkRouteDao = database.bookmarkRouteDao();
    }

    @After
    public void closeDB() {
        database.close();
    }

    @Test
    public void shouldInsertAndReadBookmark() {
        BookmarkRoute bookmarkRoute = BookmarkRouteTestHelper.getRandomBookmark();

        bookmarkRouteDao.insert(bookmarkRoute);
        List<BookmarkRoute> dbBookmarks = bookmarkRouteDao.getAll();

        assertEquals(1, dbBookmarks.size());
        assertEquals(bookmarkRoute, dbBookmarks.get(0));
    }

    @Test
    public void shouldFindBookmark() {
        List<BookmarkRoute> bookmarkRoutes = BookmarkRouteTestHelper.getRandomBookmarkList(5);
        BookmarkRoute bookmarkRouteToFind = bookmarkRoutes.get(2);

        for (BookmarkRoute bookmarkRoute : bookmarkRoutes) {
            bookmarkRouteDao.insert(bookmarkRoute);
        }

        BookmarkRoute bookmarkRouteFound = bookmarkRouteDao.findByAllParams(bookmarkRouteToFind.getOrigin(), bookmarkRouteToFind.getDestination(),
                bookmarkRouteToFind.getAdultCount(), bookmarkRouteToFind.getChildCount(), bookmarkRouteToFind.getInfantCount(),
                bookmarkRouteToFind.getFlightType(), bookmarkRouteToFind.isTransfers(), bookmarkRouteToFind.getClassType());
        assertEquals(bookmarkRouteToFind, bookmarkRouteFound);
    }

    @Test
    public void shouldFindAndDeleteBookmark() {
        BookmarkRoute bookmarkRouteToFind = BookmarkRouteTestHelper.getRandomBookmark();
        bookmarkRouteDao.insert(bookmarkRouteToFind);

        BookmarkRoute bookmarkRouteFound = bookmarkRouteDao.findByAllParams(bookmarkRouteToFind.getOrigin(), bookmarkRouteToFind.getDestination(),
                bookmarkRouteToFind.getAdultCount(), bookmarkRouteToFind.getChildCount(), bookmarkRouteToFind.getInfantCount(),
                bookmarkRouteToFind.getFlightType(), bookmarkRouteToFind.isTransfers(), bookmarkRouteToFind.getClassType());

        bookmarkRouteDao.delete(bookmarkRouteFound);
        List<BookmarkRoute> dbBookmarks = bookmarkRouteDao.getAll();
        assertTrue(dbBookmarks.isEmpty());
    }
}

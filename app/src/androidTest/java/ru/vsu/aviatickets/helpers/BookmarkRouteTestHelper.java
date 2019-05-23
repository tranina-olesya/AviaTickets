package ru.vsu.aviatickets.helpers;

import java.util.ArrayList;
import java.util.List;

import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.ticketssearch.models.CabinClass;
import ru.vsu.aviatickets.ticketssearch.models.FlightType;

public class BookmarkRouteTestHelper {

    private static final int MAX_PASSENGER_COUNT = 8;

    public static BookmarkRoute getRandomBookmark() {
        String origin = "origin" + RandomTestHelper.getRandomInt(0, 100);
        String destination = "destination" + RandomTestHelper.getRandomInt(0, 100);
        int adultsCount = RandomTestHelper.getRandomInt(1, 4);
        int childCount = RandomTestHelper.getRandomInt(0, 4);
        int infantsCount = RandomTestHelper.getRandomInt(0, Math.min(MAX_PASSENGER_COUNT - adultsCount - childCount, adultsCount));
        FlightType flightType = RandomTestHelper.getRandomInt(0, 1) == 0 ? FlightType.ROUND : FlightType.ONEWAY;
        CabinClass cabinClass = RandomTestHelper.getRandomInt(0, 1) == 0 ? CabinClass.BUSINESS : CabinClass.ECONOMY;
        boolean transfers = RandomTestHelper.getRandomInt(0, 1) != 0;

        BookmarkRoute bookmarkRoute = new BookmarkRoute(origin, destination, adultsCount, childCount, infantsCount, flightType.toString(), transfers, cabinClass.toString());
        return bookmarkRoute;
    }

    public static List<BookmarkRoute> getRandomBookmarkList(int count) {
        List<BookmarkRoute> bookmarkRoutes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            BookmarkRoute bookmarkRoute = getRandomBookmark();
            bookmarkRoutes.add(bookmarkRoute);
        }
        return bookmarkRoutes;
    }
}

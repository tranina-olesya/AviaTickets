package ru.vsu.aviatickets.ui.bookmarks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.bookmarks.entity.BookmarkRoute;
import ru.vsu.aviatickets.ticketssearch.models.CabinClass;
import ru.vsu.aviatickets.ticketssearch.models.FlightType;
import ru.vsu.aviatickets.ticketssearch.models.SearchData;

public class BookmarksAdapter extends RecyclerView.Adapter<BookmarksAdapter.BookmarksViewHolder> {

    class BookmarksViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout bookmark;
        private TextView route;
        private TextView passengerCounts;
        private ImageButton deleteButton;

        public BookmarksViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bookmark = itemView.findViewById(R.id.bookmarkRoute);
            this.route = itemView.findViewById(R.id.route);
            this.passengerCounts = itemView.findViewById(R.id.passengers);
            this.deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    private LayoutInflater inflater;
    private List<BookmarkRoute> searchDataList;
    private BookmarksRoutePresenter presenter;

    public BookmarksAdapter(Context context,List<BookmarkRoute> bookmarkRoutes) {
        this.inflater = LayoutInflater.from(context);
        this.searchDataList = bookmarkRoutes;
    }

    @NonNull
    @Override
    public BookmarksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.bookmark_route, viewGroup, false);
        BookmarksViewHolder bookmarksViewHolder = new BookmarksViewHolder(view);
        return bookmarksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarksViewHolder bookmarksViewHolder, int index) {
        BookmarkRoute searchData = searchDataList.get(index);
        bookmarksViewHolder.route.setText(String.format("%s - %s", searchData.getOrigin(), searchData.getDestination()));


        bookmarksViewHolder.passengerCounts.setText(String.format("Взрослые: %d  Дети: %d  Младенцы: %d ", searchData.getAdultCount(), searchData.getChildCount(), searchData.getInfantCount()));


        bookmarksViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDataList.remove(bookmarksViewHolder.getAdapterPosition());
                presenter.delete(bookmarksViewHolder.getAdapterPosition());
            }
        });

        bookmarksViewHolder.bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookmarkRoute bookmarkRoute = searchDataList.get(bookmarksViewHolder.getAdapterPosition());
                SearchData searchData = new SearchData(bookmarkRoute.getOrigin(),bookmarkRoute.getDestination(),null,
                                            null,bookmarkRoute.getAdultCount(),bookmarkRoute.getChildCount(),bookmarkRoute.getInfantCount(),
                                            Enum.valueOf(FlightType.class,bookmarkRoute.getFlightType()),
                                            bookmarkRoute.isTransfers(),Enum.valueOf(CabinClass.class,bookmarkRoute.getClassType()));
                presenter.itemChosen(searchData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchDataList.size();
    }

    public void setPresenter(BookmarksRoutePresenter presenter) {
        this.presenter = presenter;
    }

    public void setSearchDataList(List<BookmarkRoute> searchDataList) {
        this.searchDataList = searchDataList;
    }

}

package ru.vsu.aviatickets.ui.bookmarks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TooltipCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.tripmodels.CabinClass;
import ru.vsu.aviatickets.api.entities.tripmodels.FlightType;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchPlace;

public class BookmarksAdapter extends RecyclerView.Adapter<BookmarksAdapter.BookmarksViewHolder> {

    private LayoutInflater inflater;
    private List<BookmarkRoute> searchDataList;
    private BookmarksRoutePresenter presenter;
    private Context context;
    public BookmarksAdapter(Context context, List<BookmarkRoute> bookmarkRoutes) {
        this.context = context;
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
        BookmarkRoute bookmarkRoute = searchDataList.get(index);
        bookmarksViewHolder.route.setText(String.format("%s - %s", bookmarkRoute.getOrigin(), bookmarkRoute.getDestination()));
        bookmarksViewHolder.cabinClass.setText(bookmarkRoute.getClassType().equals(CabinClass.BUSINESS) ?
                context.getString(R.string.spinnerCabinClassBusiness) :
                context.getString(R.string.spinnerCabinClassEconomy));
        bookmarksViewHolder.adultsCount.setText(String.valueOf(bookmarkRoute.getAdultCount()));
        bookmarksViewHolder.childrenCount.setText(String.valueOf(bookmarkRoute.getChildCount()));
        bookmarksViewHolder.infantsCount.setText(String.valueOf(bookmarkRoute.getInfantCount()));
        bookmarksViewHolder.transfersImage.setImageDrawable(bookmarkRoute.isTransfers() ?
                context.getDrawable(R.drawable.has_transfers_gray) :
                context.getDrawable(R.drawable.no_transfers_gray));
        bookmarksViewHolder.flightTypeImage.setImageDrawable(bookmarkRoute.getFlightType().equals(FlightType.ROUND) ?
                context.getDrawable(R.drawable.flight_type_round) :
                context.getDrawable(R.drawable.flight_type_oneway));

        TooltipCompat.setTooltipText(bookmarksViewHolder.adultImage, context.getString(R.string.hintAdultsCount));
        TooltipCompat.setTooltipText(bookmarksViewHolder.childImage, context.getString(R.string.hintChildrenCount));
        TooltipCompat.setTooltipText(bookmarksViewHolder.infantImage, context.getString(R.string.hintInfantsCount));
        TooltipCompat.setTooltipText(bookmarksViewHolder.transfersImage, bookmarkRoute.isTransfers() ?
                context.getString(R.string.hasTransfers) :
                context.getString(R.string.noTransfers));
        TooltipCompat.setTooltipText(bookmarksViewHolder.flightTypeImage, bookmarkRoute.getFlightType().equals(FlightType.ROUND) ?
                context.getString(R.string.spinnerFlightTypeRound) :
                context.getString(R.string.spinnerFlightTypeOneway));

        bookmarksViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.delete(bookmarksViewHolder.getAdapterPosition());
            }
        });

        bookmarksViewHolder.bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookmarkRoute bookmarkRoute = searchDataList.get(bookmarksViewHolder.getAdapterPosition());
                SearchData searchData = new SearchData(new SearchPlace(bookmarkRoute.getOrigin()), new SearchPlace(bookmarkRoute.getDestination()), null,
                        null, bookmarkRoute.getAdultCount(), bookmarkRoute.getChildCount(), bookmarkRoute.getInfantCount(),
                        bookmarkRoute.getFlightType(), bookmarkRoute.isTransfers(), bookmarkRoute.getClassType());
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

    class BookmarksViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout bookmark;
        private TextView route;
        private TextView cabinClass;
        private TextView adultsCount;
        private ImageView adultImage;
        private TextView childrenCount;
        private ImageView childImage;
        private TextView infantsCount;
        private ImageView infantImage;
        private ImageView flightTypeImage;
        private ImageView transfersImage;
        private ImageButton deleteButton;

        public BookmarksViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bookmark = itemView.findViewById(R.id.bookmarkRoute);
            this.route = itemView.findViewById(R.id.route);
            this.cabinClass = itemView.findViewById(R.id.cabinClass);
            this.adultsCount = itemView.findViewById(R.id.adultsCount);
            this.adultImage = itemView.findViewById(R.id.adultImage);
            this.childrenCount = itemView.findViewById(R.id.childrenCount);
            this.childImage = itemView.findViewById(R.id.childImage);
            this.infantsCount = itemView.findViewById(R.id.infantsCount);
            this.infantImage = itemView.findViewById(R.id.infantImage);
            this.flightTypeImage = itemView.findViewById(R.id.flightTypeImage);
            this.transfersImage = itemView.findViewById(R.id.transferImage);
            this.deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}

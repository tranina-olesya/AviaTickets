package ru.vsu.aviatickets.ui.searchhistory;

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
import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ui.utils.DateConvert;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {
    class SearchHistoryViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout searchHistoryItem;
        private TextView route;
        private TextView dates;
        private ImageButton deleteButton;

        public SearchHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.searchHistoryItem = itemView.findViewById(R.id.searchHistoryItem);
            this.route = itemView.findViewById(R.id.route);
            this.dates = itemView.findViewById(R.id.dates);
            this.deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    private LayoutInflater inflater;
    private List<SearchData> searchDataList;
    private SearchHistoryPresenter presenter;

    public SearchHistoryAdapter(Context context, List<SearchData> searchDataList) {
        this.inflater = LayoutInflater.from(context);
        this.searchDataList = searchDataList;
    }

    @NonNull
    @Override
    public SearchHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.serch_history_item, viewGroup, false);
        SearchHistoryViewHolder searchHistoryViewHolder = new SearchHistoryViewHolder(view);
        return searchHistoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryViewHolder searchHistoryViewHolder, int index) {
        SearchData searchData = searchDataList.get(index);
        searchHistoryViewHolder.route.setText(String.format("%s - %s", searchData.getOrigin(), searchData.getDestination()));

        if (searchData.getInboundDate() != null)
            searchHistoryViewHolder.dates.setText(DateConvert.getDayMonthString(searchData.getOutboundDate(), searchData.getInboundDate()));
        else
            searchHistoryViewHolder.dates.setText(DateConvert.getDayMonthString(searchData.getOutboundDate()));
        searchHistoryViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDataList.remove(searchHistoryViewHolder.getAdapterPosition());
                presenter.removeItem(searchHistoryViewHolder.getAdapterPosition());
            }
        });
        searchHistoryViewHolder.searchHistoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchData searchData = searchDataList.get(searchHistoryViewHolder.getAdapterPosition());
                presenter.itemChosen(searchData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchDataList.size();
    }

    public void setPresenter(SearchHistoryPresenter presenter) {
        this.presenter = presenter;
    }

    public void setSearchDataList(List<SearchData> searchDataList) {
        this.searchDataList = searchDataList;
    }
}

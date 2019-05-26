package ru.vsu.aviatickets.ui.searchhistory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.api.entities.SearchHistoryEntry;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.ui.utils.DateConvert;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {
    private LayoutInflater inflater;
    private List<SearchHistoryEntry> searchHistoryEntries;
    private SearchHistoryPresenter presenter;
    public SearchHistoryAdapter(Context context, List<SearchHistoryEntry> searchHistoryEntries) {
        this.inflater = LayoutInflater.from(context);
        this.searchHistoryEntries = searchHistoryEntries;
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
        SearchHistoryEntry searchHistoryEntry = searchHistoryEntries.get(index);

        String dates;
        if (searchHistoryEntry.getInboundDate() != null)
            dates = DateConvert.getDayMonthString(searchHistoryEntry.getOutboundDate(), searchHistoryEntry.getInboundDate());
        else
            dates = DateConvert.getDayMonthString(searchHistoryEntry.getOutboundDate());

        searchHistoryViewHolder.text.setText(String.format("%s - %s (%s)", searchHistoryEntry.getOrigin(), searchHistoryEntry.getDestination(), dates));

        searchHistoryViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = searchHistoryViewHolder.getAdapterPosition();
                if (adapterPosition >= 0) {
                    presenter.removeItem(searchHistoryViewHolder.getAdapterPosition());
                }
            }
        });
        searchHistoryViewHolder.searchHistoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchHistoryEntry historyEntry = searchHistoryEntries.get(searchHistoryViewHolder.getAdapterPosition());
                presenter.itemChosen(historyEntry);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchHistoryEntries.size();
    }

    public void setPresenter(SearchHistoryPresenter presenter) {
        this.presenter = presenter;
    }

    public void setSearchDataList(List<SearchHistoryEntry> searchHistoryEntries) {
        this.searchHistoryEntries = searchHistoryEntries;
    }

    class SearchHistoryViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout searchHistoryItem;
        private TextView text;
        private ImageButton deleteButton;

        public SearchHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.searchHistoryItem = itemView.findViewById(R.id.searchHistoryItem);
            this.text = itemView.findViewById(R.id.text);
            this.deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}

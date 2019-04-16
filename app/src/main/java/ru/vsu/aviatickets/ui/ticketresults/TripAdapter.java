package ru.vsu.aviatickets.ui.ticketresults;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ticketssearch.models.PriceLink;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ui.fullticket.FullTripActivity;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView timeInbound;
        final TextView durationInboundComment;
        final TextView timeOutbound;
        final TextView transferInbound;
        final TextView transferOutbound;
        final TextView placesOutbound;
        final TextView placesInbound;
        final TextView durationInbound;
        final TextView durationOutbound;
        final TextView minPrice;
        final ConstraintLayout container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeInbound = (TextView) itemView.findViewById(R.id.timeInbound);
            timeOutbound = (TextView) itemView.findViewById(R.id.timeOutbound);
            durationInbound = (TextView) itemView.findViewById(R.id.durationInbound);
            durationOutbound = (TextView) itemView.findViewById(R.id.durationOutbound);
            durationInboundComment = (TextView) itemView.findViewById(R.id.durationInboundComment);
            placesInbound = (TextView) itemView.findViewById(R.id.placesInbound);
            placesOutbound = (TextView) itemView.findViewById(R.id.placesOutbound);
            transferInbound = (TextView) itemView.findViewById(R.id.transferInbound);
            transferOutbound= (TextView) itemView.findViewById(R.id.transferOutbound);
            minPrice = (TextView) itemView.findViewById(R.id.minPrice);
            container = (ConstraintLayout) itemView.findViewById(R.id.container);
        }
    }

    private LayoutInflater inflater;
    private List<Trip> trips;

    public TripAdapter(Context context, List<Trip> trips) {
        this.trips = trips;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.trip_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Trip trip = trips.get(i);
        viewHolder.placesOutbound.setText(String.format("%s - %s" ,trip.getOutbound().getOrigin().getCode(),trip.getOutbound().getDestination().getCode()));
        viewHolder.timeOutbound.setText(getTimeString(trip.getOutbound().getOutboundDate(), trip.getOutbound().getInboundDate()));
        viewHolder.durationOutbound.setText(getDurationString(trip.getOutbound().getDuration()));
        boolean transfersOutbound = trip.getOutbound().getFlightParts().size() > 1;
        viewHolder.transferOutbound.setText(transfersOutbound ? R.string.hasTransfers : R.string.noTransfers);

        PriceLink priceLink = trip.getPriceLinks().stream().min(Comparator.comparing(PriceLink::getPrice)).get();
        viewHolder.minPrice.setText(priceLink.getPrice().toString());

        if (trip.getInbound() != null) {
            viewHolder.durationInboundComment.setVisibility(View.VISIBLE);
            viewHolder.placesInbound.setText(String.format("%s - %s" ,trip.getInbound().getOrigin().getCode(),trip.getInbound().getDestination().getCode()));
            viewHolder.timeInbound.setText(getTimeString(trip.getInbound().getOutboundDate(), trip.getInbound().getInboundDate()));
            viewHolder.durationOutbound.setText(getDurationString(trip.getInbound().getDuration()));
            boolean transfersInbound = trip.getInbound().getFlightParts().size() > 1;
            viewHolder.transferInbound.setText(transfersInbound ? R.string.hasTransfers : R.string.noTransfers);
        }

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.container.getContext(), FullTripActivity.class);
                viewHolder.container.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    private String getTimeString(Date first, Date second) {
        String pattern = "HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return String.format("%s - %s",  simpleDateFormat.format(first), simpleDateFormat.format(second));
    }

    private String getDurationString(Integer duration) {
        int hours = duration / 60;
        int minutes = duration % 60;
        String hoursStr = (hours < 10 ? "0" : "") + String.valueOf(hours);
        String minutesStr = (minutes < 10 ? "0" : "") + String.valueOf(minutes);
        return String.format("%s:%s", hoursStr, minutesStr);
    }
}

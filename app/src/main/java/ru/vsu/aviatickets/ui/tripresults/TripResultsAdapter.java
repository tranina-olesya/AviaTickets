package ru.vsu.aviatickets.ui.tripresults;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TooltipCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Comparator;
import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ticketssearch.models.PriceLink;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ui.fulltrip.FullTripActivity;
import ru.vsu.aviatickets.ui.utils.DateConvert;

import static ru.vsu.aviatickets.ui.fulltrip.FullTripActivity.TRIP_EXTRA;

public class TripResultsAdapter extends RecyclerView.Adapter<TripResultsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView timeInbound;
        final TextView durationInboundComment;
        final TextView timeOutbound;
        final ImageView transferInbound;
        final ImageView transferOutbound;
        final TextView placesOutbound;
        final TextView placesInbound;
        final TextView durationInbound;
        final TextView durationOutbound;
        final TextView minPrice;
        final ConstraintLayout container;

        final Group groupInbound;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeInbound = itemView.findViewById(R.id.timeInbound);
            timeOutbound = itemView.findViewById(R.id.timeOutbound);
            durationInbound = itemView.findViewById(R.id.durationInbound);
            durationOutbound = itemView.findViewById(R.id.durationOutbound);
            durationInboundComment = itemView.findViewById(R.id.durationInboundComment);
            placesInbound = itemView.findViewById(R.id.placesInbound);
            placesOutbound = itemView.findViewById(R.id.placesOutbound);
            transferInbound = itemView.findViewById(R.id.transferInbound);
            transferOutbound = itemView.findViewById(R.id.transferOutbound);
            minPrice = itemView.findViewById(R.id.minPrice);
            container = itemView.findViewById(R.id.container);
            groupInbound = itemView.findViewById(R.id.groupInbound);
        }
    }

    private LayoutInflater inflater;
    private List<Trip> trips;

    public TripResultsAdapter(Context context, List<Trip> trips) {
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
        viewHolder.placesOutbound.setText(String.format("%s - %s", trip.getOutbound().getOrigin().getCode(), trip.getOutbound().getDestination().getCode()));
        viewHolder.timeOutbound.setText(DateConvert.getTimeString(trip.getOutbound().getOutboundDate(), trip.getOutbound().getInboundDate()));
        viewHolder.durationOutbound.setText(DateConvert.getDurationString(trip.getOutbound().getDuration()));
        boolean transfersOutbound = trip.getOutbound().getFlightParts().size() > 1;
        final Context context = viewHolder.container.getContext();
        viewHolder.transferOutbound.setImageDrawable(transfersOutbound ?
                context.getDrawable(R.drawable.has_transfers):
                context.getDrawable(R.drawable.no_transfers));
        TooltipCompat.setTooltipText(viewHolder.transferOutbound, transfersOutbound ?
                context.getString(R.string.hasTransfers) :
                context.getString(R.string.noTransfers));

        PriceLink priceLink = trip.getPriceLinks().stream().min(Comparator.comparing(PriceLink::getPrice)).get();
        viewHolder.minPrice.setText(priceLink.getPrice().toString());

        if (trip.getInbound() != null) {
            viewHolder.groupInbound.setVisibility(View.VISIBLE);
            viewHolder.placesInbound.setText(String.format("%s - %s", trip.getInbound().getOrigin().getCode(), trip.getInbound().getDestination().getCode()));
            viewHolder.timeInbound.setText(DateConvert.getTimeString(trip.getInbound().getOutboundDate(), trip.getInbound().getInboundDate()));
            viewHolder.durationInbound.setText(DateConvert.getDurationString(trip.getInbound().getDuration()));
            boolean transfersInbound = trip.getInbound().getFlightParts().size() > 1;
            viewHolder.transferInbound.setImageDrawable(transfersInbound ?
                    context.getDrawable(R.drawable.has_transfers):
                    context.getDrawable(R.drawable.no_transfers));
            TooltipCompat.setTooltipText(viewHolder.transferInbound, transfersInbound ?
                    context.getString(R.string.hasTransfers) :
                    context.getString(R.string.noTransfers));
        }

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = viewHolder.container.getContext();
                Intent intent = new Intent(context, FullTripActivity.class);
                intent.putExtra(TRIP_EXTRA, trip);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

}

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

import java.util.Comparator;
import java.util.List;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ticketssearch.models.PriceLink;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ui.fullticketinfo.FullTripActivity;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView arrivalTimeTo;
        final TextView departureTimeTo;
        final TextView arrivalTimeFrom;
        final TextView departureTimeFrom;
        final TextView minPrice;
        final ConstraintLayout container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            arrivalTimeTo = (TextView) itemView.findViewById(R.id.arrivalTimeTo);
            departureTimeTo = (TextView) itemView.findViewById(R.id.departureTimeTo);
            arrivalTimeFrom = (TextView) itemView.findViewById(R.id.arrivalTimeFrom);
            departureTimeFrom = (TextView) itemView.findViewById(R.id.departureTimeFrom);
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
        viewHolder.arrivalTimeTo.setText(trip.getInbound().getInboundDate().toString());
        viewHolder.departureTimeTo.setText(trip.getInbound().getOutboundDate().toString());
        viewHolder.arrivalTimeFrom.setText(trip.getOutbound().getInboundDate().toString());
        viewHolder.departureTimeFrom.setText(trip.getOutbound().getOutboundDate().toString());
        PriceLink priceLink = trip.getPriceLinks().stream().min(Comparator.comparing(PriceLink::getPrice)).get();
        viewHolder.minPrice.setText(priceLink.getPrice().toString());

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
}

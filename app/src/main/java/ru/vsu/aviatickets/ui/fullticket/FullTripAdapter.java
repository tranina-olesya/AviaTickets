package ru.vsu.aviatickets.ui.fullticket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.vsu.aviatickets.R;
import ru.vsu.aviatickets.ticketssearch.models.PriceLink;
import ru.vsu.aviatickets.ticketssearch.models.Ticket;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ui.utils.DateConvert;

public class FullTripAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        private final TextView route;
        private final TextView fullRoute;
        private final TextView date;
        private final TextView duration;
        private final TextView carrier;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            route = (TextView) itemView.findViewById(R.id.route);
            fullRoute = (TextView) itemView.findViewById(R.id.fullRoute);
            carrier = (TextView) itemView.findViewById(R.id.carrier);
            date = (TextView) itemView.findViewById(R.id.date);
            duration = (TextView) itemView.findViewById(R.id.duration);
        }

        public void addTicketInfo(Ticket ticket){
            carrier.setText(String.format("%s", ticket.getCarrier().getName()));
            fullRoute.setText(String.format("%s - %s", ticket.getOrigin().getName(), ticket.getDestination().getName()));
            route.setText(String.format("%s - %s", ticket.getOrigin().getCode(), ticket.getDestination().getCode()));
            date.setText(DateConvert.getTimeString(ticket.getOutboundDate(), ticket.getInboundDate()));
            duration.setText(DateConvert.getDurationString(ticket.getDuration()));
        }
    }

    public class PriceLinkViewHolder extends RecyclerView.ViewHolder {
        private final TextView priceLink;

        public PriceLinkViewHolder(@NonNull View itemView) {
            super(itemView);
            priceLink = (TextView) itemView.findViewById(R.id.linkText);
        }
    }

    private Trip trip;
    private LayoutInflater inflater;
    private int ticketsToCount;
    private int ticketsFromCount;
    private int titlesCount;
    private int linksCount;

    public FullTripAdapter(Context context, Trip trip) {
        this.trip = trip;
        this.inflater = LayoutInflater.from(context);

        ticketsToCount = trip.getOutbound().getFlightParts().size();
        ticketsFromCount = trip.getInbound() != null ? trip.getInbound().getFlightParts().size() : 0;
        titlesCount = trip.getInbound() != null ? 3 : 2;
        linksCount = trip.getPriceLinks().size();
    }

    @Override
    public int getItemViewType(int position) {
        int inbound = ticketsFromCount > 0 ? 1 : 0;
        if (position == 0) {
            return ViewHolderTypes.ROUTE_TO.index();
        } else if (position > 0 && position <= ticketsToCount) {
            return ViewHolderTypes.TICKETS_TO.index();
        } else if (position == ticketsToCount + inbound) {
            return ViewHolderTypes.ROUTE_FROM.index();
        } else if (position > ticketsToCount + inbound && position <= ticketsToCount + ticketsFromCount + inbound) {
            return ViewHolderTypes.TICKETS_FROM.index();
        } else if (position == ticketsToCount + ticketsFromCount + titlesCount - 1) {
            return ViewHolderTypes.PRICE_TITLE.index();
        } else {
            return ViewHolderTypes.PRICE_LINK.index();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (viewType == ViewHolderTypes.ROUTE_TO.index() || viewType == ViewHolderTypes.ROUTE_FROM.index() || viewType == ViewHolderTypes.PRICE_TITLE.index()) {
            View view = inflater.inflate(R.layout.title_info, viewGroup, false);
            return new TitleViewHolder(view);
        } else if (viewType == ViewHolderTypes.TICKETS_TO.index() || viewType == ViewHolderTypes.TICKETS_FROM.index()) {
            View view = inflater.inflate(R.layout.ticket_info, viewGroup, false);
            return new TicketViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.price_link, viewGroup, false);
            return new PriceLinkViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int type = viewHolder.getItemViewType();
        if (type == ViewHolderTypes.ROUTE_TO.index()) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) viewHolder;
            titleViewHolder.title.setText(String.format("%s - %s (%s - %s)",
                    trip.getOutbound().getOrigin().getName(), trip.getOutbound().getDestination().getName(),
                    trip.getOutbound().getOrigin().getCode(), trip.getOutbound().getDestination().getCode()));
        } else if (type == ViewHolderTypes.ROUTE_FROM.index()) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) viewHolder;
            titleViewHolder.title.setText(String.format("%s - %s (%s - %s)",
                    trip.getInbound().getOrigin().getName(), trip.getInbound().getDestination().getName(),
                    trip.getInbound().getOrigin().getCode(), trip.getInbound().getDestination().getCode()));
        } else if (type == ViewHolderTypes.PRICE_TITLE.index()) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) viewHolder;
            titleViewHolder.title.setText(R.string.priceLinks);
        } else if (type == ViewHolderTypes.TICKETS_TO.index()) {
            Ticket ticket = trip.getOutbound().getFlightParts().get(position - 1);
            TicketViewHolder ticketViewHolder = (TicketViewHolder) viewHolder;
            ticketViewHolder.addTicketInfo(ticket);
        } else if (type == ViewHolderTypes.TICKETS_FROM.index() && ticketsFromCount > 0) {
            Ticket ticket = trip.getOutbound().getFlightParts().get(position - 2 - ticketsToCount);
            TicketViewHolder ticketViewHolder = (TicketViewHolder) viewHolder;
            ticketViewHolder.addTicketInfo(ticket);
        } else if (type == ViewHolderTypes.PRICE_LINK.index()) {
            PriceLink priceLink = trip.getPriceLinks().get(position - ticketsToCount - ticketsFromCount - titlesCount);
            PriceLinkViewHolder priceLinkViewHolder = (PriceLinkViewHolder) viewHolder;
            priceLinkViewHolder.priceLink.setText(priceLink.getDeepLink());
        }
    }

    @Override
    public int getItemCount() {
        return ticketsToCount + ticketsFromCount + titlesCount + linksCount;
    }
}
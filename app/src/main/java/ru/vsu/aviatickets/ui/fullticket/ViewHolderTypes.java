package ru.vsu.aviatickets.ui.fullticket;

public enum ViewHolderTypes {
    ROUTE_TO(0),
    ROUTE_FROM(1),
    PRICE_TITLE(2),
    TICKETS_TO(3),
    TICKETS_FROM(4),
    PRICE_LINK(5);

    private int index;

    ViewHolderTypes(int index){
        this.index = index;
    }

    public int index(){
        return index;
    }
}

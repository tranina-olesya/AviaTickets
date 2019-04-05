package ru.vsu.aviatickets.ticketssearch.models;

import java.util.Date;

public class Ticket {
    private String carrier;
    private String origin;
    private String destination;
    private Date outboundDate;
    private Date inboundDate;
    private Integer flightLength;
    private Integer price;
}

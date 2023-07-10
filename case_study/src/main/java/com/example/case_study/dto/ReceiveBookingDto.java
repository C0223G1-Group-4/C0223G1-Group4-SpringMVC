package com.example.case_study.dto;

public class ReceiveBookingDto {
    private int id;
    private String passengerName;
    private String total;
    private String departure;
    private String arrival;
    private String numberAirCraft;
    private String airport;
    private String destination;
    private int quantity;
    private boolean status;

    private boolean status_receive;


    public ReceiveBookingDto(int id, String passengerName, String total, String departure, String arrival, String numberAirCraft, String airport, String destination, int quantity, boolean status, boolean status_receive) {
        this.id = id;
        this.passengerName = passengerName;
        this.total = total;
        this.departure = departure;
        this.arrival = arrival;
        this.numberAirCraft = numberAirCraft;
        this.airport = airport;
        this.destination = destination;
        this.quantity = quantity;
        this.status = status;
        this.status_receive = status_receive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getNumberAirCraft() {
        return numberAirCraft;
    }

    public void setNumberAirCraft(String numberAirCraft) {
        this.numberAirCraft = numberAirCraft;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus_receive() {
        return status_receive;
    }

    public void setStatus_receive(boolean status_receive) {
        this.status_receive = status_receive;
    }
}

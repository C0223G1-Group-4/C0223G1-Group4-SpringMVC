package com.example.case_study.dto;


public class ReceiveBookingDto {

    private Integer id;
    private String passengerName;
    private Integer total;
    private String departure;
    private String arrival;
    private String numberAirCraft;
    private String airport;
    private String destination;
    private Integer quantity;
    private Boolean status;

    private String phone;
    private String email;
    private String bookingDate;
    private boolean status_receive;


    public ReceiveBookingDto(Integer id, String passengerName, Integer total, String departure, String arrival, String numberAirCraft, String airport, String destination, Integer quantity, Boolean status, String phone, String email, String bookingDate) {
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
        this.phone = phone;
        this.email = email;
        this.bookingDate = bookingDate;
    }

    public ReceiveBookingDto(Integer id, String passengerName, Integer total, String departure, String arrival, String numberAirCraft, String airport, String destination, Integer quantity, Boolean status) {
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
    }

    public ReceiveBookingDto(Integer id, String passengerName, Integer total, String departure, String arrival, String numberAirCraft, String airport, String destination, Integer quantity, Boolean status, Boolean status_receive) {
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

    public ReceiveBookingDto() {

    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean isStatus() {
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

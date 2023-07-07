package com.example.case_study.model;

import javax.persistence.*;

@Entity
public class BookingTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBookingTicket;

    @Column(columnDefinition = "date")
    private String bookingDate;

    private int quantity;
//trạng thái
    private boolean type;

    @ManyToOne
    private Passengers passenger;

    @ManyToOne
    private Employees employee;

    public BookingTicket() {
    }

    public BookingTicket(int idBookingTicket, String bookingDate, int quantity, boolean type,
                         Passengers passenger, Employees employee) {
        this.idBookingTicket = idBookingTicket;
        this.bookingDate = bookingDate;
        this.quantity = quantity;
        this.type = type;
        this.passenger = passenger;
        this.employee = employee;
    }

    public int getIdBookingTicket() {
        return idBookingTicket;
    }

    public void setIdBookingTicket(int idBookingTicket) {
        this.idBookingTicket = idBookingTicket;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public Passengers getPassenger() {
        return passenger;
    }

    public void setPassenger(Passengers passenger) {
        this.passenger = passenger;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
}

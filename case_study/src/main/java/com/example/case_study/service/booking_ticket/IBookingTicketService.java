package com.example.case_study.service.booking_ticket;

import com.example.case_study.model.BookingTicket;
import org.springframework.stereotype.Service;

public interface IBookingTicketService {
    void createAuto(BookingTicket bookingTicket);
    void save(BookingTicket bookingTicket);
}

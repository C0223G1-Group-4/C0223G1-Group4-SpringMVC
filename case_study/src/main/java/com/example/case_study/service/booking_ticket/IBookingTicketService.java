package com.example.case_study.service.booking_ticket;

import com.example.case_study.model.BookingTicket;
import com.example.case_study.model.Passengers;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface IBookingTicketService {
    void createAuto(BookingTicket bookingTicket);
    void save(BookingTicket bookingTicket);
    BookingTicket findByPassenger_Id(int id);

    BookingTicket findById(int id);

    void sendEmail(Passengers passengers, String siteURL) throws MessagingException, UnsupportedEncodingException;
}

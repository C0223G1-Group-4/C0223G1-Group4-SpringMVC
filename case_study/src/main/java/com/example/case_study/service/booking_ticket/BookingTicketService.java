package com.example.case_study.service.booking_ticket;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.BookingTicket;
import com.example.case_study.model.Passengers;
import com.example.case_study.repository.IAccountRepository;
import com.example.case_study.repository.IBookingTicketRepository;
import com.example.case_study.repository.IPassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingTicketService implements IBookingTicketService{
    @Autowired
    private IBookingTicketRepository iBookingTicketRepository;

    @Override
    public void createAuto(BookingTicket bookingTicket) {
        iBookingTicketRepository.save(bookingTicket);
    }

    @Override
    public void save(BookingTicket bookingTicket) {
        iBookingTicketRepository.save(bookingTicket);
    }

    @Override
    public BookingTicket findByPassenger_Id(int id) {
        return iBookingTicketRepository.findById(id).orElse(null);
    }

    @Override
    public BookingTicket findById(int id) {
        return iBookingTicketRepository.findById(id).orElse(null);
    }
}

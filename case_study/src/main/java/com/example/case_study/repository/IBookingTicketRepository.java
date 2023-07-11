package com.example.case_study.repository;

import com.example.case_study.model.BookingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookingTicketRepository extends JpaRepository<BookingTicket,Integer> {
    BookingTicket findByPassenger_Id(int id);
    BookingTicket findByIdBookingTicket(int id);
}

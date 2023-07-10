package com.example.case_study.service.receive_booking_service;

import com.example.case_study.dto.ReceiveBookingDto;
import com.example.case_study.model.ChairFlight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReceiveBookingService {
    Page<ReceiveBookingDto> getReceiveBookingTicketList(Pageable pageable);
    void cancelBookingStatus();

    ReceiveBookingDto findById(int id);
    void cancelBooking(int id);

    void confirm(int id);

    List<ChairFlight> seats(int id);

}

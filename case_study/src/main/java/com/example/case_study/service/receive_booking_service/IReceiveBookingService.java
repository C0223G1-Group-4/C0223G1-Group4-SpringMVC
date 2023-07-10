package com.example.case_study.service.receive_booking_service;

import com.example.case_study.dto.ReceiveBookingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReceiveBookingService {
    Page<ReceiveBookingDto> getReceiveBookingTicketList(Pageable pageable);
    void cancelBookingStatus();

    ReceiveBookingDto findById(int id);
    void cancelBooking(int id);

    void confirm(int id);

}

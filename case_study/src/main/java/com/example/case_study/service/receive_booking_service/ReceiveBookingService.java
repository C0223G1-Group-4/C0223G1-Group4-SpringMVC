package com.example.case_study.service.receive_booking_service;

import com.example.case_study.dto.ReceiveBookingDto;
import com.example.case_study.model.ChairFlight;
import com.example.case_study.repository.IChairFlightRepository;
import com.example.case_study.repository.IReceiveBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ReceiveBookingService implements IReceiveBookingService {
    @Autowired
    private IReceiveBookingRepo receiveBookingRepo;

    @Autowired
    private IChairFlightRepository chairFlightRepository;
    @Override
    public Page<ReceiveBookingDto> getReceiveBookingTicketList(Pageable pageable) {
//        cancelBookingStatus();
        return receiveBookingRepo.getReceiveBookingTicketList(pageable);
    }

    @Override
    public void cancelBookingStatus() {
        List<ReceiveBookingDto> receiveBookingDtos = receiveBookingRepo.getReceiveBookingTickets();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedDate ;
        LocalDateTime cancel = LocalDateTime.now().minusDays(1);
        for (ReceiveBookingDto rc : receiveBookingDtos) {
            parsedDate = LocalDateTime.parse(rc.getDeparture().substring(0, 10).concat(" " + rc.getDeparture().substring(11, 16)), formatter);
            if (parsedDate.isBefore(cancel)) {
                rc.setStatus_receive(true);
            } else {
                rc.setStatus_receive(false);
            }
        }
    }

    @Override
    public ReceiveBookingDto findById(int id) {
        for (ReceiveBookingDto rc : receiveBookingRepo.getReceiveBookingTickets()) {
            if (rc.getId() == id) {
                return rc;
            }
        }
        return null;
    }

    @Override
    public void cancelBooking(int id) {
        receiveBookingRepo.findByIdBookingTicket(id).setStatus(true);
    }

    @Override
    public void confirm(int id) {
        receiveBookingRepo.findByIdBookingTicket(id).setStatus(true);
    }

    @Override
    public List<ChairFlight> seats(int id) {
        return chairFlightRepository.findByBookingTicket_IdBookingTicket(id);
    }


}

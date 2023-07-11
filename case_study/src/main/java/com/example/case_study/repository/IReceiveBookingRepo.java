package com.example.case_study.repository;

import com.example.case_study.dto.ReceiveBookingDto;
import com.example.case_study.model.BookingTicket;
import com.example.case_study.model.ChairFlight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReceiveBookingRepo extends JpaRepository<BookingTicket,Integer> {
    @Query("select new com.example.case_study.dto.ReceiveBookingDto(BK.idBookingTicket,P.name ,BK.total,FS.departure,FS.arrival,AC.numberAirCraft,R.airPort,R.destination,BK.quantity,BK.type,P.phoneNumber,P.accountUser.email,BK.bookingDate)\n" +
            "from ChairFlight CF \n" +
            "LEFT JOIN CF.bookingTicket BK \n" +
            "LEFT JOIN CF.chair Ch\n" +
            "LEFT JOIN CF.flightScheduleAirCraft FLC \n" +
            "LEFT JOIN FLC.flightSchedule FS\n" +
            "LEFT JOIN FLC.idAirCraft AC \n" +
            "LEFT JOIN AC.routes R\n" +
            "LEFT JOIN BK.passenger P where BK.status=false")
    Page<ReceiveBookingDto> getReceiveBookingTicketList(Pageable pageable);
    @Query("select new com.example.case_study.dto.ReceiveBookingDto(BK.idBookingTicket,P.name ,BK.total,FS.departure,FS.arrival,AC.numberAirCraft,R.airPort,R.destination,BK.quantity,BK.type,P.phoneNumber,P.accountUser.email,BK.bookingDate)\n" +
            "from ChairFlight CF \n" +
            "LEFT JOIN CF.bookingTicket BK \n" +
            "LEFT JOIN CF.chair Ch\n" +
            "LEFT JOIN CF.flightScheduleAirCraft FLC \n" +
            "LEFT JOIN FLC.flightSchedule FS\n" +
            "LEFT JOIN FLC.idAirCraft AC \n" +
            "LEFT JOIN AC.routes R\n" +
            "LEFT JOIN BK.passenger P where BK.status=false and P.accountUser.email like concat('%',:email,'%')")
    Page<ReceiveBookingDto> getHistory(String email,Pageable pageable);
    @Query("select new com.example.case_study.dto.ReceiveBookingDto(BK.idBookingTicket,P.name ,BK.total,FS.departure,FS.arrival,AC.numberAirCraft,R.airPort,R.destination,BK.quantity,BK.type,P.phoneNumber,P.accountUser.email,BK.bookingDate)\n" +
            "from ChairFlight CF \n" +
            "LEFT JOIN CF.bookingTicket BK \n" +
            "LEFT JOIN CF.chair Ch\n" +
            "LEFT JOIN CF.flightScheduleAirCraft FLC \n" +
            "LEFT JOIN FLC.flightSchedule FS\n" +
            "LEFT JOIN FLC.idAirCraft AC \n" +
            "LEFT JOIN AC.routes R\n" +
            "LEFT JOIN BK.passenger P where BK.status=false")
    List<ReceiveBookingDto> getReceiveBookingTickets();

//    BookingTicket findByIdBookingTicket(int id);


}

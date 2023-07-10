package com.example.case_study.repository;

import com.example.case_study.dto.ReceiveBookingDto;
import com.example.case_study.model.BookingTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReceiveBookingRepo extends JpaRepository<BookingTicket,Integer> {
    @Query(value = "select BK.id_booking_ticket,P.name_passengers ,C.name_chair,fs.departure,fs.arrival,AC.number_air_craft,R.air_port,R.destination,BK.quantity,BK.type\n" +
            "from booking_ticket as BK\n" +
            "left  OUTER join chair_flight CF on (BK.id_booking_ticket=CF.id_bk)\n" +
            "left OUTER JOIN chair C on (CF.id_chair=C.id)\n" +
            "left OUTER JOIN flight_schedule_aircraft FLC on (FLC.id=CF.id_flight_schedule_aircraft)\n" +
            "left outer join flight_schedule FS on FLC.flight_schedule_id = FS.id\n" +
            "left outer join air_craft AC on FLC.air_craft_id = AC.id\n" +
            "left outer join route_air_craft RAC on AC.id = RAC.air_craft_id\n" +
            "left outer join route R on RAC.route_id = R.id\n" +
            "left outer join passengers P on BK.passenger_id = P.id where BK.type=true",nativeQuery = true)
    Page<ReceiveBookingDto> getReceiveBookingTicketList(Pageable pageable);
    @Query(value = "select BK.id_booking_ticket,P.name_passengers ,C.name_chair,fs.departure,fs.arrival,AC.number_air_craft,R.air_port,R.destination,BK.quantity,BK.type\n" +
            "from booking_ticket as BK\n" +
            "left  OUTER join chair_flight CF on (BK.id_booking_ticket=CF.id_bk)\n" +
            "left OUTER JOIN chair C on (CF.id_chair=C.id)\n" +
            "left OUTER JOIN flight_schedule_aircraft FLC on (FLC.id=CF.id_flight_schedule_aircraft)\n" +
            "left outer join flight_schedule FS on FLC.flight_schedule_id = FS.id\n" +
            "left outer join air_craft AC on FLC.air_craft_id = AC.id\n" +
            "left outer join route_air_craft RAC on AC.id = RAC.air_craft_id\n" +
            "left outer join route R on RAC.route_id = R.id\n" +
            "left outer join passengers P on BK.passenger_id = P.id where BK.type=true",nativeQuery = true)
    List<ReceiveBookingDto> getReceiveBookingTickets();


}

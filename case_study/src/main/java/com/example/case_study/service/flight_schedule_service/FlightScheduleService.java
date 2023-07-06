package com.example.case_study.service.flight_schedule_service;

import com.example.case_study.model.tai.FlightSchedule;
import com.example.case_study.repository.IFlightScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightScheduleService implements IFlightScheduleService{
    @Autowired
    private IFlightScheduleRepository iFlightScheduleRepository;
    @Override
    public Page<FlightSchedule> getAllListFlightSchedule(Pageable pageable) {
        return iFlightScheduleRepository.findFlightScheduleByFlagFalse(pageable);
    }

    @Override
    public List<FlightSchedule> checkAllListFlightSchedule() {
        return iFlightScheduleRepository.getAirCraftByFlagIsFalse();
    }

    @Override
    public boolean createFlightSchedule(FlightSchedule flightSchedule) {
            iFlightScheduleRepository.save(flightSchedule);
            return true;
    }

    @Override
    public boolean editFlightSchedule(FlightSchedule flightSchedule) {
        if (this.findByIdFlightSchedule(flightSchedule.getId())!=null){
            iFlightScheduleRepository.save(flightSchedule);
            return true;
        }
        return false;
    }

    @Override
    public void deleteFlightSchedule(FlightSchedule flightSchedule) {
            iFlightScheduleRepository.save(flightSchedule);
    }

    @Override
    public FlightSchedule findByIdFlightSchedule(int id) {
        return iFlightScheduleRepository.findById(id).orElse(null);
    }
}

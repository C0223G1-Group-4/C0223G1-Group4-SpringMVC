package com.example.case_study.service.flight_scgedule_air_craft_service;

import com.example.case_study.model.tai.FlightScheduleAirCraft;
import com.example.case_study.repository.IFlightScheduleAirCraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightScheduleAirCraftService implements IFlightScheduleAirCraftService{
    @Autowired
    private IFlightScheduleAirCraftRepository iFlightScheduleAirCraftRepository;

    @Override
    public Page<FlightScheduleAirCraft> getAllList(Pageable pageable) {
        return iFlightScheduleAirCraftRepository.getFlightScheduleAirCraftsByFlagIsFalse(pageable);
    }

    @Override
    public List<FlightScheduleAirCraft> checkAllListFlightScheduleAirCraft() {
        return iFlightScheduleAirCraftRepository.findAll();
    }

    @Override
    public void createFlightScheduleAirCraft(FlightScheduleAirCraft flightScheduleAirCraft) {
             iFlightScheduleAirCraftRepository.save(flightScheduleAirCraft);
    }

    @Override
    public void editFlightScheduleAirCraft(FlightScheduleAirCraft flightScheduleAirCraft) {
        iFlightScheduleAirCraftRepository.save(flightScheduleAirCraft);
    }

    @Override
    public void deleteFlightScheduleAirCraft(FlightScheduleAirCraft flightScheduleAirCraft) {
        iFlightScheduleAirCraftRepository.save(flightScheduleAirCraft);
    }

    @Override
    public FlightScheduleAirCraft findByIdFlightScheduleAirCraft(int id) {
        return iFlightScheduleAirCraftRepository.findById(id).orElse(null);
    }

    @Override
    public List<FlightScheduleAirCraft> searchTicket(String airPort,String departure, String arrival, String destination) {
        if (departure.equals("") && arrival.equals("")) {
            return this.iFlightScheduleAirCraftRepository.searchTicket(airPort, departure, arrival, destination);
        } else if (!arrival.equals("") && departure.equals("")) {
            return this.iFlightScheduleAirCraftRepository.searchTicket1(airPort, departure, arrival, destination);
        } else if (arrival.equals("")&&!departure.equals("")){
            return this.iFlightScheduleAirCraftRepository.searchTicket2(airPort, departure, arrival, destination);
        }else {
            return this.iFlightScheduleAirCraftRepository.searchTicket3(airPort, departure, arrival, destination);
        }
    }
}

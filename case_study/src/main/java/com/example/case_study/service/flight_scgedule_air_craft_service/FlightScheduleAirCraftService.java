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
}

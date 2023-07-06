package com.example.case_study.service.chairflight_service;

import com.example.case_study.model.ChairFlight;
import com.example.case_study.repository.IChairFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChairFlightService implements IChairFlightService{
    @Autowired
    private IChairFlightRepository chairFlightRepository;

    @Override
    public List<ChairFlight> getList(int num) {
        return chairFlightRepository.getList(num);
    }
}

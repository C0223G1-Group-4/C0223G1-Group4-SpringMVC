package com.example.case_study.service.chairflight_service;

import com.example.case_study.model.ChairFlight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IChairFlightService {
    List<ChairFlight> getList( int num);
    void update(ChairFlight chairFlight);

}

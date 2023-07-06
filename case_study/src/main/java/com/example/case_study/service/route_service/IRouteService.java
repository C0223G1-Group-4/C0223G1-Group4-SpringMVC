package com.example.case_study.service.route_service;

import com.example.case_study.model.tai.AirCraft;
import com.example.case_study.model.tai.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRouteService {
    Page<Route> getAllListRoute(Pageable pageable);
    List<Route> checkAllListRoute();

    boolean createRoute(Route route);
    boolean editRoute(Route route);
    void deleteRoute(Route route);

    Route findByIdRoute(int id);;
}

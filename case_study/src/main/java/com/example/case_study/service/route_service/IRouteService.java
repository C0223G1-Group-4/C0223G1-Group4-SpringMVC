package com.example.case_study.service.route_service;

import com.example.case_study.model.AirCraft;
import com.example.case_study.model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRouteService {
    Page<Route> getAllListRoute(Pageable pageable);

    void createRoute(Route route);
    void editRoute(Route route);
    void deleteRoute(Route route);

    AirCraft findByIdRoute(int id);;
}

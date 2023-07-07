package com.example.case_study.service.route_service;

import com.example.case_study.model.tai.Route;
import com.example.case_study.repository.IRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService implements IRouteService {
    @Autowired
    private IRouteRepository iRouteRepository;

    @Override
    public Page<Route> getAllListRoute(Pageable pageable) {
        return iRouteRepository.findFlightScheduleByFlagFalse(pageable);
    }

    @Override
    public List<Route> checkAllListRoute() {
        return iRouteRepository.getFlightScheduleByFlagFalse();
    }

    @Override
    public boolean createRoute(Route route) {
            this.iRouteRepository.save(route);
            return true;
    }

    @Override
    public boolean editRoute(Route route) {
        if (this.findByIdRoute(route.getId()) != null) {
            this.iRouteRepository.save(route);
            return true;
        }
        return false;
    }

    @Override
    public void deleteRoute(Route route) {
        this.iRouteRepository.save(route);
    }

    @Override
    public Route findByIdRoute(int id) {
        return this.iRouteRepository.findById(id).orElse(null);
    }
}

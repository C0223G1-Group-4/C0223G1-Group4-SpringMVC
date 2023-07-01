package com.example.case_study.repository;

import com.example.case_study.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRouteRepository extends JpaRepository<Integer, Route> {
}

package com.example.case_study.repository;

import com.example.case_study.model.AirCraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAirCraftRepository extends JpaRepository<AirCraft ,Integer> {
}

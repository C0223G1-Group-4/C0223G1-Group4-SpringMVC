package com.example.case_study.repository;

import com.example.case_study.model.tai.AirCraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAirCraftRepository extends JpaRepository<AirCraft ,Integer> {
    Page<AirCraft> getAirCraftByFlagIsFalse(Pageable pageable);
    List<AirCraft> getAirCraftByFlagIsFalse();
}

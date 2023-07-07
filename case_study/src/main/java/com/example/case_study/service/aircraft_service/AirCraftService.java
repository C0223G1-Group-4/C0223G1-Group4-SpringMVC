package com.example.case_study.service.aircraft_service;

import com.example.case_study.model.tai.AirCraft;
import com.example.case_study.repository.IAirCraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirCraftService implements IAirCraftService{
    @Autowired
    private IAirCraftRepository iAirCraftRepository;
    @Override
    public Page<AirCraft> getAllListAirCraft(Pageable pageable) {
        return iAirCraftRepository.getAirCraftByFlagIsFalse(pageable);
    }

    @Override
    public List<AirCraft> checkAllListAirCraft() {
        return iAirCraftRepository.getAirCraftByFlagIsFalse();
    }

    @Override
    public void createAirCraft(AirCraft airCraft) {
       iAirCraftRepository.save(airCraft);
    }

    @Override
    public void editAirCraft(AirCraft airCraft) {
      iAirCraftRepository.save(airCraft);
    }

    @Override
    public void deleteAirCraft(AirCraft airCraft) {
         iAirCraftRepository.save(airCraft);
    }

    @Override
    public AirCraft findByIdAirCraft(int id) {
        return iAirCraftRepository.findById(id).orElse(null);
    }
}

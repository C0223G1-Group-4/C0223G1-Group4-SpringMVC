package com.example.case_study.service.chair_service;

import com.example.case_study.model.Chair;
import com.example.case_study.repository.IChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChairService implements IChairService{
    @Autowired
    private IChairRepository chairRepository;
    @Override
    public List<Chair> getList(int num) {
        return chairRepository.getChairByNum(num);
    }
}

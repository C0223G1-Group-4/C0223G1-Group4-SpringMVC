package com.example.case_study.repository;

import com.example.case_study.model.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IChairRepository extends JpaRepository<Chair,Integer> {
    @Query(value = "select * from chair where name_chair like concat(:num,'%')",nativeQuery = true)
    List<Chair> getChairByNum(@Param("num") int num);

}

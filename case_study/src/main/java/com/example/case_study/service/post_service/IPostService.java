package com.example.case_study.service.post_service;

import com.example.case_study.model.Employees;
import com.example.case_study.model.Post;
import javafx.geometry.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {
    Page<Post> findAll(Pageable pageable);
    void create(Post post);
    void update(Post post);
    void delete(Integer id);
    Post findById(Integer id);
}

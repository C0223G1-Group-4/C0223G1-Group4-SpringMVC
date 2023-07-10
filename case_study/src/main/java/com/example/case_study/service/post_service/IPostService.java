package com.example.case_study.service.post_service;

import com.example.case_study.model.Post;

import java.util.List;

public interface IPostService {
    List<Post> findAll();
    void create(Post post);
    void update(Post post);
    void delete(Integer id);
    Post findById(Integer id);
}

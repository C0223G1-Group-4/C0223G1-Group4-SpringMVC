package com.example.case_study.service.post_service;

import com.example.case_study.model.Post;
import com.example.case_study.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService{
    @Autowired
    private IPostRepository postRepository;
    @Override
    public List<Post> findAll() {
        return postRepository.findByFlagDeleteFalse();
    }

    @Override
    public void create(Post post) {
        postRepository.save(post);
    }

    @Override
    public void update(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(Integer id) {
        postRepository.updatePostFlagDelete(id);
    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findPostById(id);
    }
}

package com.example.case_study.service.post_service;

import com.example.case_study.model.Post;
import com.example.case_study.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService{
    @Autowired
    private IPostRepository postRepository;
    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findByFlagDeleteFalse(pageable);
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
        try{
            return postRepository.findPostById(id);
        }catch (Exception e){
            return null;
        }

    }
}

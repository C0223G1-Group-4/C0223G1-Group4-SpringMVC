package com.example.case_study.repository;

import com.example.case_study.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByFlagDeleteFalse();
    Post findPostById(Integer id);
    @Query(value = "UPDATE from Post set flagDelete = true where Post.id = :id", nativeQuery = true)
    void updatePostFlagDelete( Integer id);
}

package com.example.case_study.repository;

import com.example.case_study.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IPostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByFlagDeleteFalse();
    Optional<Post> findPostById(Integer id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Post set flag_delete = 1 where Post.id = :id", nativeQuery = true)
    void updatePostFlagDelete( Integer id);
}

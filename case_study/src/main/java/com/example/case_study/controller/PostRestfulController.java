package com.example.case_study.controller;

import com.example.case_study.model.Post;
import com.example.case_study.service.post_service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aip/post")
public class PostRestfulController {
    @Autowired
    private IPostService postService;
    @GetMapping("/detail/{id}")
    public ResponseEntity<Post> detail(@PathVariable Integer id){
        if (this.postService.findById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new  ResponseEntity<>(this.postService.findById(id), HttpStatus.OK);
    }
}

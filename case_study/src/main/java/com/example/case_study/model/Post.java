package com.example.case_study.model;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String namePost;
    private String content;
    private String image;
    private String datePost;
    @ManyToOne
    private Employees employees;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;
    public Post() {
    }

    public Post(Integer id, String namePost, String content, String image, String datePost, Employees employees) {
        this.id = id;
        this.namePost = namePost;
        this.content = content;
        this.image = image;
        this.datePost = datePost;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePost() {
        return namePost;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
}

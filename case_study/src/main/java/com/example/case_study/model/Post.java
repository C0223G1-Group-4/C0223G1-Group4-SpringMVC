package com.example.case_study.model;

import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String namePost;
    @Column(columnDefinition = "LongText")
    private String content;
    @Column(columnDefinition = "LongText")
    private String image;
    private String datePost;
    private String heading;

    @ManyToOne
    private Employees employees;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;
    public Post() {
    }

    public Post(Integer id, String namePost, String content, String image, String datePost, String heading, Employees employees, boolean flagDelete) {
        this.id = id;
        this.namePost = namePost;
        this.content = content;
        this.image = image;
        this.datePost = datePost;
        this.heading = heading;
        this.employees = employees;
        this.flagDelete = flagDelete;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
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

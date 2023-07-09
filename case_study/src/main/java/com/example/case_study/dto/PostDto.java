package com.example.case_study.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PostDto {
    private Integer id;
    @NotBlank
    @Size(max = 500, message = "Length must be from 10 to 500")
    private String namePost;
    @NotBlank
    @Size(max = 5000)
    @Pattern(regexp = "^([\\w\\d\\s]+[,]*)+$", message = "Do not accept special characters")
    private String content;
    @NotBlank
    @Size(max = 50000)
    private String image;
    @NotNull(message = "Age cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String datePost;

    public PostDto() {
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
}

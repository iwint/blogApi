package com.blog.blogApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class Blog {
    private final UUID blog_id;
    @NotBlank(message = "Title should not be blank")
    private final String title;

    private final String description;

    public Blog(@JsonProperty("id") UUID id,
               @JsonProperty("title") String title ,
               @JsonProperty("description") String description){
        this.blog_id = id;
        this.title = title;
        this.description = description;

    }
    public UUID getBlog_id() {
        return blog_id;
    }
    public String getBlogTitle(){
        return title;
    }
    public String getDescription() {
        return description;
    }


}

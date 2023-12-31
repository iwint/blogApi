package com.blog.blogApi.api;

import com.blog.blogApi.model.Blog;
import com.blog.blogApi.service.BlogService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequestMapping("api/v1/blog")
@RestController
public class BlogController {
    private final BlogService blogService;
    @Autowired
    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }
    @PostMapping
    public List<Blog> addBlog(@Valid @NotNull @RequestBody Blog blog){
        System.out.println("ADDED BLOG" + " " + blog);
        return blogService.addBlog(blog);

    }

    @GetMapping
    public List<Blog> getAllBlogs(){
        System.out.println("GET METHOD CALLED FROM CLIENT");
        return blogService.getAllBlogs();
    }

    @GetMapping(path = "{id}")
    public Blog getBlogById(@PathVariable("id") UUID id){
        System.out.println("GOT ID " +id);
        return blogService.getBlogById(id).orElse(null);

    }
    @DeleteMapping(path = "{id}")
    public List<Blog> deleteBlog(@PathVariable("id") UUID id){
        System.out.println("DELETED BLOG "+id);
        return blogService.deleteBlog(id);

    }

    @PutMapping(path = "{id}")
    public Blog editBlog(@PathVariable("id") UUID id ,@Valid @NotNull @RequestBody Blog blog){
        System.out.println("EDITING BLOG" +blog.getBlogTitle());
        return blogService.editBlog(id,blog);
    }

}

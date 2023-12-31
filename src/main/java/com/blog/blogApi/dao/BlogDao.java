package com.blog.blogApi.dao;

import com.blog.blogApi.model.Blog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BlogDao {
    List insertBlog(UUID id , Blog blog);
    default List insertBlog(Blog blog){
        UUID id = UUID.randomUUID();
        return insertBlog(id , blog);
    }
    List<Blog> selectAllBlogs();
    Optional<Blog> selectBlogById(UUID id);
    List<Blog> removeBlog(UUID id);
    Blog updateBlog(UUID id,Blog blog);

}

package com.blog.blogApi.service;

import com.blog.blogApi.dao.BlogDao;
import com.blog.blogApi.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class BlogService {
    private final BlogDao blogDao;
    @Autowired
    public BlogService(@Qualifier("postgres") BlogDao blogDao){
        this.blogDao = blogDao;
    }
    public int addBlog(Blog blog){
        return blogDao.insertBlog(blog);
    }

    public List<Blog> getAllBlogs(){
        return blogDao.selectAllBlogs();
    }

    public Optional<Blog> getBlogById(UUID id){
        return blogDao.selectBlogById(id);
    }

    public int deleteBlog(UUID id){
        return blogDao.removeBlog(id);
    }

    public int editBlog(UUID id ,Blog blog){
        return blogDao.updateBlog(id,blog);
    }
}

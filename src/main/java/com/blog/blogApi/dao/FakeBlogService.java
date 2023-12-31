package com.blog.blogApi.dao;

import com.blog.blogApi.model.Blog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("FakeDao")
public class FakeBlogService implements BlogDao{

    private final List<Blog> DB = new ArrayList<>();
    @Override
    public List<Blog> insertBlog(UUID id, Blog blog) {
        DB.add(new Blog(id ,blog.getBlogTitle(),blog.getDescription()));
        return DB;
    }

    @Override
    public List<Blog> selectAllBlogs() {
        return DB;
    }

    @Override
    public Optional<Blog> selectBlogById(UUID id) {
        return DB.stream().filter(blog -> blog.getBlog_id().equals(id)).findFirst();
    }

    @Override
    public List<Blog> removeBlog(UUID id) {
        Blog blog = selectBlogById(id).orElse(null);
        System.out.println("BLOG NAME : " +blog.getBlogTitle());
        DB.remove(blog);
        return selectAllBlogs();
    }

    @Override
    public Blog updateBlog(UUID id, Blog blog) {
        Blog prevBlog = selectBlogById(id).orElse(null);
        Blog editedBlog = new Blog(prevBlog.getBlog_id(),blog.getBlogTitle(), blog.getDescription());
        int index = DB.indexOf(prevBlog);
        DB.remove(index);
        DB.add(index,editedBlog);
        return editedBlog;
    }


}

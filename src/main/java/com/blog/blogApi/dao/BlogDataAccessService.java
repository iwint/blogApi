package com.blog.blogApi.dao;

import com.blog.blogApi.model.Blog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class BlogDataAccessService implements BlogDao{
   private final JdbcTemplate jdbcTemplate;

   public BlogDataAccessService(JdbcTemplate jdbcTemplate){
       this.jdbcTemplate = jdbcTemplate;
   }
    @Override
    public List<Blog> insertBlog(UUID id, Blog blog) {
        final String sql = "INSERT INTO blog(id,title,description) VALUES(?,?,?)";
        jdbcTemplate.update(sql,new Object[]{id,blog.getBlogTitle(),blog.getDescription()});
        return selectAllBlogs();
    }

    @Override
    public List<Blog> insertBlog(Blog blog) {
       return BlogDao.super.insertBlog(blog);
    }

    @Override
    public List<Blog> selectAllBlogs() {
       final String sql = "SELECT id,title,description FROM blog";
       List<Blog> Blogs = jdbcTemplate.query(sql,(resultSet , index)->{
           UUID id = UUID.fromString(resultSet.getString("id"));
           String title = resultSet.getString("title");
           String description = resultSet.getString("description");
           return new Blog(id,title,description);
       });
        return Blogs;
    }

    @Override
    public Optional<Blog> selectBlogById(UUID id) {

        final String sql = "SELECT id,title,description FROM blog WHERE id = ?";
        Blog blog = jdbcTemplate.queryForObject(sql,new Object[]{id},(resultSet , index)->{
            UUID blogId = UUID.fromString(resultSet.getString("id"));
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            return new Blog(blogId,title,description);
        });

        return Optional.ofNullable(blog);
    }

    @Override
    public List<Blog> removeBlog(UUID id) {
        System.out.println("BLOG ID : " + id);
        final String sql = "DELETE FROM blog WHERE id = ?";
        jdbcTemplate.update(sql,new Object[]{id});
        return selectAllBlogs();
    }

    @Override
    public Blog updateBlog(UUID id, Blog blog) {

       final String sql = "UPDATE blog SET title = ?, description = ? WHERE id = ?";
         jdbcTemplate.update(sql,new Object[]{blog.getBlogTitle(),blog.getDescription(),id});
         return selectBlogById(id).orElse(null);


    }
}

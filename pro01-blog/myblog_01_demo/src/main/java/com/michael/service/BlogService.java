package com.michael.service;

import com.michael.pojo.Blog;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;


public interface BlogService {
    void addBlog(Blog blog);
    List<Blog> selectByAuthor(String author);
    LinkedHashMap<String,List<Blog>> archiveBlog(String author);
    Blog selectById(Long id);
}

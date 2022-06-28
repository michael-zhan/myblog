package com.michael.service;

import com.michael.pojo.Blog;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;


public interface BlogService {
    /**
     * 添加文章
     * @param blog
     */
    void addBlog(Blog blog);
    /**
     * 根据作者查询文章
     * @param author
     * @return
     */
    List<Blog> selectByAuthor(String author);

    /**
     * 文章归档
     * @param author
     * @return
     */
    LinkedHashMap<String,List<Blog>> archiveBlog(String author);

    /**
     * 根据Id查询文章
     * @param id
     * @return
     */
    Blog selectById(Long id);

    /**
     * 根据Id删除文章
     * @param id
     */
    boolean delectById(Integer id);
}

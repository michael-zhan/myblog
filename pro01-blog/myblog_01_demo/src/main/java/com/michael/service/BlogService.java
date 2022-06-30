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
    List<Blog> getByAuthor(String author);

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
    Blog getById(Integer id);

    /**
     * 根据Id删除文章
     * @param id
     */
    boolean removeById(Integer id);

    /**
     * 浏览文章,每次浏览则浏览数加1
     * @param id
     * @return
     */
    Blog viewBlog(Integer id);

    /**
     * 根据页码查询文章
     * @param author
     * @param page
     * @return
     */
    List<Blog> getByPage(String author,Integer page,Integer eachPageCount);

    /**
     * 返回文章列表页数
     * @param author
     * @return
     */
    Integer getCount(String author);

    /**
     *增加文章点赞数
     */
    void likeBlog(Integer bloghId);
}

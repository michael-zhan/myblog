package com.michael.service.Impl;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.michael.mapper.BlogMapper;
import com.michael.pojo.Blog;
import com.michael.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service("blogServiceImpl")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public void addBlog(Blog blog){
        System.out.println("文章已添加");
    }

    @Override
    public List<Blog> selectByAuthor(String author) {
        return blogMapper.selectByAuthor(author);
    }

    @Override
    public LinkedHashMap<String, List<Blog>> archiveBlog(String author) {
        List<String> years = blogMapper.findGroupYear(author);
        LinkedHashMap<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogMapper.findByYear(year,author));
        }
        return map;
    }
}

package com.michael.service.Impl;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.michael.pojo.Blog;
import com.michael.service.BlogService;
import org.springframework.stereotype.Service;

@Service("blogServiceImpl")
public class BlogServiceImpl implements BlogService {

    public void addBlog(Blog blog){
        System.out.println("文章已添加");
    }
}

package com.michael.service.Impl;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.michael.mapper.BlogMapper;
import com.michael.pojo.Blog;
import com.michael.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service("blogServiceImpl")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public void addBlog(Blog blog) {
        Date date=new Date();
        if (blog.getId() != null && blogMapper.selectByPrimaryKey(blog.getId().longValue()) != null) {
            blog.setUpdatetime(date);
            blogMapper.updateByPrimaryKey(blog);
        } else {
            blog.setId(null);
            blog.setView(0);
            blog.setCountComment(0);
            blog.setCountLike(0);
            blog.setUpdatetime(date);
            blogMapper.insert(blog);
        }

    }

    @Override
    public List<Blog> getByAuthor(String author) {
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

    public Blog getById(Integer id){
        return blogMapper.selectByPrimaryKey(id.longValue());
    }

    @Override
    public boolean removeById(Integer id) {
        blogMapper.deleteByPrimaryKey(id.longValue());
        return blogMapper.selectByPrimaryKey(id.longValue())==null;
    }

    @Override
    public Blog viewBlog(Integer id) {
        Blog blog=blogMapper.selectByPrimaryKey(id.longValue());
        blogMapper.updateViewById(id.longValue());
        return blog;
    }

    @Override
    public List<Blog> getByPage(String author, Integer page,Integer eachPageCount) {

        return blogMapper.selectWithLimit(author,(page-1)*eachPageCount,eachPageCount);
    }

    @Override
    public Integer getCount(String author) {
        return blogMapper.selectCountByAuthor(author);
    }

    @Override
    public void likeBlog(Integer blogId) {
        blogMapper.updateLikeById(blogId.longValue());
    }

    @Override
    public String findAuthor(Integer id) {
        return blogMapper.selectAuthorById(id.longValue());
    }


}

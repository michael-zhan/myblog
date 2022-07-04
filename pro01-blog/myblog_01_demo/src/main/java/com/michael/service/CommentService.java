package com.michael.service;

import com.github.pagehelper.PageInfo;
import com.michael.pojo.Blog;
import com.michael.pojo.Comment;


import java.util.HashMap;
import java.util.List;


public interface CommentService {

    // 对指定字段查询
    PageInfo<Comment> pageComment(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria);

    // 修改评论状态
    Comment updateCommentPass(Integer id, Integer pass);

    // 添加评论
    Comment insertComment(Comment comment);

    // 删除评论
    void deleteComment(Integer commentId);

    // 查找当前博客下的所有评论
    PageInfo<Comment> getListCommentByBlogId(Integer blogPublished,Integer commentPublished, Integer postType,Integer pageIndex, Integer pageSize,Integer blogId);

    // 根据id获取评论
    Comment getById(Integer blogPublished,Integer commentPublished, Integer postType,Integer id);

    Comment getByIdMyself(Integer id);

    // 查找每种状态下的评论数
    Integer countCommentByPass(Integer pass);

    // 批量查询
    List<Comment> findByBatchIds(List<Integer> ids);

    // 查询最新评论
    List<Comment> getCommentLimit(Integer limit);

    /**
     * 根据页码和每页的留言数查询留言
     * @param master
     * @param page
     * @param eachPageCount
     */
    List<Comment> getByPage(Integer master, Integer page, Integer eachPageCount);

    /**
     * 返回留言数
     * @param master
     * @return
     */
    Integer getCount(Integer master);
}

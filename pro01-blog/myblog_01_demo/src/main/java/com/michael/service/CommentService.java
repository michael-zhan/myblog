package com.michael.service;


import com.michael.pojo.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 获取评论
     * @param blogId
     * @return
     */
    List<Comment> getCommentList(Integer blogId);

    /**
     * 添加评论
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 删除评论
     */
    void removeComment(Integer id);
}

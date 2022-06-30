package com.michael.service.Impl;

import com.michael.pojo.Comment;
import com.michael.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    /**
     * 未实现
     * @param blogId
     * @return
     */
    @Override
    public List<Comment> getCommentList(Integer blogId) {
        return null;
    }

    /**
     * 未实现
     * @param comment
     */
    @Override
    public void addComment(Comment comment) {

    }

    /**
     * 未实现
     * @param id
     */
    @Override
    public void removeComment(Integer id) {

    }
}

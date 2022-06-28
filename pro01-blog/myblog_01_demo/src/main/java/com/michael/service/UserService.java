package com.michael.service;

import com.michael.pojo.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    /**
     * 添加用户
     * @param user
     * @throws NoSuchAlgorithmException
     */
    void insert(User user) throws NoSuchAlgorithmException;

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User selectById(String id);

    /**
     * 核实用户
     * @param id
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    User selectByIdAndPassword(String id,String password) throws NoSuchAlgorithmException;

    /**
     * 查询好友列表
     * @param id
     * @return
     */
    List<User> selectFriendListById(String id);
}

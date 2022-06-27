package com.michael.service;

import com.michael.pojo.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    void insert(User user) throws NoSuchAlgorithmException;
    User selectById(String id);
    User selectByIdAndPassword(String id,String password) throws NoSuchAlgorithmException;
    List<User> selectFriendListById(String id);
}

package com.michael.service;

import com.michael.pojo.User;

import java.security.NoSuchAlgorithmException;

public interface UserService {

    void insert(User user) throws NoSuchAlgorithmException;
    User selectById(String id);
    User selectByIdAndPassword(String id,String password) throws NoSuchAlgorithmException;
}

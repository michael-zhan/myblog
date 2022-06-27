package com.michael.service.Impl;

import com.michael.mapper.UserMapper;
import com.michael.pojo.User;
import com.michael.service.UserService;
import com.michael.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper usermapper;




    @Override
    public void insert(User user) throws NoSuchAlgorithmException {
        String passowrd=MD5Util.getMd5(user.getPassword());
        user.setPassword(passowrd);
        usermapper.insert(user);
    }

    @Override
    public User selectById(String id) {
        return usermapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByIdAndPassword(String id, String password) throws NoSuchAlgorithmException {
        User user = usermapper.selectByIdAndPassword(id, MD5Util.getMd5(password));
        return user;
    }
}

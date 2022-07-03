package com.michael.service;



import com.michael.pojo.Notice;
import com.michael.pojo.User;

import java.util.List;


public interface UserService {
    /**
     * 获得用户列表
     *
     * @return 用户列表
     */
    List<User> listUser(Integer userId);

    /**
     * 根据id查询用户信息
     *
     * @param id 用户ID
     * @return 用户
     */
    User getUserById(Integer id);

    /**
     * 修改用户信息
     *
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void deleteUser(Integer id);

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 用户
     */
    User insertUser(User user);

    /**
     * 根据用户名和邮箱查询用户
     *
     * @param str 用户名或Email
     * @return 用户
     */
    User getUserByNameOrEmail(String str);

    /**
     * 根据用户名查询用户
     *
     * @param name 用户名
     * @return 用户
     */
    User getUserByName(String name);

    /**
     * 根据邮箱查询用户
     *
     * @param email Email
     * @return 用户
     */
    User getUserByEmail(String email);

    /**
     * 查询好友列表
     * @param id
     * @return
     */
    List<User> getFriendList(Integer id);

    Boolean sendMakeFriendRequest(Integer sender,Integer receiver);


    /**
     * 获取通知列表
     * @param userId
     * @return
     */
    List<Notice> getNoticeList(Integer userId);

    /**
     * 处理好友请求
     * @param noticeId
     * @param sign
     */
    void dealWithFriendRequest(Integer noticeId,Integer sign);

    /**
     * 删除好友
     * @param friendId
     */
    void removeFriend(Integer friendId);
}

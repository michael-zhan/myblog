package com.michael.service;

import com.michael.pojo.Notice;
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
    User getById(String id);

    /**
     * 核实用户身份
     * @param id
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    User selectByIdAndPassword(String id,String password) throws NoSuchAlgorithmException;

    /**
     * 查询好友列表
     * @param user
     * @return
     */
    List<User> getFriendList(String user);

    /**
     * 发送添加好友的请求
     * 返回1说明已经是好友
     * @param sender
     * @param receiver
     */
    Integer sendMakeFriendRequest(String sender,String receiver);

    /**
     * 获取通知列表
     * @param userId
     * @return
     */
    List<Notice> getNoticeList(String userId);
    /**
     * 处理好友请求
     * @param noticeId
     * @param sign
     */
    void dealWithFriendRequest(Integer noticeId,Integer sign);

    /**
     * 更新个人信息
     * @param user
     */
    void modifyInfo(User user);

    /**
     * 删除好友
     * @param friendId
     */
    void removeFriend(String friendId);
}

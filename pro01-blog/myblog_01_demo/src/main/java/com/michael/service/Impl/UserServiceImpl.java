package com.michael.service.Impl;


import com.michael.mapper.BlogMapper;
import com.michael.mapper.FriendshipMapper;
import com.michael.mapper.NoticeMapper;
import com.michael.mapper.UserMapper;
import com.michael.pojo.Friendship;
import com.michael.pojo.Notice;
import com.michael.pojo.User;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private BlogMapper blogMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private FriendshipMapper friendshipMapper;

    /**
     * 获取所有用户并查询该用户所写的文章数
     * @return List<User>
     */
    @Override
    public List<User> listUser(Integer userId) {
        List<User> userList = userMapper.listUser();
        for (int i = 0; i < userList.size(); i++) {
            Integer blogCount = blogMapper.countBlogByUser(userList.get(i).getId());
            userList.get(i).setBlogCount(blogCount);
        }
        return userList;
    }

    /**
     * 根据用户id查询
     * @param id 用户ID
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        User u = userMapper.getUserById(id);
        u.setBlogCount(blogMapper.selectCountByAuthor(u.getId()));
        return u;
    }

    /**
     * 修改用户
     * @param user 用户
     */
    @Override
    public void updateUser(User user) {
        userMapper.update(user);
    }

    /**
     * 删除用户
     * @param id 用户ID
     */
    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 添加一位用户
     * @param user 用户
     * @return
     */
    @Override
    public User insertUser(User user) {
        user.setRegisterTime(new Date());
        userMapper.insert(user);
        return user;
    }

    /**
     * 根据用户名或Email查询用户
     * @param str 用户名或Email
     * @return
     */
    @Override
    public User getUserByNameOrEmail(String str) {
        return userMapper.getUserByNameOrEmail(str);
    }

    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return
     */
    @Override
    public User getUserByName(String name) {
        User u = userMapper.getUserByName(name);
        u.setBlogCount(blogMapper.selectCountByAuthor(u.getId()));
        return u;
    }

    /**
     * 根据Email查询用户
     * @param email Email
     * @return
     */
    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public List<User> getFriendList(Integer userId) {
        List<Integer> friendListStr = friendshipMapper.selectByUser(userId);
        List<User> friendList=new ArrayList<>();

        for(Integer friendId:friendListStr){
            User friend=getUserById(friendId);
            if(friend!=null){
                friendList.add(friend);
            }
        }
        return friendList;
    }

    @Override
    public Boolean sendMakeFriendRequest(Integer sender, Integer receiver) {

        Integer i = friendshipMapper.selectByUserAndFriend(sender, receiver);
        if(i==0) {
            Date d = new Date();
            Notice notice = new Notice(null, sender, receiver, false, d);
            noticeMapper.insert(notice);
            return true;
        }
        return false;
    }

    @Override
    public List<Notice> getNoticeList(Integer userId) {
        return noticeMapper.selectByUserId(userId);
    }

    @Override
    public void dealWithFriendRequest(Integer userId,Integer senderId,Integer sign) {

        Integer noticeId = noticeMapper.selectBySAndR(senderId, userId);
        Integer i1 = friendshipMapper.selectByUserAndFriend(userId,senderId);
        Integer i2 = friendshipMapper.selectByUserAndFriend(senderId,userId);
        if(sign==1&&i1==0&&i2==0){
            Friendship friendship1 = new Friendship(null, senderId, userId);
            Friendship friendship2 = new Friendship(null, userId,senderId);
            friendshipMapper.insert(friendship1);
            friendshipMapper.insert(friendship2);
        }
        noticeMapper.deleteByPrimaryKey(noticeId.longValue());
    }

    @Override
    public void removeFriend(Integer friendId) {
        friendshipMapper.deleteByUserIdOrFriendId(friendId);
    }

}

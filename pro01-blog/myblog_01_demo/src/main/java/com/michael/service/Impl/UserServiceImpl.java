package com.michael.service.Impl;

import com.michael.mapper.FriendshipMapper;
import com.michael.mapper.NoticeMapper;
import com.michael.mapper.UserMapper;
import com.michael.pojo.Friendship;
import com.michael.pojo.Notice;
import com.michael.pojo.User;
import com.michael.service.UserService;
import com.michael.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper usermapper;
    @Autowired
    private FriendshipMapper friendshipMapper;
    @Autowired
    private NoticeMapper noticeMapper;



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

    @Override
    public List<User> selectFriendListById(String id) {
        List<String> strings = friendshipMapper.selectByUser(id);
        List<User> friendList=new ArrayList<>();
        for(String friendId:strings){
            User user=usermapper.selectByPrimaryKey(friendId);
            if(user!=null){
                friendList.add(user);
            }
        }
        return friendList;
    }

    @Override
    public Integer sendMakeFriendRequest(String sender, String receiver) {
        Integer i = friendshipMapper.selectByUserAndFriend(sender, receiver);
        if(i==0) {
            Date d = new Date();
            Notice notice = new Notice(null, sender, receiver, false, d);
            noticeMapper.insert(notice);
            return 1;
        }
        return 0;
    }

    @Override
    public void dealWithFriendRequest(Integer noticeId,Integer sign) {
        Notice notice = noticeMapper.selectByPrimaryKey(noticeId.longValue());

        String sender = notice.getSender();
        String receiver = notice.getReceiver();
        if(sign==1){
            Friendship friendship = new Friendship(null, sender, receiver);
            friendshipMapper.insert(friendship);
        }

        noticeMapper.deleteByPrimaryKey(noticeId.longValue());
    }
}

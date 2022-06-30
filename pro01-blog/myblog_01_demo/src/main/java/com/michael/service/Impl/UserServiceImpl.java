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
import java.util.Objects;

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
    public User getById(String id) {
        return usermapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByIdAndPassword(String id, String password) throws NoSuchAlgorithmException {
        User user = usermapper.selectByIdAndPassword(id, MD5Util.getMd5(password));
        return user;
    }

    @Override
    public List<User> getFriendList(String userId) {
        List<String> strings = friendshipMapper.selectByUser(userId);
        List<User> friendList=new ArrayList<>();

        for(String friendId:strings){
            User friend=usermapper.selectByPrimaryKey(friendId);
            if(friend!=null){
                friendList.add(friend);
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
    public List<Notice> getNoticeList(String userId) {
         return noticeMapper.selectByUserId(userId);
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

    @Override
    public void modifyInfo(User user) {
        usermapper.updateByPrimaryKey(user);
    }

    @Override
    public void removeFriend(String friendId) {
        friendshipMapper.delectByUserIdOrFriendId(friendId);
    }
}

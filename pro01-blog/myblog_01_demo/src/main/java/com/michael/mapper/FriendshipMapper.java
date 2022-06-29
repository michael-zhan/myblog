package com.michael.mapper;

import com.michael.pojo.Friendship;
import java.util.List;

public interface FriendshipMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friendship
     *
     * @mbggenerated Tue Jun 28 09:10:37 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friendship
     *
     * @mbggenerated Tue Jun 28 09:10:37 CST 2022
     */
    int insert(Friendship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friendship
     *
     * @mbggenerated Tue Jun 28 09:10:37 CST 2022
     */
    Friendship selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friendship
     *
     * @mbggenerated Tue Jun 28 09:10:37 CST 2022
     */
    List<Friendship> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_friendship
     *
     * @mbggenerated Tue Jun 28 09:10:37 CST 2022
     */
    int updateByPrimaryKey(Friendship record);

    /**
     * 根据user_id返回friend_id列表
     * @param id
     * @return
     */
    List<String> selectByUser(String id);

    int selectByUserAndFriend(String user_id,String friend_id);
}
package com.michael.mapper;

import com.michael.pojo.User;
import java.util.List;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Sun Jun 26 20:25:48 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Sun Jun 26 20:25:48 CST 2022
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Sun Jun 26 20:25:48 CST 2022
     */
    User selectByPrimaryKey(String id);


    User selectByIdAndPassword(String id,String password);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Sun Jun 26 20:25:48 CST 2022
     */
    List<User> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Sun Jun 26 20:25:48 CST 2022
     */
    int updateByPrimaryKey(User record);
}
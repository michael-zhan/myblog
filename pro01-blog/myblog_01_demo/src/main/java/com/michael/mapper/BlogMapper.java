package com.michael.mapper;

import com.michael.pojo.Blog;
import java.util.List;

public interface BlogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog
     *
     * @mbggenerated Sun Jun 26 17:31:25 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog
     *
     * @mbggenerated Sun Jun 26 17:31:25 CST 2022
     */
    int insert(Blog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog
     *
     * @mbggenerated Sun Jun 26 17:31:25 CST 2022
     */
    Blog selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog
     *
     * @mbggenerated Sun Jun 26 17:31:25 CST 2022
     */
    List<Blog> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog
     *
     * @mbggenerated Sun Jun 26 17:31:25 CST 2022
     */
    int updateByPrimaryKey(Blog record);
}
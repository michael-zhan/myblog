package com.michael.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Type {
    private Integer id;
    private String name;

    private List<Blog> blogs=new ArrayList<>();

    /**
     * 文章数量
     */
    private Integer blogCount;

    public Type(Integer id, String name, Integer blogCount) {
        this.id = id;
        this.name = name;
        this.blogCount = blogCount;
    }

    public Type(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Type() {
    }
}

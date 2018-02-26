package com.hqs.test.dao.entity;

/**
 * @author huqinsong
 * @date 2018/2/26
 */
public class Test {

    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

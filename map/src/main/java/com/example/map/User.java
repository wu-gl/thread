package com.example.map;

import java.util.Comparator;

/**
 * @Description User
 * @Author 吴桂林
 * @Date 2019/12/27 19:41
 * @Version 1.0
 */

public class User implements Comparable<User> {

    public User(int age) {
        this.age = age;
    }

    public int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    @Override
//    public int compare(User o1, User o2) {
//        return o1.getAge() - o2.getAge();
//    }

    @Override
    public int compareTo(User o) {
        return this.age - o.age;
    }
}

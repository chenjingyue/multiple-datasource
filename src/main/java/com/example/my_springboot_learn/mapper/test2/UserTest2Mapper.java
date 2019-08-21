package com.example.my_springboot_learn.mapper.test2;

import com.example.my_springboot_learn.model.QueryCondition;
import com.example.my_springboot_learn.model.User;

import java.util.List;
import java.util.Map;

public interface UserTest2Mapper {
    List<User> selectAllUser() throws Exception;


}

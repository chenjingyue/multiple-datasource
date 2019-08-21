package com.example.my_springboot_learn.service.test1.impl;

import com.example.my_springboot_learn.mapper.test1.UserTest1Mapper;
import com.example.my_springboot_learn.model.User;
import com.example.my_springboot_learn.service.test1.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userServcieTest1")
@Transactional("test1TransactionManager")
public class UserService implements IUserService {

    @Autowired
    private UserTest1Mapper userMapper;

    @Override
    public List<User> selectAllUser() throws Exception {
        return userMapper.selectAllUser();
    }



}

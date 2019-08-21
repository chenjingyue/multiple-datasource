package com.example.my_springboot_learn.service.impl;

import com.example.my_springboot_learn.mapper.test2.UserTest2Mapper;
import com.example.my_springboot_learn.model.User;
import com.example.my_springboot_learn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional("test2TransactionManager")
public class UserService implements IUserService {

    @Autowired
    private UserTest2Mapper userMapper;

    @Override
    public List<User> selectAllUser() throws Exception {
        return userMapper.selectAllUser();
    }



}

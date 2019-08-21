package com.example.my_springboot_learn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.my_springboot_learn.model.QueryCondition;
import com.example.my_springboot_learn.model.ResponseVO;
import com.example.my_springboot_learn.model.User;
import com.example.my_springboot_learn.service.impl.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO<List<User>> getAllUser() {
        ResponseVO<List<User>> responseVO = new ResponseVO<>();
        try {
            logger.info("Query user information.");
            List<User> allUser = userService.selectAllUser();
            responseVO.setCode(HttpServletResponse.SC_OK);
            responseVO.setMessage("success");
            responseVO.setData(allUser);
            logger.info("User info: " + allUser);
        } catch (Exception e) {
            logger.error("Failed to query user information", e);
            responseVO.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseVO.setMessage("error");
        }
        return responseVO;
    }



}

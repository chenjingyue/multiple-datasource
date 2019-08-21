package com.example.my_springboot_learn.controller;

import com.alibaba.fastjson.JSON;
import com.example.my_springboot_learn.model.ResponseVO;
import com.example.my_springboot_learn.model.User;
import com.example.my_springboot_learn.service.test1.impl.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
//@CrossOrigin("*")
public class UserTest1Controller {
    private static final Logger logger = LogManager.getLogger(UserTest1Controller.class);

    @Resource(name="userServcieTest1")
    private UserService userService;

    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @RequestMapping(value="/userstest1", method = RequestMethod.GET)
    @ResponseBody
    public String getAllUserTest1(@RequestParam(name="callback",required = false) String callback) {
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
        return callback+"("+JSON.toJSONString(responseVO)+")";
    }



}

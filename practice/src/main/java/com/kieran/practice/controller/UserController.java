package com.kieran.practice.controller;

import com.kieran.practice.domain.vo.response.ApiResult;
import com.kieran.practice.model.User;
import com.kieran.practice.service.RedisService;
import com.kieran.practice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisService redisService;

    @GetMapping
    public ApiResult<User> getUser(@RequestParam String nickName) {
        User user =  userService.getUser(nickName);
        return ApiResult.success(user);
    }

    @GetMapping("/push")
    public ApiResult<Integer> push() {
        Integer count = redisService.push();
        return ApiResult.success(count);
    }




}

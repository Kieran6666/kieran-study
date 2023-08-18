package com.kieran.practice.service;

import com.kieran.practice.mapper.UserMapper;
import com.kieran.practice.model.User;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User getUser(String nickName) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("nickName", nickName);
        return userMapper.selectOneByExample(example);
    }
}

package com.watcher.springboot.Service;

import com.watcher.springboot.entity.User;
import com.watcher.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll(){
        return userMapper.findAll();
    }
    public int insertUser(User user){
        return userMapper.insertUser(user);
    }
}

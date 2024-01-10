package com.watcher.springboot.mapper;

import com.watcher.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    int insertUser(User user);

    User findUserByEmail(String email);
}

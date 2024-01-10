package com.watcher.springboot.controller;

import com.watcher.springboot.Service.UserService;
import com.watcher.springboot.entity.User;
import com.watcher.springboot.mapper.UserMapper;
import com.watcher.springboot.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/all-user")
    @ResponseBody
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/register")
    @ResponseBody
    public String insertUser(@RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("code") String code){
        //判断验证码是否正确
        if(!code.equals("123456")) return "验证码错误";
        User user=new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setIsVip(0);
        userService.insertUser(user);

        return "注册成功";
    }

    @GetMapping("/login")
    @ResponseBody
    public Map<String,Object> login(@RequestParam("email") String email,
                                    @RequestParam("password") String password
                        ){
        User user=userMapper.findUserByEmail(email);
        if(user==null) return null;
        if(user.getPassword().equals(password)){
            Map<String,Object> mp=new HashMap<>();
            mp.put("name",user.getName());
            mp.put("token",JwtUtil.createToken(user.getName(),user.getIsVip().toString()));
            return mp;
        }
        return null;
    }

}

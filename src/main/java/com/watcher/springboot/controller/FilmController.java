package com.watcher.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.watcher.springboot.Service.FilmService;
import com.watcher.springboot.entity.Film;
import com.watcher.springboot.entity.FilmJson;
import com.watcher.springboot.mapper.FilmMapper;
import com.watcher.springboot.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private FilmMapper filmMapper;

//    @GetMapping("all-film")
//    @ResponseBody
//    public List<Film> findAll(){return filmService.findAll();}

//    @GetMapping("/page")
//    @ResponseBody
//    public IPage<Film> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String search){
//      //  Map<String,Object> map=new HashMap<>(2);
//        IPage<Film> page=new Page<>(pageNum,pageSize);
//        QueryWrapper<Film> queryWrapper=new QueryWrapper<>();
//        IPage<Film> filmPage=filmService.page(page,queryWrapper);
//        return filmPage;
//    }
    @GetMapping("/page")
    @ResponseBody
    public IPage<FilmJson> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String search,@RequestParam String order) throws JsonProcessingException {

        Page<Film> page=new Page<>(pageNum,pageSize);
        IPage<Film> ipage=filmMapper.findSearch(page,search,order);
        return getFilmJsonIPage(ipage);
    }
    @GetMapping("/play")
    @ResponseBody
    public Map<String, Object> play(@RequestParam String name,@RequestParam String token){
        Map<String,Object> mp = new HashMap<>();
        //先检验vip权限
        Claims claims= JwtUtil.analyseToken(token);
        if(claims.get("isVip").equals("0")){
            Integer needVip=filmMapper.findByName(name);
            if(needVip.equals(1)){
                mp.put("code",200);
                mp.put("msg","权限不足");
                return mp;
            }
        }


        filmMapper.updateCountByName(name);
        mp.put("code",200);
        mp.put("msg","播放成功");
        return mp;
    }

    @GetMapping("/category")
    @ResponseBody
    public IPage<FilmJson> findCategory(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String search,@RequestParam String category) throws JsonProcessingException {
        Page<Film> page=new Page<>(pageNum,pageSize);
        IPage<Film> ipage=filmMapper.findCategory(page,search,category);
        return getFilmJsonIPage(ipage);
    }

    private IPage<FilmJson> getFilmJsonIPage(IPage<Film> ipage) throws JsonProcessingException {
        List<FilmJson> resJson=new ArrayList<>();
        for(Film film:ipage.getRecords()){
            System.out.println(film);
            resJson.add(new FilmJson(filmMapper.connect(film.getId())));
        }

        IPage<FilmJson> iPageJson = new Page<>();
        iPageJson.setRecords(resJson);
        iPageJson.setTotal(ipage.getTotal());
        iPageJson.setCurrent(ipage.getCurrent());
        iPageJson.setSize(ipage.getSize());
        iPageJson.setPages(ipage.getPages());

        return iPageJson;
    }

}

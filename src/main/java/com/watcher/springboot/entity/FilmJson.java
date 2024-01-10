package com.watcher.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FilmJson{
    private Integer id;
    private String name;
    private Float score;
    private Integer count;

    private Integer needVip;
    private String actors;
    private String categories;

    private static String listToJson(List<?> list){
        List<String> names = list.stream()
                .map(item -> {
                    // 在这里根据实际对象的类型调用相应的获取name属性的方法
                    if (item instanceof Actor) {
                        return ((Actor) item).getName();
                    } else if (item instanceof Category) {
                        return ((Category) item).getName();
                    }
                    // 如果是其他类型的对象，需要相应地处理
                    return null;
                })
                .filter(name -> name != null)  // 过滤掉为null的name
                .collect(Collectors.toList());
        if(names.size()==0){
            return "无";
        }
        return String.join(",",names);
    }

    // Constructor to create FilmJson from Film
    public FilmJson(Film film) throws JsonProcessingException {
        this.id = film.getId();
        this.name = film.getName();
        this.count=film.getCount();
        this.score = film.getScore();
        this.needVip = film.getNeedVip();
        this.actors = listToJson(film.getActors());
        this.categories = listToJson(film.getCategories());
    }
}

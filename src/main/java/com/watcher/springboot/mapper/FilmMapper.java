package com.watcher.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.watcher.springboot.entity.Film;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FilmMapper {
   // List<Film> findAll();
    IPage<Film> findSearch(Page<Film> page,String search,String order);
    IPage<Film> findCategory(Page<Film> page,String search,String category);
    Film connect(Integer id);

    void updateCountByName(String name);
    Integer findByName(String name);
}

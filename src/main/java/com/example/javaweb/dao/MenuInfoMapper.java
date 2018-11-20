package com.example.javaweb.dao;

import com.example.javaweb.domain.MenuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MenuInfoMapper {
    @Select("select * from menuinfo")
    List<MenuInfo> getAllMenu();

}

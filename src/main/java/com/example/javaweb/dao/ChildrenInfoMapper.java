package com.example.javaweb.dao;

import com.example.javaweb.domain.ChildrenInfo;
import com.example.javaweb.domain.ParentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ChildrenInfoMapper {
    @Select("select * from children_info ")
    List<ChildrenInfo> getAllChildrenInfo();

    //根据登录名返回子女信息，处理登陆
    @Select("select * from children_info where username=#{username}")
    ChildrenInfo getChildrenInfoByUsername(String username);
}


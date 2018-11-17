package com.example.javaweb.dao;

import com.example.javaweb.domain.ServeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ServeInfoMapper {

    @Select("select * from serve_info")
    public List<ServeInfo> getAllServerInfo();


}


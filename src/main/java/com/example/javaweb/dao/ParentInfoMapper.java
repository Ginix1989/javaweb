package com.example.javaweb.dao;

import com.example.javaweb.domain.ParentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ParentInfoMapper {
    @Select("select * from parent_info ")
    List<ParentInfo> getAllParentInfo();

    @Select("select * from parent_info where  userName=#{parentName}")
    ParentInfo getParentInfoByParentName(String parentName);
}

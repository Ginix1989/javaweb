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


}


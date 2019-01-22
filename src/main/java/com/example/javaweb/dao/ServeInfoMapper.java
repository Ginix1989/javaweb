package com.example.javaweb.dao;

import com.example.javaweb.domain.ServeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ServeInfoMapper {

    @Select("select * from serve_info t where t.ispersonal='0'")
    public List<ServeInfo> getAllServerInfo();

    //返回检索数据
    @Select("select * from serve_info t where t.ispersonal='0' and t.serverItemInfo like  CONCAT('%',#{itemInfo},'%') ")
    public List<ServeInfo> getAllServerInfoByType(String itemInfo);

    //返回护理检索数据
    @Select("select * from serve_info t where t.ispersonal = '2'")
    public List<ServeInfo> getAllNursingServeInfo();
    //返回护理检索数据
    @Select("select * from serve_info t where t.ispersonal = '2' and t.serverItemInfo like  CONCAT('%',#{itemInfo},'%') ")
    public List<ServeInfo> getAllNursingServeInfoByItem(String itemInfo);


}


package com.example.javaweb.dao;

import com.example.javaweb.domain.MenuInfo;
import com.example.javaweb.domain.VillageStaff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface VillageStaffMapper {
    @Select("select * from  village_staff")
    public List<VillageStaff> getAllVillageStaff();


    @Select("select * from  village_staff where loginname=#{name}")
    public VillageStaff getAllVillageStaffByName(String name);
}

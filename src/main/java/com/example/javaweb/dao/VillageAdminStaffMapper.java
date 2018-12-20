package com.example.javaweb.dao;

import com.example.javaweb.domain.VillageAdminStaff;
import com.example.javaweb.domain.VillageStaff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface VillageAdminStaffMapper {
    @Select("select * from  village_adminstaff")
    public List<VillageAdminStaff> getAllVillageAdminStaff();
}

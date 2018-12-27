package com.example.javaweb.controller;

import com.example.javaweb.domain.VillageAdminStaff;
import com.example.javaweb.service.VillageAdminStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小区管理员控制器
 */
@Controller
@RequestMapping("/index")
public class VillageAdminStaffController {

    @Autowired
    private VillageAdminStaffService villageAdminStaffService ;//管理员信息服务类

    @GetMapping("/getAllvillageAdminStaff")
    public @ResponseBody
    List<VillageAdminStaff> getAllVillageAdminStaff()
    {

        return villageAdminStaffService.getAllVillageAdminStaff();

    }

    /**
     * 根据ID 删除管理员信息
     * @param villageAdminStaffId
     * @return  管理员信息列表
     */
    @GetMapping("/deleteVillageAdminStaffById")
    public  @ResponseBody List<VillageAdminStaff> deleteVillageStaffById(@RequestParam Long villageAdminStaffId)
    {
        villageAdminStaffService.deleteVillageStaffById(villageAdminStaffId);
        return getAllVillageAdminStaff();

    }

    /**
     * 保存管理员信息
     * @param villageAdminStaff
     * @return
     */
    @PostMapping("/saveVillageAdminStaffInfo")
    public @ResponseBody List<VillageAdminStaff> saveVillageStaff(@RequestBody VillageAdminStaff villageAdminStaff)
    {
        villageAdminStaffService.saveVillageStaffInfo(villageAdminStaff);
        return getAllVillageAdminStaff();
    }
}

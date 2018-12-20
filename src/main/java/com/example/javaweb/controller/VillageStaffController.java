package com.example.javaweb.controller;

import com.example.javaweb.domain.VillageStaff;
import com.example.javaweb.service.VillageStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小区员工控制器
 */
@Controller
@RequestMapping("/index")
public class VillageStaffController {

    @Autowired
    private VillageStaffService villageStaffService ;//员工信息服务类

    @GetMapping("/getAllvillageStaff")
    public @ResponseBody
    List<VillageStaff> getAllVillageStaff()
    {

        return villageStaffService.getAllVillageStaff();

    }

    /**
     * 根据ID 删除员工信息
     * @param villageStaffId
     * @return  员工信息列表
     */
    @GetMapping("/deleteVillageStaffById")
    public  @ResponseBody List<VillageStaff> deleteVillageStaffById(@RequestParam Long villageStaffId)
    {
        villageStaffService.deleteVillageStaffById(villageStaffId);
        return getAllVillageStaff();

    }

    /**
     * 保存员工信息
     * @param villageStaff
     * @return
     */
    @PostMapping("/saveVillageStaffInfo")
    public @ResponseBody List<VillageStaff> saveVillageStaff(@RequestBody VillageStaff villageStaff)
    {
        villageStaffService.saveVillageStaffInfo(villageStaff);
        return getAllVillageStaff();
    }
}

package com.example.javaweb.controller;

import com.example.javaweb.domain.ParentInfo;
import com.example.javaweb.service.ParentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 父母信息控制器
 */
@Controller
@RequestMapping("/index")
public class ParentInfoController {

    //注入父母信息服务
    @Autowired
    ParentInfoService parentInfoService;
    @GetMapping("/getAllParetnInfo")
    public  @ResponseBody
    List<ParentInfo> getAllParentInfo()
    {
        return parentInfoService.getAllParentInfo();
    }

    /**
     * 根据Id删除父母信息
     * @param parentId
     * @return
     */
    @GetMapping("/deleteParentInfoById")
    public  @ResponseBody
    List<ParentInfo> deleteParentInfoById(@RequestParam Long parentId)
    {
        parentInfoService.deleteParentInfoById(parentId);
        return getAllParentInfo();
    }

    /**
     * 保存父母信息
     * @param parentInfo
     * @return
     */
    @PostMapping("/saveParentInfoEntity")
    public @ResponseBody
    List<ParentInfo> saveParentInfoEntity(@RequestBody ParentInfo parentInfo)
    {
        parentInfoService.saveParentByEntity(parentInfo);

        return getAllParentInfo();
    }
}

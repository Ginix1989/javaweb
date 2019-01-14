package com.example.javaweb.controller;

import com.example.javaweb.domain.ParentInfo;
import com.example.javaweb.securityExp.UserInfo;
import com.example.javaweb.service.ParentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //获取父母信息 用以修改
    @GetMapping("/getParentInfoById")
    public  @ResponseBody ParentInfo getParentInfo()
    {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ParentInfo parentInfo =parentInfoService.getParentInfoByParentId(userInfo.getUserid());

        return parentInfo;
    }

    //保存修改后的信息
    @PostMapping("/saveParentInfoForEdit")
    public  @ResponseBody
    Map<String,String> saveParentInfoForEdit(@RequestBody ParentInfo parentInfo)
    {
        return parentInfoService.saveParent(parentInfo);
    }

    //保存注册后的信息
    @PostMapping("/reg/saveParentInfoNewRegParent")
    public @ResponseBody
    Map<String,String> saveParentInfoNewRegParent(@RequestBody ParentInfo parentInfo)
    {
        return parentInfoService.saveParent(parentInfo);
    }


    @GetMapping("/reg/getAllParetnInfoReg")
    public  @ResponseBody
    List<ParentInfo> getAllParentInfoReg()
    {
        return parentInfoService.getAllParentInfo();
    }




}

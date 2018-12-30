package com.example.javaweb.controller;

import com.example.javaweb.domain.ChildrenInfo;
import com.example.javaweb.domain.ParentUseServe;
import com.example.javaweb.securityExp.UserInfo;
import com.example.javaweb.service.ChildrenInfoService;
import com.example.javaweb.service.ParentUseServeService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务评分控制器
 */
@Controller
@RequestMapping("/index")
public class ParentUseServeController {

    @Autowired
    ParentUseServeService parentUseServeService;

    /**
     * 获取指定老人的服务信息 用以评分
     * @param parentId
     * @return
     */
    @GetMapping("/getParentUseServeInfo")
    public @ResponseBody
    List<Map<Object, Object>> getParentUseServeInfo(@RequestParam Long parentId) {
        return parentUseServeService.getUse_serveInfo(parentId);
    }

    //保存评分信息
    @PostMapping("/saveParentUseServeGrade")
    public  @ResponseBody
    List<Map<Object,Object>>saveParentUseServe(@RequestBody ParentUseServe parentUseServe)
    {
        parentUseServeService.saveParentUseInfo(parentUseServe);
        return  getParentUseServeInfo(parentUseServe.getParentId());
    }


    //保存用户预约服务信息
    @PostMapping("/saveParentOrderInfo")
    public  Map saveParentOrderInfo(@RequestBody  ParentUseServe parentUseServe)
    {

        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        parentUseServe.setParentId(  Long.parseLong( userInfo.getUserid()));
        //保存预约信息
        parentUseServeService.saveParentOrderInfo(parentUseServe);
        Map message = new HashMap<String,String>();
        message.put("msg","预约成功！");
        return  message;
//        getAllServeInfo();
    }


}

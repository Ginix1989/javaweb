package com.example.javaweb.controller;

import com.example.javaweb.domain.ChildrenInfo;
import com.example.javaweb.securityExp.UserInfo;
import com.example.javaweb.service.ChildrenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 子女信息控制器
 */
@Controller
@RequestMapping("/index")
public class ChildrenInfoController {

    //注入子女信息服务
    @Autowired
    ChildrenInfoService childrenInfoService;

    @GetMapping("/getAllChildrenInfo")
    public @ResponseBody
    List<ChildrenInfo> getAllChildrenInfo() {
        return childrenInfoService.getAllChildrenInfo();
    }

    /**
     * 根据Id删除子女信息
     *
     * @param childrenId
     * @return
     */
    @GetMapping("/deleteChildrenInfoById")
    public @ResponseBody
    List<ChildrenInfo> deleteChildrenInfoById(@RequestParam Long childrenId) {
        childrenInfoService.deleteChildrenInfoById(childrenId);
        return getAllChildrenInfo();
    }

    /**
     * 保存子女信息并返回列表
     *
     * @param childrenInfo
     * @return
     */
    @PostMapping("/saveChildrenInfoEntity")
    public @ResponseBody
    List<ChildrenInfo> saveChildrenInfoEntity(@RequestBody ChildrenInfo childrenInfo) {
        childrenInfoService.saveChildrenByEntity(childrenInfo);

        return getAllChildrenInfo();
    }

    //获取当前子女用户信息并进行修改
    @GetMapping("/getChildrenForEidt")
    public @ResponseBody
    ChildrenInfo getChildrenForEidt() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userInfo.getChildrenInfo();
    }

    /**
     * 保存子女信息并返回保存信息
     *
     * @param childrenInfo
     * @return
     */
    @PostMapping("/saveChildrenForEidt")
    public @ResponseBody
    Map<String, String> saveChildrenForEidt(@RequestBody ChildrenInfo childrenInfo) {
        childrenInfoService.saveChildrenByEntity(childrenInfo);

        Map msg = new HashMap<String, String>();
        msg.put("msg", "保存成功");
        return msg;
    }


}

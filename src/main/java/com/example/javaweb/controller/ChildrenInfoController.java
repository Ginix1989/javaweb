package com.example.javaweb.controller;

import com.example.javaweb.domain.ChildrenInfo;
import com.example.javaweb.service.ChildrenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public  @ResponseBody
    List<ChildrenInfo> getAllChildrenInfo()
    {
        return childrenInfoService.getAllChildrenInfo();
    }

    /**
     * 根据Id删除子女信息
     * @param childrenId
     * @return
     */
    @GetMapping("/deleteChildrenInfoById")
    public  @ResponseBody
    List<ChildrenInfo> deleteChildrenInfoById(@RequestParam Long childrenId)
    {
        childrenInfoService.deleteChildrenInfoById(childrenId);
        return getAllChildrenInfo();
    }

    /**
     * 保存子女信息
     * @param childrenInfo
     * @return
     */
    @PostMapping("/saveChildrenInfoEntity")
    public @ResponseBody
    List<ChildrenInfo> saveChildrenInfoEntity(@RequestBody ChildrenInfo childrenInfo)
    {
        childrenInfoService.saveChildrenByEntity(childrenInfo);

        return getAllChildrenInfo();
    }
}

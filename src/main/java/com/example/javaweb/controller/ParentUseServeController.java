package com.example.javaweb.controller;

import com.example.javaweb.domain.ChildrenInfo;
import com.example.javaweb.domain.ParentUseServe;
import com.example.javaweb.domain.ServeInfo;
import com.example.javaweb.securityExp.UserInfo;
import com.example.javaweb.service.ChildrenInfoService;
import com.example.javaweb.service.ParentUseServeService;
import com.example.javaweb.service.ServeInfoService;
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
@Autowired
    ServeInfoService serveInfoService;
    /**
     * 获取指定老人的服务信息
     * @param
     * @return
     */
    @GetMapping("/getParentUseServeInfo")
    public @ResponseBody
    List<Map<Object, Object>> getParentUseServeInfo() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return parentUseServeService.getUse_serveInfo(userInfo.getUserid());
    }


    /**
     * 获取全部订单信息 服务信息
     * @param
     * @return
     */
    @GetMapping("/getAllUseServeOrderInfo")
    public @ResponseBody
    List<Map<Object, Object>> getAllUseServeOrderInfo() {
        //UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return parentUseServeService.getAllOrders();
    }




    /**
     * 获取指定老人的服务信息 用以评分
     * @param
     * @return
     */
    @GetMapping("/getParentUseServeInfoForGrade")
    public @ResponseBody
    List<Map<Object, Object>> getParentUseServeInfoForGrade() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return parentUseServeService.getUse_serveInfoForGrade(userInfo.getUserid());
    }



    /**
     * 获取全部订单信息 管理员用户CRUD 订单信息
     * @param
     * @return
     */
    @GetMapping("/getAllOrders")
    public @ResponseBody
    List<Map<Object, Object>> getAllOrders() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return parentUseServeService.getAllOrders();
    }


    //保存评分信息
    @PostMapping("/saveParentUseServeGrade")
    public  @ResponseBody
    List<Map<Object,Object>>saveParentUseServe(@RequestBody ParentUseServe parentUseServe)
    {
        System.out.println(parentUseServe.toString());
        parentUseServeService.saveParentUseInfo(parentUseServe);
        return  getParentUseServeInfoForGrade();
    }


    //保存用户预约服务信息
    @PostMapping("/saveParentOrderInfo")
    public @ResponseBody Map<String,String>saveParentOrderInfo(@RequestBody  ParentUseServe parentUseServe)
    {

        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        parentUseServe.setParentId(  userInfo.getUserid());
        //保存预约信息
        parentUseServeService.saveParentOrderInfo(parentUseServe);
        Map message = new HashMap<String,String>();
        message.put("msg","预约成功！");
        return  message;
//        getAllServeInfo();
    }


    /**
     * 根据日期查询服务信息
     * @param stratTime
     * @return
     */
    @GetMapping("/getUseServeInfoByStartTimeAndParentId")
  public @ResponseBody List<Map<Object,Object>> getUseServeInfoByStartTimeAndParentId(@RequestParam String startTime)
    {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return  parentUseServeService.getUseServeInfoByStartTimeAndParentId(userInfo.getUserid(),startTime);
    }


    /**
     * 根据日期查询全部服务信息
     * @param stratTime
     * @return
     */
    @GetMapping("/getUseServeOrderInfoByStartTimeAndParentId")
    public @ResponseBody List<Map<Object,Object>> getUseServeOrderInfoByStartTimeAndParentId(@RequestParam String startTime)
    {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return  parentUseServeService.getUseServeOrderInfoByStartTimeAndParentId(startTime);
    }


    /**
     * 获取指定老人的定制服务信息
     * @param
     * @return
     */
    @GetMapping("/getParentPersonalUseServeInfo")
    public @ResponseBody
    List<Map<Object, Object>> getUse_PersonalServeInfo() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return parentUseServeService.getUse_PersonalServeInfo(userInfo.getUserid(),"1");
    }

    /**
     * 根据日期查询定制服务信息
     * @param stratTime
     * @return
     */
    @GetMapping("/getPersonalUseServeInfoByStartTimeAndParentId")
    public @ResponseBody List<Map<Object,Object>> getPersonalUseServeInfoByStartTimeAndParentId(@RequestParam String startTime)
    {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return  parentUseServeService.getPersonalUseServeInfoByStartTimeAndParentId(userInfo.getUserid(),startTime);
    }


    /**
     *
     * @return
     */
    @PostMapping(value="/savePersionServeInfo")
    public  @ResponseBody  List<Map<Object, Object>>  savePersionalServeInfo(@RequestBody Map serveInfo)
    {

        System.out.println(serveInfo);
        ServeInfo sinfo = new ServeInfo(serveInfo.get("serveAddInfo").toString());
        sinfo.setIspersonal("1");
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        serveInfoService.SaveServeInfoReturnNewValue(sinfo);

        parentUseServeService.saveParentOrderInfo( new ParentUseServe(userInfo.getUserid(),sinfo.getId(),serveInfo.get("serveTimeInfo").toString(),"","-1"));


        return getUse_PersonalServeInfo();
    }



}

package com.example.javaweb.controller;

import com.example.javaweb.dao.MenuInfoMapper;
import com.example.javaweb.dao.ServeInfoMapper;
import com.example.javaweb.domain.ParentInfo;
import com.example.javaweb.domain.ParentUseServe;
import com.example.javaweb.domain.ServeInfo;
import com.example.javaweb.repository.MenuInfoRepository;
import com.example.javaweb.repository.ParentRegRepository;
import com.example.javaweb.repository.ParentUseServeRepository;
import com.example.javaweb.repository.ServerInfoRepositrory;
import com.example.javaweb.securityExp.UserInfo;
import com.example.javaweb.service.ServeInfoService;
import com.fasterxml.jackson.annotation.JsonMerge;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sound.midi.SoundbankResource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/index")
public class ServeInfoController {

    //服务项目信息
    @Autowired
    private ServeInfoService serveInfoService;


    @Autowired
    private ServerInfoRepositrory serverInfoRepositrory;
    @Autowired
    private ServeInfoMapper serveInfoMapper;


    @Autowired
    private MenuInfoMapper menuInfoMapper;//注入菜单查询模块

    @Autowired
    private ParentUseServeRepository parentUseServeRepository;//用户预约服务模块

    @Autowired
    private ParentRegRepository parentRegRepository;//用户信息（父母）查询修改模块

    @GetMapping
    public ModelAndView listServeInfo(Model model) {
        model.addAttribute("title", "信息展示");
        model.addAttribute("info", serverInfoRepositrory.findAll());

        return new ModelAndView("index", "ServerModel", model);
    }

    @GetMapping("/mapper")
    public ModelAndView listServeInfoMapper(Model model) {


        model.addAttribute("title", "信息展示");
        model.addAttribute("info", serveInfoMapper.getAllServerInfo());

        return new ModelAndView("main", "ServerModel", model);
    }



    //获取全部服务信息
@GetMapping("/getAllServeInfo")
    public @ResponseBody List<ServeInfo> getAllServeInfo() {
        List<ServeInfo> listServeinfo = serveInfoService.getAllServe();
        return  listServeinfo;
    }

    //获取全部护理服务信息
    @GetMapping("/getAllNursingServeInfo")
    public @ResponseBody List<ServeInfo> getAllNursingServeInfo() {
        List<ServeInfo> listServeinfo = serveInfoService.getAllNursingServeInfo();
        return  listServeinfo;
    }

    //获取制定护理服务信息
    @GetMapping("/getAllNursingServeInfoByType")
    public @ResponseBody List<ServeInfo> getAllNursingServeInfoByItem(@RequestParam String itemInfo) {
        List<ServeInfo> listServeinfo = serveInfoService.getAllNursingServeInfoByItem(itemInfo);
        return  listServeinfo;
    }

    //获取查询服务信息
    @GetMapping("/getAllServeInfoByType")
    public @ResponseBody List<ServeInfo> getAllServeInfoByType(@RequestParam String itemInfo) {
        List<ServeInfo> listServeinfo = serveInfoService.getAllServeByType(itemInfo);
        return  listServeinfo;
    }
    //修改用户（父母）信息
    @PostMapping("/saveParentInfo")
    public void  updateParentInfo(@RequestBody ParentInfo parentInfo)
    {
        parentRegRepository.save(parentInfo);
    }


    /**
     * deleteServeInfo
     * @param id  serveInfo id
     * @return
     */
    @GetMapping(value = "/deleteServeInfoById")
    public @ResponseBody   List<ServeInfo> deleteServeInfo(@RequestParam Long id,@RequestParam String serveType)
    {
        serveInfoService.deleteServeInfo(id);
        if(!serveType.equals("0"))
        {
            return  getAllNursingServeInfo();
        }else {
            return getAllServeInfo();
        }



    }

    /**
     *
     * @return
     */
    @GetMapping(value="/saveServeInfo")
    public  @ResponseBody List<ServeInfo> saveServeInfo(@RequestParam String serveInfo,@RequestParam String serveType)
    {
        if (!serveType.equals("0"))
        {
            serveInfoService.SaveServeInfo(serveInfo,serveType);
            return getAllNursingServeInfo();
        }
        else {
            serveInfoService.SaveServeInfo(serveInfo);
            return getAllServeInfo();
        }

    }










}

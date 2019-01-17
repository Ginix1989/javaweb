package com.example.javaweb.controller;

import com.example.javaweb.dao.MenuInfoMapper;
import com.example.javaweb.dao.ServeInfoMapper;
import com.example.javaweb.repository.MenuInfoRepository;
import com.example.javaweb.securityExp.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {


    @Autowired
    private MenuInfoMapper menuInfoMapper;//注入菜单查询模块

    @RequestMapping("/login")
    public String returnString() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError() {
        return "login-error";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }


    /**
     * 获取权限
     *
     * @param * @return
     */
    @GetMapping("/getAuthor")
    public @ResponseBody
    Map<String, Boolean> getParentAuthor() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Map<String, Boolean> Author = new HashMap<String, Boolean>();

        //管理员
        if (userInfo.getRole().equals("ROLE_ADMIN")) {
            Author.put("serveinfoAddShow", true);//服务信息添加
            Author.put("serveinfoDeleteShow", true);//删除
            Author.put("serveinfoOrderShow", false);//预约

            //老人信息管理 CRUD
            Author.put("isPass",true);//老人审核
            Author.put("CanDelete",true);//删除老人信息
            Author.put("CanEdit",true);//修改老人信息

        }
        //老人
        else if (userInfo.getRole().equals("ROLE_PARENT")) {
            Author.put("serveinfoAddShow", false);
            Author.put("serveinfoDeleteShow", false);
            Author.put("serveinfoOrderShow", true);
        }
        //子女
        else if(userInfo.getRole().equals("ROLE_CHILDREN"))
        {
            Author.put("serveinfoAddShow", false);
            Author.put("serveinfoDeleteShow", false);
            Author.put("serveinfoOrderShow", true);
        }
        //员工
        else
        {
            Author.put("serveinfoAddShow", true);
            Author.put("serveinfoDeleteShow", true);
            Author.put("serveinfoOrderShow", false);

            Author.put("isPass",false);//老人审核
            Author.put("CanDelete",false);//删除老人信息
            Author.put("CanEdit",false);//修改老人信息
        }


        return Author;
    }


    /**
     * 获取菜单
     *
     * @param model
     * @return
     */
    @GetMapping("index/menuinfo")
    public ModelAndView listMenuInfoMapper(Model model) {
        UserInfo sysuser = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //


        //管理员
        if (sysuser.getRole().equals("ROLE_ADMIN")) {

            model.addAttribute("menuinfo", menuInfoMapper.getAllMenu("'1','20','40','50','60','70','21','24','101'"));

        }
        //父母信息
        else if (sysuser.getRole().equals("ROLE_PARENT")) {
            model.addAttribute("menuinfo", menuInfoMapper.getAllMenu("'1','20','21','80','22','29','30','101'"));
        }
        //子女
        else if(sysuser.getRole().equals("ROLE_CHILDREN"))
        {
            model.addAttribute("menuinfo", menuInfoMapper.getAllMenu("'1','20','21','80','22','28','31','101'"));
        }
        //小区员工
        else
        {
            model.addAttribute("menuinfo", menuInfoMapper.getAllMenu("'1','20','60','70','21','24','101'"));
        }
        model.addAttribute("userloginname", sysuser.getGeneralname());

        return new ModelAndView("main", "MenuModel", model);

    }


}

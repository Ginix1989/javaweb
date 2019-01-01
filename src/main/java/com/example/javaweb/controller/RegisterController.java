package com.example.javaweb.controller;

import com.example.javaweb.domain.ParentInfo;
import com.example.javaweb.repository.ParentRegRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reg")
public class RegisterController {



    @Autowired
    ParentRegRepository  parentRegRepository;



    /**
     * processing ParentrRegisiter
     * and
     * redir
     * @param parentInfo
     * @return
     */
    @PostMapping()
    public ModelAndView registerUser(ParentInfo parentInfo) {

        //save ParentInfo
       // parentRegRepository.save(parentInfo);


        return new ModelAndView("redirect:/index/menuinfo");
    }



}

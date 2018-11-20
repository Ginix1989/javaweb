package com.example.javaweb.controller;

        import com.example.javaweb.dao.MenuInfoMapper;
        import com.example.javaweb.dao.ServeInfoMapper;
        import com.example.javaweb.domain.MenuInfo;
        import com.example.javaweb.repository.MenuInfoRepository;
        import com.example.javaweb.repository.ServerInfoRepositrory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/index")
public class ServeInfoController {

    @Autowired
    private ServerInfoRepositrory serverInfoRepositrory;
    @Autowired
    private ServeInfoMapper serveInfoMapper;
    @Autowired
    private MenuInfoRepository menuInfoRepository;

    @Autowired
    private MenuInfoMapper menuInfoMapper;//注入菜单查询模块




    @GetMapping
    public ModelAndView listServeInfo(Model model)
    {


        model.addAttribute("title","信息展示");
        model.addAttribute("info",serverInfoRepositrory.findAll());

//        MenuInfo menu = new MenuInfo(0L,"系统管理","glyphicon glyphicon-chevron-right pull-right");
//        menuInfoRepository.save(menu);
//
//        menuInfoRepository.save(new MenuInfo(menu.getId(),"系统信息","glyphicon glyphicon-info-sign"));
//        menuInfoRepository.save(new MenuInfo(menu.getId(),"管理员管理","glyphicon glyphicon-user"));
//        menuInfoRepository.save(new MenuInfo(menu.getId(),"日志管理","glyphicon glyphicon-list-alt"));
//        menuInfoRepository.save(new MenuInfo(menu.getId(),"退出","glyphicon glyphicon-off\""));

//        menuInfo = new MenuInfo(0L,"系统管理","glyphicon glyphicon-chevron-right pull-right");
//        menuInfoRepository.save(menuInfo);
//
//        menuInfoRepository.save(new MenuInfo(menuInfo.getId(),"系统信息","glyphicon glyphicon-info-sign"));
//        menuInfoRepository.save(new MenuInfo(menuInfo.getId(),"管理员管理","glyphicon glyphicon-user"));
//        menuInfoRepository.save(new MenuInfo(menuInfo.getId(),"日志管理","glyphicon glyphicon-list-alt"));
//        menuInfoRepository.save(new MenuInfo(menuInfo.getId(),"退出","glyphicon glyphicon-off"));

        return  new ModelAndView("index","ServerModel",model);
    }
    @GetMapping("/mapper")
    public ModelAndView listServeInfoMapper(Model model)
    {


        model.addAttribute("title","信息展示");
        model.addAttribute("info",serveInfoMapper.getAllServerInfo());

        return  new ModelAndView("main","ServerModel",model);
    }

    /**
     *  获取菜单
     * @param model
     * @return
     */
    @GetMapping("/menuinfo")
    public  ModelAndView listMenuInfoMapper(Model model)
    {

        model.addAttribute("menuinfo",menuInfoMapper.getAllMenu());
        return new ModelAndView("main","MenuModel",model);

    }
}

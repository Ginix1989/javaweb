package com.example.javaweb.controller;

        import com.example.javaweb.dao.ServeInfoMapper;
        import com.example.javaweb.domain.ServeInfo;
        import com.example.javaweb.repository.ServerInfoRepositrory;
        import org.apache.catalina.util.ServerInfo;
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

    @GetMapping
    public ModelAndView listServeInfo(Model model)
    {

        serverInfoRepositrory.save(new ServeInfo("清洁"));
        serverInfoRepositrory.save(new ServeInfo("测血压"));
        serverInfoRepositrory.save(new ServeInfo("修理电器"));

        model.addAttribute("title","信息展示");
        model.addAttribute("info",serverInfoRepositrory.findAll());

        return  new ModelAndView("index","ServerModel",model);
    }
    @GetMapping("/mapper")
    public ModelAndView listServeInfoMapper(Model model)
    {

        serverInfoRepositrory.save(new ServeInfo("清洁"));
        serverInfoRepositrory.save(new ServeInfo("测血压"));
        serverInfoRepositrory.save(new ServeInfo("修理电器"));

        model.addAttribute("title","信息展示");
        model.addAttribute("info",serveInfoMapper.getAllServerInfo());

        return  new ModelAndView("index","ServerModel",model);
    }

}

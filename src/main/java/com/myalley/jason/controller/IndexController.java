package com.myalley.jason.controller;

import com.myalley.jason.dao.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/index")
    public String index(Model model) throws Exception {
        model.addAttribute("errCode", indexService.json1());
        model.addAttribute("errMsg", indexService.json2());
        model.addAttribute("both", indexService.json3());
        return "/index";
    }

}

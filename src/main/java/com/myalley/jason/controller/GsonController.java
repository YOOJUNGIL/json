package com.myalley.jason.controller;

import com.myalley.jason.dao.service.GsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GsonController {

    @Autowired
    private GsonService gsonService;

    @RequestMapping("/gson")
    public String gson(Model model) throws Exception {
        model.addAttribute("req", gsonService.gson1());
        return "/gson";
    }

}

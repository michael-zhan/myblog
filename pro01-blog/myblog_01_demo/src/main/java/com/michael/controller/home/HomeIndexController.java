package com.michael.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeIndexController {

    @RequestMapping("/testIndex")
    public String index(){
        System.out.println("indexing......");
        return "index";
    }
}

package com.my.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mgiec on 9/7/2016.
 */

@Controller
public class HelloController {
    @RequestMapping("/")
    public String Index(){
        return "index";
    }
}

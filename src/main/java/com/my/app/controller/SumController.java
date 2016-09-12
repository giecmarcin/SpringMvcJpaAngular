package com.my.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mgiec on 9/7/2016.
 */
@Controller
public class SumController {
    @RequestMapping("/sum")
    public String sum(@RequestParam("a") int a, @RequestParam("b") int b, ModelMap map){
        int sum = a +b;
        map.addAttribute("sum", sum);
        return "sum";
    }
}

package com.didispace.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HotelViewController {
    @RequestMapping("/sayhello.html")
    public @ResponseBody String say(){
        return "Hello without view.";
    }
}

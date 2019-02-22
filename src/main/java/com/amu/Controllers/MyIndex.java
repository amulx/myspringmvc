package com.amu.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyIndex {

    @RequestMapping("index")
    public @ResponseBody String loadIndex(){
        return "this is my first spring";
    }
}

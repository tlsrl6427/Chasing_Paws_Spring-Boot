package com.chasingpaws.sb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/story")
public class StoryController {

    @GetMapping("/begin")
    public String begin(){
        return "story/begin";
    }
}

package com.example.restservice.conroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class BuildController {
    @GetMapping(path = "/aaa")
    @ResponseBody
    public String getBuild() {
        return "Build 0.0.1";
    }
}

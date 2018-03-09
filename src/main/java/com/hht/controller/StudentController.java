package com.hht.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hht on 2017/8/3.
 */
@RequestMapping("/student")
@RestController
@Slf4j
public class StudentController {

    @RequestMapping("/list")
    public Object getStudentList(){
        return "";
    }

}

package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/registration")

public class RegistrationController {

    @GetMapping()
    String healthCheck(){

        return "Service is running";
    }








}

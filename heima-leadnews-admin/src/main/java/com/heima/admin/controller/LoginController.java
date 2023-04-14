package com.heima.admin.controller;

import com.heima.admin.service.AdUserService;
import com.heima.apis.admin.LoginControllerApi;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController implements com.heima.apis.admin.LoginControllerApi {
    @Autowired
    private AdUserService adUserService;

    @Override
    @PostMapping("/in")
    public ResponseResult login(@RequestBody AdUserDto adUserDto) {
        return adUserService.login(adUserDto);
    }
}
package com.example.backend_422.controller;


import com.example.backend_422.common.Result;
import com.example.backend_422.service.UserService;
import com.example.backend_422.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xwyzsn
 * @since 2023-04-22
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("getall")
    public Result getAll(){
        return userService.getAll();
    }

    @PostMapping("login")
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.login(username,password);
    }

}

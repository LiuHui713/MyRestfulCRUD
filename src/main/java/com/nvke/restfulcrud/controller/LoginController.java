package com.nvke.restfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author nvke
 * @create 2020-07-17-10:00
 */
@Controller
public class LoginController {

    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    //以前的写法需要在requestmapping中指定请求方式post

    @PostMapping(value = "/user/login")//restful风格写法中，可直接用postmapping接收请求
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){//用requestpara标注的属性一旦没提交，就会报错
        //登录成功
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser",username);
           //防止表单重复提交，可以重定向到后台主页
            return "redirect:/main.html";//冒号后面不要加空格
        }else {
            //登录失败
            map.put("msg","用户名密码错误");
            return "login";
        }

    }
}

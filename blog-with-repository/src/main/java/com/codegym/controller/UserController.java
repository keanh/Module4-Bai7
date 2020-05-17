package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/")
    public ModelAndView index(Pageable pageable){
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/security/index");
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }

    @GetMapping("/user")
    public String user(Principal principal) {
//        System.out.println(principal.getName());
        return "/security/user";
    }

    @GetMapping("/admin")
    public String admin() {
        SecurityContext context = SecurityContextHolder.getContext();
//        System.out.println(context.getAuthentication().getName());
        return "/security/admin";
    }
}

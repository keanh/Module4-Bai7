package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private BlogService blogService;

    public String getPrincipal(){
        String email = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails){
            email =((UserDetails) principal).getUsername();
        }else {
            email = principal.toString();
        }
        return email;
    }

    @GetMapping(value = "/")
    public ModelAndView HomePage(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/security-db/welcome");
        modelAndView.addObject("user",getPrincipal());
        Page<Blog> blogs = blogService.findAll(pageable);
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }

    @GetMapping(value = "/admin")
    public ModelAndView adminPage(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/security-db/admin");
        modelAndView.addObject("user",getPrincipal());
        return modelAndView;
    }
    @GetMapping(value = "/user")
    public ModelAndView userPage(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/security-db/user");
        Page<Blog> blogs = blogService.findAll(pageable);
        modelAndView.addObject("blogs",blogs);
        modelAndView.addObject("user",getPrincipal());
        return modelAndView;
    }

    @GetMapping(value = "/Access_Denied")
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "/security-db/accessDenied";
    }
//    @GetMapping("/")
//    public ModelAndView index(Pageable pageable){
//        Page<Blog> blogs = blogService.findAll(pageable);
//        ModelAndView modelAndView = new ModelAndView("/security/index");
//        modelAndView.addObject("blogs",blogs);
//        return modelAndView;
//    }
//
//    @GetMapping("/user")
//    public String user(Principal principal) {
////        System.out.println(principal.getName());
//        return "/security/user";
//    }
//
//    @GetMapping("/admin")
//    public String admin() {
//        SecurityContext context = SecurityContextHolder.getContext();
////        System.out.println(context.getAuthentication().getName());
//        return "/security/admin";
//    }
}

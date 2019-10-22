package com.damon.fsd.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.damon.fsd.entity.SysUser;
import com.damon.fsd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @PostMapping(value = "/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:login";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute SysUser sysUser, Model model, HttpServletRequest request) {
        if(StringUtils.isEmpty(sysUser.getUsername())){
            model.addAttribute("msg","User name can not be empty.");
            return "register";
        }
        if(StringUtils.isEmpty(sysUser.getEmail())){
            model.addAttribute("msg","Email name can not be empty.");
            return "register";
        }
        if(!Pattern.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", sysUser.getEmail())){
            model.addAttribute("msg","email error");
            return "register";
        }
        if(StringUtils.isEmpty(sysUser.getName())){
            model.addAttribute("msg","Name can not be empty.");
            return "register";
        }
        if(StringUtils.isEmpty(sysUser.getPassword())){
            model.addAttribute("msg","Password name can not be empty.");
            return "register";
        }
        String s = request.getSession().getAttribute("CHECK_CODE").toString();
        if (StringUtils.isEmpty(sysUser.getKaptcha()) || !s.equals(sysUser.getKaptcha())) {
            model.addAttribute("msg","Verification code error");
            return "register";
        }

        SysUser existUser = userService.loadUserByUsername(sysUser.getUsername());
        if(existUser != null){
            model.addAttribute("msg","User name exist");
            return "register";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        userService.register(sysUser);
        model.addAttribute("msg","Register successful");
        return "register";
    }

    @GetMapping(value = "/register")
    public String toRegister(Model model) {
        model.addAttribute("sysUser",new SysUser());
        return "register";
    }

    @PostMapping(value = "/modify")
    public String modify(@ModelAttribute SysUser sysUser, Model model, HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        SysUser _sysUser = userService.loadUserByUsername(userDetails.getUsername());
        if(StringUtils.isEmpty(sysUser.getEmail())){
            model.addAttribute("msg","Email name can not be empty.");
            model.addAttribute("sysUser",userService.loadUserByUsername(userDetails.getUsername()));
            return "modify";
        }
        if(StringUtils.isEmpty(sysUser.getName())){
            model.addAttribute("msg","Name can not be empty.");
            model.addAttribute("sysUser",userService.loadUserByUsername(userDetails.getUsername()));
            return "modify";
        }
        if(StringUtils.isEmpty(sysUser.getNewPassword())){
            model.addAttribute("msg","Password name can not be empty.");
            model.addAttribute("sysUser",userService.loadUserByUsername(userDetails.getUsername()));
            return "modify";
        }

        String s = request.getSession().getAttribute("CHECK_CODE").toString();
        if (StringUtils.isEmpty(sysUser.getKaptcha()) || !s.equals(sysUser.getKaptcha())) {
            model.addAttribute("msg","Verification code error");
            model.addAttribute("sysUser",userService.loadUserByUsername(userDetails.getUsername()));
            return "modify";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        _sysUser.setEmail(sysUser.getEmail());
        _sysUser.setName(sysUser.getName());
        _sysUser.setPassword(passwordEncoder.encode(sysUser.getNewPassword()));
        userService.updateUser(_sysUser);
        model.addAttribute("msg","modify successful");
        model.addAttribute("sysUser",userService.loadUserByUsername(userDetails.getUsername()));
        return "modify";
    }

    @GetMapping(value = "/modify")
    public String toModify(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        SysUser sysUser = userService.loadUserByUsername(userDetails.getUsername());
        model.addAttribute("sysUser", sysUser);
        return "modify";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }


    @GetMapping(value = "/admin/info")
    public String info() {
        return "info";
    }


    @GetMapping("/captcha.jpg")
    public void applyCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        request.getSession().setAttribute("CHECK_CODE", text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }
}

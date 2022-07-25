package com.codestates.springsecurity.index;

import com.codestates.springsecurity.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String join() {
        return "joinForm";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/hello")
//    @ResponseBody
    public String hello(HttpServletResponse response, HttpServletRequest request) {

//        Member member = new Member();
//        member.setUsername("thom");
//        member.setRole("nothing");
//        member.setCreatedAt(LocalDateTime.now());
//        member.setPassword("1234");
        response.addHeader("test", "Test");

        log.info("request URI: {}", request.getRequestURI());
        System.out.println("content type : " + response.getContentType());
        System.out.println("buffer size : " + response.getBufferSize());
        System.out.println("hello");

//        return new ResponseEntity<>(member, HttpStatus.OK);
        return "hello";
    }

}

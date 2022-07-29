package com.codestates.springsecurity.index;

import com.codestates.springsecurity.config.security.PrincipalDetails;
import com.codestates.springsecurity.member.Member;
import com.codestates.springsecurity.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {


    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

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

    @PostMapping("/join")
    public @ResponseBody String join(@RequestBody Member member) {

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole("ROLE_USER");
        memberRepository.save(member);

        return "signed up";
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

    @GetMapping("/login-test")
    public @ResponseBody String loginTest(Authentication authentication) {
        log.info("login test to get some incoming information after authentication with oauth");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return principalDetails.getMember().toString();
    }
}

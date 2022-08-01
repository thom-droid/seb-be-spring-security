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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
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

    @GetMapping("/login-test-with-annotation")
    @ResponseBody
    public String loginTestWithAnnotation(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("get member info with annotation after authentication");
        log.info("principal details: {}", principalDetails.getMember().toString());
        return principalDetails.getMember().toString();
    }


    @GetMapping("/login-test-after-oauth")
    @ResponseBody
    public String loginTestAfterOauth(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User) {
        log.info("oauth login test");
        OAuth2User oAuth2User1 = (OAuth2User) authentication.getPrincipal();
        log.info("authentication info from OAuth2User : {}", oAuth2User1.toString());
        log.info("oauth2User info : {}", oAuth2User.toString());

        return "testing";

    }
}

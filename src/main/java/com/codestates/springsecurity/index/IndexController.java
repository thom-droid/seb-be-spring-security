package com.codestates.springsecurity.index;

import com.codestates.springsecurity.member.Member;
import com.codestates.springsecurity.member.MemberRepository;
import com.codestates.springsecurity.member.dto.MemberPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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
    @ResponseBody
    public Long signup(MemberPostDto memberPostDto) {
        Member member = new Member(memberPostDto.getUsername(), passwordEncoder.encode(memberPostDto.getPassword()));
        return memberRepository.save(member).getId();
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}

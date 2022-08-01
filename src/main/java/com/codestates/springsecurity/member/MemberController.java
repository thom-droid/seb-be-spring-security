package com.codestates.springsecurity.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping
@Controller
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String join() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(Member member) {
        member.setRole("ROLE_USER");
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        memberRepository.save(member);

        return "redirect:/login";
    }

    @PostMapping("/join-with-different-password-encoding")
    public String joinWithDifferentPasswordEncoding(Member member) {
        member.setRole("ROLE_USER");
        log.info("setting encoder to pbkdf12");

        DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) passwordEncoder;
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
        member.setPassword(delegatingPasswordEncoder.encode(member.getPassword()));

        memberRepository.save(member);
        return "redirect:/login";
    }
}

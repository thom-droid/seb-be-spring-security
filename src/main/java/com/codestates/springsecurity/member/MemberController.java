package com.codestates.springsecurity.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public String join(Member member) {
        member.setRole("ROLE_USER");
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        memberRepository.save(member);

        return "redirect:/login";
    }

}

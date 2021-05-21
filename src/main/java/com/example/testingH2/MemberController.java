package com.example.testingH2;

import com.example.testingH2.entity.Member;
import com.example.testingH2.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Member saveMember(Member member) throws Exception{
        return  memberService.createMember(member);
    }

}

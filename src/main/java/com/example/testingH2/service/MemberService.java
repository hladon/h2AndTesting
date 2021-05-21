package com.example.testingH2.service;

import com.example.testingH2.entity.Member;
import com.example.testingH2.repository.MemberRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;

@Service
@Validated
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(@Valid Member member) throws Exception {
        if ((LocalDate.now().getYear() - member.getBirtDate().getYear()) < 18)
            throw new Exception("invalid date");
        Member returnMember = memberRepository.save(member);
        if (returnMember != null)
            return returnMember;
        throw new Exception("Save problem");
    }

    public boolean checkIsMemberExist(Member member) throws Exception {
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnorePaths("memberId");
        Example<Member> example = Example.of(member, modelMatcher);
        if (!memberRepository.exists(example))
            return false;
        return true;
    }

}

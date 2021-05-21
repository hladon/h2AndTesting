package com.example.testingH2.service;

import com.example.testingH2.entity.Member;
import com.example.testingH2.repository.MemberRepository;
import com.example.testingH2.service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemberServiceTest {

    private MemberRepository memberRepository=Mockito.mock(MemberRepository.class);

    private MemberService memberService = new MemberService(memberRepository);

    @Test
    public void whenCreateMemberReturnMember() throws Exception {
        Member initial = createIncomeMember();
        Member expected = createIncomeMember();
        expected.setMemberId(1);

        Mockito.when(memberRepository.save(initial)).thenReturn(expected);

        Member actual = memberService.createMember(initial);

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void whenCreateMemberMemberYounger18ThrowException() throws Exception {
        Member initial = createIncomeMember();
        initial.setBirtDate(LocalDate.parse("2010-05-05"));

        Exception exception = assertThrows(
                Exception.class,
                () -> memberService.createMember(initial));

        assertTrue(exception.getMessage().contains("invalid date"));
    }

    @Test
    public void whenCreateMemberMemberRepositoryReturnNullThrowException() throws Exception {
        Member initial = createIncomeMember();

        Mockito.when(memberRepository.save(initial)).thenReturn(null);
        Exception exception = assertThrows(
                Exception.class,
                () -> memberService.createMember(initial));

        assertTrue(exception.getMessage().contains("Save problem"));
    }

    public static Member createIncomeMember() {
        Member member = new Member();
        member.setUserName("test");
        member.setPassword("test");
        member.setEmail("test@google.com");
        member.setFullName("test test");
        member.setAddress("Kiyv");
        member.setPhone("+2(095)345-34-34");
        member.setBirtDate(LocalDate.parse("2000-05-20"));
        member.setGender("MALE");
        return member;
    }
}

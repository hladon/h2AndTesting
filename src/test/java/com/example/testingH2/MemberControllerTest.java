package com.example.testingH2;

import com.example.testingH2.entity.Member;
import com.example.testingH2.repository.MemberRepository;
import com.example.testingH2.service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(ValidationAutoConfiguration.class)
public class MemberControllerTest {

    @MockBean
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberController memberController;

    @Test
    public void whenSaveMemberReturnMemmber() throws Exception {
        Member initial=createIncomeMember();
        Member expected=createIncomeMember();
        expected.setMemberId(1);
        Mockito.when(memberRepository.save(initial)).thenReturn(expected);
        Member actual= memberController.saveMember(initial);

        assertThat(actual).isEqualTo(expected);


    }

    @Test
    public void whenSaveMemberWithNotValidNameThrowException() throws Exception {
        Member initial=createIncomeMember();
        initial.setUserName(null);
        Member expected=createIncomeMember();
        expected.setMemberId(1);
        Mockito.when(memberRepository.save(initial)).thenReturn(expected);
        Member actual= memberController.saveMember(initial);
        assertThat(actual).isEqualTo(expected);
    }

    private Member createIncomeMember(){
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

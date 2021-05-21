package com.example.testingH2.service;

import com.example.testingH2.entity.Comment;
import com.example.testingH2.entity.Member;
import com.example.testingH2.entity.Product;
import com.example.testingH2.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class CommentServiceTest {

    private MemberService memberService= Mockito.mock(MemberService.class);
    private CommentRepository commentRepository=Mockito.mock(CommentRepository.class);
    private ProductService productService=Mockito.mock(ProductService.class);
    private CommentService commentService=new CommentService(commentRepository,productService,memberService);


    @Test
    public void whenAddCommentReturnTrue() throws Exception{
        Member member=MemberServiceTest.createIncomeMember();
        Product product=ProductServiceTest.createProduct();
        Mockito.when(memberService.checkIsMemberExist(member)).thenReturn(true);
        Mockito.when(productService.checkIsProductExist(product)).thenReturn(true);
        Mockito.when(commentRepository.save(any(Comment.class))).then(AdditionalAnswers.returnsFirstArg());

        String incomeMessage="message";
        assertTrue(commentService.addComment(product,member,incomeMessage));
    }

    @Test
    public void whenAddCommentWithoutMessageReturnFalse() throws Exception{
        Member member=MemberServiceTest.createIncomeMember();
        Product product=ProductServiceTest.createProduct();
        Mockito.when(memberService.checkIsMemberExist(member)).thenReturn(true);
        Mockito.when(productService.checkIsProductExist(product)).thenReturn(true);
        Mockito.when(commentRepository.save(any(Comment.class))).then(AdditionalAnswers.returnsFirstArg());

        String incomeMessage="";

        assertFalse(commentService.addComment(product,member,incomeMessage));
    }

}

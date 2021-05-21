package com.example.testingH2.service;

import com.example.testingH2.entity.Comment;
import com.example.testingH2.entity.Member;
import com.example.testingH2.entity.Product;
import com.example.testingH2.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private ProductService productService;
    private MemberService memberService;

    public CommentService(CommentRepository commentRepository, ProductService productService, MemberService memberService) {
        this.commentRepository = commentRepository;
        this.productService = productService;
        this.memberService = memberService;
    }

    public boolean addComment(Product product, Member member, String message) throws Exception {
        Comment comment = new Comment();
        if (!memberService.checkIsMemberExist(member))
            throw new Exception("member not exist");
        if(!productService.checkIsProductExist(product))
            throw new Exception("product not exist");
        comment.setMemberId(member);
        comment.setProductId(product);
        comment.setMessage(message);
        if (message == null || message.length() > 150 || message.isEmpty())
            return false;
        if (commentRepository.save(comment) != null)
            return true;
        return false;
    }




}

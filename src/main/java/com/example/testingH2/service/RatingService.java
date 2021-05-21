package com.example.testingH2.service;

import com.example.testingH2.entity.Member;
import com.example.testingH2.entity.Product;
import com.example.testingH2.entity.Rating;
import com.example.testingH2.repository.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private RatingRepository ratingRepository;
    private MemberService memberService;
    private ProductService productService;

    public RatingService(RatingRepository ratingRepository, MemberService memberService, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.memberService = memberService;
        this.productService = productService;
    }

    public boolean addRate(Product product, Member member, int rate) throws Exception {
        Rating rating = new Rating();
        memberService.checkIsMemberExist(member);
        productService.checkIsProductExist(product);
        if (rate > 5 || rate < 0)
            return false;
        rating.setProductId(product);
        rating.setMemberId(member);
        if (ratingRepository.save(rating) != null)
            return true;
        return false;

    }
}

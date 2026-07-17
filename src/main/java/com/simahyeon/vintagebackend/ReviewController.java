package com.simahyeon.vintagebackend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public ReviewController(ReviewRepository reviewRepository, StoreRepository storeRepository) {
        this.reviewRepository = reviewRepository;
        this.storeRepository = storeRepository;
    }

    @GetMapping("/api/stores/{storeId}/reviews")
    public List<Review> getReviewsByStore(@PathVariable Long storeId) {
        return reviewRepository.findByStoreId(storeId);
    }

    @PostMapping("/api/stores/{storeId}/reviews")
    public Review createReview(@PathVariable Long storeId, @RequestBody Review review) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("매장을 찾을 수 없습니다: " + storeId));
        review.setStore(store);
        return reviewRepository.save(review);
    }
}
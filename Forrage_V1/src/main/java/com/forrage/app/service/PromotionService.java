package com.forrage.app.service;

import com.forrage.app.model.Promotion;
import com.forrage.app.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public Optional<Promotion> getActivePromotion() {
        return promotionRepository.findFirstByActifTrue();
    }
}

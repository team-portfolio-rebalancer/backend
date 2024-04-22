package com.portfolio.rebalancer.application;

import com.portfolio.rebalancer.dto.request.CategoryRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    
    @Transactional
    public Long save(final Long userId, final CategoryRequest request) {
        return null;
    }
}

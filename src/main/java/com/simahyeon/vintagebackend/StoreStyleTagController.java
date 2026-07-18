package com.simahyeon.vintagebackend;

import com.simahyeon.vintagebackend.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/stores/{storeId}/tags")
public class StoreStyleTagController {

    private final StoreRepository storeRepository;
    private final StyleTagRepository styleTagRepository;
    private final StoreStyleTagRepository storeStyleTagRepository;

    public StoreStyleTagController(StoreRepository storeRepository,
                                   StyleTagRepository styleTagRepository,
                                   StoreStyleTagRepository storeStyleTagRepository) {
        this.storeRepository = storeRepository;
        this.styleTagRepository = styleTagRepository;
        this.storeStyleTagRepository = storeStyleTagRepository;
    }

    @PostMapping("/{tagId}")
    public StoreStyleTag addTagToStore(@PathVariable Long storeId, @PathVariable Long tagId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("매장을 찾을 수 없습니다: " + storeId));
        StyleTag styleTag = styleTagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("태그를 찾을 수 없습니다: " + tagId));

        return storeStyleTagRepository.save(new StoreStyleTag(store, styleTag));
    }
}
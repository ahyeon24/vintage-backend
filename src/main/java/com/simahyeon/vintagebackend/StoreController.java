package com.simahyeon.vintagebackend;

import com.simahyeon.vintagebackend.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class StoreController {

    private final StoreRepository storeRepository;
    private final StoreStyleTagRepository storeStyleTagRepository;

    public StoreController(StoreRepository storeRepository,
                           StoreStyleTagRepository storeStyleTagRepository) {
        this.storeRepository = storeRepository;
        this.storeStyleTagRepository = storeStyleTagRepository;
    }

    @GetMapping("/api/stores")
    public List<StoreResponse> getAllStores() {
        return storeRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/stores/{id}")
    public StoreResponse getStoreById(@PathVariable Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("매장을 찾을 수 없습니다: " + id));
        return toResponse(store);
    }

    @PostMapping("/api/stores")
    public Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    private StoreResponse toResponse(Store store) {
        List<String> tagNames = storeStyleTagRepository.findByStoreId(store.getId()).stream()
                .map(sst -> sst.getStyleTag().getName())
                .collect(Collectors.toList());
        return new StoreResponse(store, tagNames);
    }
}
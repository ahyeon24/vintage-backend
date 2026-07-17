package com.simahyeon.vintagebackend;

import com.simahyeon.vintagebackend.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class StoreController {

    private final StoreRepository storeRepository;

    public StoreController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @GetMapping("/api/stores")
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @GetMapping("/api/stores/{id}")
    public Store getStoreById(@PathVariable Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("매장을 찾을 수 없습니다: " + id));
    }

    @PostMapping("/api/stores")
    public Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }
}
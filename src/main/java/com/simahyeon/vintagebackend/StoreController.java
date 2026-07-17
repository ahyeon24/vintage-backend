package com.simahyeon.vintagebackend;

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
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        return storeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/stores")
    public Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }
}
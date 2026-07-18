package com.simahyeon.vintagebackend;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoreStyleTagRepository extends JpaRepository<StoreStyleTag, Long> {
    List<StoreStyleTag> findByStoreId(Long storeId);
}
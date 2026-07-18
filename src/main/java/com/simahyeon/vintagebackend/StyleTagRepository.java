package com.simahyeon.vintagebackend;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StyleTagRepository extends JpaRepository<StyleTag, Long> {
    Optional<StyleTag> findByName(String name);
}
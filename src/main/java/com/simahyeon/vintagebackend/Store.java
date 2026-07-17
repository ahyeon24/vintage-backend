package com.simahyeon.vintagebackend;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Boolean hasCustomRepair;

    private Boolean isUnmanned;

    @Enumerated(EnumType.STRING)
    private PriceTier priceTier;

    private String address;

    private Double latitude;

    private Double longitude;

    private String openHours;

    private String closedDays;

    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public enum PriceTier {
        LOW,    // 저가
        MID,    // 중가
        HIGH    // 고가
    }

    public enum StoreStatus {
        PUBLISHED,       // 게시중
        PENDING_REVIEW,  //제보검수중
        HIDDEN           //비공개
    }
}
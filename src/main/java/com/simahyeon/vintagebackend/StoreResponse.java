package com.simahyeon.vintagebackend;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class StoreResponse {

    private final Long id;
    private final String name;
    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final String openHours;
    private final String closedDays;
    private final Store.PriceTier priceTier;
    private final Boolean isUnmanned;
    private final Boolean hasCustomRepair;
    private final Store.StoreStatus status;
    private final LocalDateTime createdAt;
    private final List<String> styleTags;

    public StoreResponse(Store store, List<String> styleTags) {
        this.id = store.getId();
        this.name = store.getName();
        this.address = store.getAddress();
        this.latitude = store.getLatitude();
        this.longitude = store.getLongitude();
        this.openHours = store.getOpenHours();
        this.closedDays = store.getClosedDays();
        this.priceTier = store.getPriceTier();
        this.isUnmanned = store.getIsUnmanned();
        this.hasCustomRepair = store.getHasCustomRepair();
        this.status = store.getStatus();
        this.createdAt = store.getCreatedAt();
        this.styleTags = styleTags;
    }
}
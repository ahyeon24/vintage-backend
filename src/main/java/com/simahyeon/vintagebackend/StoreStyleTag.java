package com.simahyeon.vintagebackend;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_style_tags")
@Getter
@Setter
@NoArgsConstructor
public class StoreStyleTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_tag_id", nullable = false)
    private StyleTag styleTag;

    public StoreStyleTag(Store store, StyleTag styleTag) {
        this.store = store;
        this.styleTag = styleTag;
    }
}
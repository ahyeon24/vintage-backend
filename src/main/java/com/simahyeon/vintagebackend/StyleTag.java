package com.simahyeon.vintagebackend;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "style_tags")
@Getter
@Setter
@NoArgsConstructor
public class StyleTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public StyleTag(String name) {
        this.name = name;
    }
}
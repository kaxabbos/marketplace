package com.marketplace.category;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "category_g")
    @SequenceGenerator(name = "category_g", sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    private String name;


}

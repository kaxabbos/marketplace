package com.marketplace.product.img;

import com.marketplace.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductImg {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_g")
    @SequenceGenerator(name = "product_g", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    @Column(length = 1000)
    private String img;

    @ManyToOne
    private Product product;

    public ProductImg(String img, Product product) {
        this.img = img;
        this.product = product;
    }
}

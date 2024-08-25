package com.marketplace.product;

import com.marketplace.appUser.AppUser;
import com.marketplace.category.Category;
import com.marketplace.product.img.ProductImg;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_g")
    @SequenceGenerator(name = "product_g", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String bind;
    private int count;
    private float price;

    @Column(length = 5000)
    private String description;

    @ManyToOne
    private Category category;
    @ManyToOne
    private AppUser owner;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImg> imgs = new ArrayList<>();

    public Product(String name, String bind, int count, float price, String description, Category category) {
        this.name = name;
        this.bind = bind;
        this.count = count;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public void set(String name, String bind, int count, float price, String description, Category category) {
        this.name = name;
        this.bind = bind;
        this.count = count;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public List<String> getImgsString() {
        return imgs.stream().map(ProductImg::getImg).toList();
    }
}

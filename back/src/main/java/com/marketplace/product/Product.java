package com.marketplace.product;

import com.marketplace.appUser.AppUser;
import com.marketplace.category.Category;
import com.marketplace.enums.ProductStatus;
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

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.WAITING;

    @ManyToOne
    private Category category;
    @ManyToOne
    private AppUser owner;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImg> imgs = new ArrayList<>();

    public Product(String name, String bind, int count, float price, String description) {
        this.name = name;
        this.bind = bind;
        this.count = count;
        this.price = price;
        this.description = description;
    }

    public void set(Product product) {
        this.name = product.getName();
        this.bind = product.getBind();
        this.count = product.getCount();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }

    public List<String> getImgsString() {
        return imgs.stream().map(ProductImg::getImg).toList();
    }

    public String getImg() {
        if (imgs.isEmpty()) {
            return "/img/no_img.png";
        }
        return imgs.get(0).getImg();
    }
}

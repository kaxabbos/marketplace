package com.marketplace.ordering;

import com.marketplace.appUser.AppUser;
import com.marketplace.enums.OrderingStatus;
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
public class Ordering {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ordering_g")
    @SequenceGenerator(name = "ordering_g", sequenceName = "ordering_seq", allocationSize = 1)
    private Long id;

    private int count;
    private float price = 0;

    @Enumerated(EnumType.STRING)
    private OrderingStatus status = OrderingStatus.WAITING;

    @ManyToOne
    private Product product;
    @ManyToOne
    private AppUser owner;

    public Ordering(int count, Product product, AppUser owner) {
        this.count = count;
        this.product = product;
        this.owner = owner;
    }

    public float getTotal() {
        if (price == 0) {
            return count * product.getPrice();
        }
        return count * price;
    }
}

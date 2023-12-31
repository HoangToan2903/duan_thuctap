package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private Integer productID;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    @Column(name = "productName", columnDefinition = "nvarchar(100) not null")
    private String productName;

    @Column(name = "img")
    private String img;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "unit", nullable = false)
    private String unit;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "description", columnDefinition = "nvarchar(300)")
    private String description;

    @Column(name = "deleted")
    private boolean deleted = false;

    @Transient
    public String getPhotosImagePath() {
        if (img == null || productID == null) return null;

        return "/images/" + productID + "/" + img;
    }
}

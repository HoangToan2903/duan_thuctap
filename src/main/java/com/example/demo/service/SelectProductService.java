package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectProductService {
    @Autowired private ProductRepository repo;

    public List<Product> selectAll(){
        return (List<Product>) repo.findProduct();
    }

    public List<Product> findProductByName(String searchProductName) {
        return repo.findByProductNameContainingIgnoreCase(searchProductName);
    }
}

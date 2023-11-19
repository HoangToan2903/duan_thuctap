package com.example.demo.service;

import com.example.demo.model.PurchaseOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
    List<PurchaseOrder> save(List<PurchaseOrder> list);

    List<Integer> getProductOfOrder(Long orderID);

    String sumProductOrder(int productID);
}

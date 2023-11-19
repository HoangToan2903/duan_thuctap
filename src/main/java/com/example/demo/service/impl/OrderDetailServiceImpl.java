package com.example.demo.service.impl;


import com.example.demo.model.PurchaseOrder;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<PurchaseOrder> save(List<PurchaseOrder> orderDetailList){
        return orderDetailRepository.saveAll(orderDetailList);
    }

    @Override
    public List<Integer> getProductOfOrder(Long orderID) {
        return orderDetailRepository.getProductID(orderID);
    }

    @Override
    public String sumProductOrder(int productID) {
        return orderDetailRepository.getQuantityProductSale(productID);
    }
}

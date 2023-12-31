package com.example.demo.service.impl;


import com.example.demo.dto.SearchOrderObject;
import com.example.demo.model.Orders;
import com.example.demo.model.PurchaseOrder;
import com.example.demo.model.QOrders;
import com.example.demo.model.User;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.attoparser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.querydsl.core.BooleanBuilder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<Orders> findByUserId(Long userId) {



        return orderRepository.findByUserId(userId);
    }


    @Override
    public void cancelOrder(Long orderID) {
        Optional<Orders> optionalOrder = orderRepository.findById(orderID);
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            order.setOrderStatus("Đã bị hủy");
            orderRepository.save(order);
        }
    }

    @Override
    public Orders placeOrder(Orders order, List<PurchaseOrder> purchaseOrders) {
        Orders savedOrder = orderRepository.save(order);

        for (PurchaseOrder purchaseOrder : purchaseOrders) {

            purchaseOrder.setOrder(savedOrder);
            orderDetailRepository.save(purchaseOrder);

        }

        return savedOrder;
    }
    @Override
    public Page<Orders> getAllOrderByFilter(SearchOrderObject object, int page) throws ParseException {
        BooleanBuilder builder = new BooleanBuilder();

        String trangThaiDon = object.getStatusOrder();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

        if (!trangThaiDon.equals("")) {
            builder.and(QOrders.orders.orderStatus.eq(trangThaiDon));
        }

        return orderRepository.findAll(builder, PageRequest.of(page - 1, 3));
    }

    @Override
    public Orders updateOrder(Orders order){
        return orderRepository.save(order);
    }

    @Override
    public Orders save(Orders order){
        return orderRepository.save(order);
    }

    @Override
    public Orders findById(long id){
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Orders> findByOrderStatusAndShipper(String orderStatus, User shipper){
        return orderRepository.findByOrderStatusAndShipper(orderStatus, shipper);
    }

    @Override
    public Page<Orders> findOrderByShipper(SearchOrderObject object, int page, int size, User shipper) throws ParseException {
        BooleanBuilder builder = new BooleanBuilder();

        String trangThaiDon = object.getStatusOrder();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

        builder.and(QOrders.orders.shipper.eq(shipper));

        if (!trangThaiDon.equals("")) {
            builder.and(QOrders.orders.orderStatus.eq(trangThaiDon));
        }

        return orderRepository.findAll(builder, PageRequest.of(page - 1, size));
    }

    @Override
    public int countByOrderStatus(String orderStatus){
        return orderRepository.countByOrderStatus(orderStatus);
    }


    @Override
    public List<Object> getOrderByDayAndWeek(){
        return orderRepository.getOrderByDayAndWeek();
    }

    @Override
    public List<Object> getOrderByWeekAndMonth(){
        return orderRepository.getOrderByWeekAndMonth();
    }

    @Override
    public List<Object> getOrderByMonthAndYear(){
        return orderRepository.getOrderByMonthAndYear();
    }

//    @Override
//    public List<Object> getOrderByYear(){
//        return orderRepository.getOrderByYear();
//    }
}

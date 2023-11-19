package com.example.demo.service;

import com.example.demo.dto.SearchOrderObject;
import com.example.demo.model.Orders;
import com.example.demo.model.PurchaseOrder;
import com.example.demo.model.User;
import org.attoparser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Orders placeOrder(Orders order, List<PurchaseOrder> orderDetails);
    void cancelOrder(Long orderID);

    List<Orders> findByUserId(Long userId);
    Page<Orders> getAllOrderByFilter(SearchOrderObject object, int page) throws ParseException;
    List<Orders> findByOrderStatusAndShipper(String orderStatus, User shipper);
    int countByOrderStatus(String orderStatus);

    Orders updateOrder(Orders order);

    Orders findById(long id);

    Orders save(Orders order);

    Page<Orders> findOrderByShipper(SearchOrderObject object, int page, int size, User shipper) throws ParseException;

    List<Object> getOrderByDayAndWeek();

    List<Object> getOrderByWeekAndMonth();

    List<Object> getOrderByMonthAndYear();

//    List<Object> getOrderByYear();
}

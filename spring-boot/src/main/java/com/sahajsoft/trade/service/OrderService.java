package com.sahajsoft.trade.service;

import java.util.List;

import com.sahajsoft.trade.model.Order;

public interface OrderService {

	boolean placeOrder(Order order);

	List<Order> listOrders();

	boolean placeOrders(List<Order> orders);

}

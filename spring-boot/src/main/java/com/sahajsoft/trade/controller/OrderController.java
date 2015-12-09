package com.sahajsoft.trade.controller;

import java.util.List;

import com.sahajsoft.trade.model.Order;

public interface OrderController {

	List<Order> listOrders();

	boolean placeOrder(Order order);

}

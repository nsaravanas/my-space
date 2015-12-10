package com.sahajsoft.soes.service;

import java.util.List;

import com.sahajsoft.soes.model.Order;

public interface OrderService {

	boolean placeOrder(Order order);

	List<Order> listOrders();

	boolean placeOrders(List<Order> orders);

	boolean parseCSVInput();

	boolean generateCSVOutput();

}

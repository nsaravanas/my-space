package com.sahajsoft.soes.controller;

import java.util.List;

import com.sahajsoft.soes.model.Order;

public interface OrderController {

	List<Order> listOrders();

	boolean placeOrder(Order order);

	boolean parseCSVInput();

	boolean generateOutput();

}

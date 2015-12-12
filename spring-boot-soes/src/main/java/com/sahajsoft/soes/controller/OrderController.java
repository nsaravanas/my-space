package com.sahajsoft.soes.controller;

import org.springframework.web.servlet.ModelAndView;

import com.sahajsoft.soes.model.Order;

public interface OrderController {

	ModelAndView listOrders();

	boolean placeOrder(Order order);

	boolean parseCSVInput();

	boolean generateOutput();

	String home();

}

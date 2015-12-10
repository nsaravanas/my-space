package com.sahajsoft.trade.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sahajsoft.trade.model.Order;
import com.sahajsoft.trade.model.Side;
import com.sahajsoft.trade.service.OrderService;

@RestController
public class OrderControllerImpl implements OrderController {

	@Inject
	private OrderService service;

	@Override
	@RequestMapping(name = "/placeOrder", method = RequestMethod.POST)
	@ResponseBody
	public boolean placeOrder(@RequestBody @Valid Order order) {
		return this.service.placeOrder(order);
	}

	@Override
	@RequestMapping("/listOrders")
	@ResponseBody
	public List<Order> listOrders() {
		return this.service.listOrders();
	}

	@RequestMapping("/sample")
	@ResponseBody
	public Order sampleOrder() {
		Order o = new Order();
		o.setCompany("ABC");
		o.setQuantity(100L);
		o.setSide(Side.BUY);
		o.setStockId(123);
		return o;
	}

	@Override
	@RequestMapping("/readInput")
	@ResponseBody
	public boolean parseCSVInput() {
		return this.service.parseCSVInput();
	}

	@Override
	@RequestMapping("/generateOutput")
	@ResponseBody
	public boolean generateOutput() {
		return this.service.generateCSVOutput();
	}
}
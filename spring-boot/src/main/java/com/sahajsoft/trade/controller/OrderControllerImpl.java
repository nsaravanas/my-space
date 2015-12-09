package com.sahajsoft.trade.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sahajsoft.trade.model.Order;
import com.sahajsoft.trade.model.Side;
import com.sahajsoft.trade.model.Status;
import com.sahajsoft.trade.service.OrderService;

@RestController("/soes")
public class OrderControllerImpl implements OrderController {

	@Inject
	private OrderService service;

	@Override
	@RequestMapping(name = "/placeOrder", method = RequestMethod.POST)
	@ResponseBody
	public boolean placeOrder(@RequestBody Order order) {
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
		o.setQuantity(100);
		o.setReceivedTime(new Date());
		o.setSide(Side.BUY);
		o.setStatus(Status.OPEN);
		o.setStockId(123);
		o.setUpdatedTime(new Date());
		return o;
	}

	@RequestMapping("/test")
	public void test() {

		Order o1 = new Order(); // 1 Buy ABC 10
		o1.setStockId(1);
		o1.setSide(Side.BUY);
		o1.setCompany("ABC");
		o1.setQuantity(10);

		Order o2 = new Order(); // 2 Sell XYZ 15
		o2.setStockId(2);
		o2.setSide(Side.SELL);
		o2.setCompany("XYZ");
		o2.setQuantity(15);

		Order o3 = new Order(); // 3 Sell ABC 13
		o3.setStockId(3);
		o3.setSide(Side.SELL);
		o3.setCompany("ABC");
		o3.setQuantity(13);

		Order o4 = new Order(); // 4 Buy XYZ 10
		o4.setStockId(4);
		o4.setSide(Side.BUY);
		o4.setCompany("XYZ");
		o4.setQuantity(10);

		Order o5 = new Order(); // 5 Buy XYZ 8
		o5.setStockId(5);
		o5.setSide(Side.BUY);
		o5.setCompany("XYZ");
		o5.setQuantity(8);

		List<Order> orders = new ArrayList<>();
		orders.add(o1);
		orders.add(o2);
		orders.add(o3);
		orders.add(o4);
		orders.add(o5);

		this.service.placeOrders(orders);

	}
}

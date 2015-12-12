package com.sahajsoft.soes.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sahajsoft.soes.model.Order;
import com.sahajsoft.soes.model.Side;
import com.sahajsoft.soes.service.OrderService;

@Controller
@RequestMapping("/soes")
public class OrderControllerImpl implements OrderController {

	@Inject
	private OrderService service;

	@Override
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@Override
	@RequestMapping(name = "/placeOrder", method = RequestMethod.POST)
	public boolean placeOrder(@RequestBody @Valid Order order) {
		return this.service.placeOrder(order);
	}

	// @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	// public String placeOrder(@RequestParam Integer stockId, @RequestParam
	// Side side, @RequestParam String company,
	// @RequestParam Long quantity, RedirectAttributes redirectAttributes) {
	// Order order = new Order(stockId, side, company, quantity);
	// this.service.placeOrder(order);
	// redirectAttributes.addFlashAttribute("message", String.format("Stock
	// order with ID %s placed.", stockId));
	// return "redirect:/";
	// }

	@Override
	@RequestMapping("/listOrders")
	public ModelAndView listOrders() {
		final Map<String, Object> model = new HashMap<>();
		model.put("orders", this.service.listOrders());
		return new ModelAndView("index", model);
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
package com.sahajsoft.soes.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sahajsoft.soes.model.Order;
import com.sahajsoft.soes.service.OrderService;

@Controller
public class OrderControllerImpl implements OrderController {

	@Inject
	private OrderService service;

	@Override
	@RequestMapping("/")
	public ModelAndView home() {
		final Map<String, Object> model = new HashMap<>();
		model.put("orders", this.service.listOrders());
		return new ModelAndView("index", model);
	}

	@Override
	@RequestMapping(name = "/placeOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public String placeOrder(@ModelAttribute @Valid Order order, RedirectAttributes redirectAttributes,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/";
		}
		this.service.placeOrder(order);
		// model.addAttribute("order", order);
		redirectAttributes.addFlashAttribute("message", "Order with order id " + order.getStockId() + " placed.");
		return "redirect:/";
	}

	@Override
	@RequestMapping("/listOrders")
	public String listOrders() {
		return "redirect:/";
	}

	@Override
	@RequestMapping("/readInput")
	public String parseCSVInput(RedirectAttributes redirectAttributes) {
		this.service.parseCSVInput();
		redirectAttributes.addFlashAttribute("message", "CSV input parsed and placed orders");
		return "redirect:/";
	}

	@Override
	@RequestMapping("/generateOutput")
	public String generateOutput(RedirectAttributes redirectAttributes) {
		this.service.generateCSVOutput();
		redirectAttributes.addFlashAttribute("message", "CSV output generated");
		return "redirect:/";
	}

	@ModelAttribute("order")
	public Order createOrder() {
		return new Order();
	}
}
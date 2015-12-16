package com.sahajsoft.soes.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sahajsoft.soes.model.Order;

public interface OrderController {

	String listOrders();

	String parseCSVInput(RedirectAttributes redirectAttributes);

	String generateOutput(RedirectAttributes redirectAttributes);

	ModelAndView home();

	String placeOrder(Order order, RedirectAttributes redirectAttributes, BindingResult bindingResult);

	ModelAndView globalError(Exception e);

}

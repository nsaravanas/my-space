package com.sahajsoft.trade.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.sahajsoft.trade.engine.OrderProcessingEngine;
import com.sahajsoft.trade.model.Order;
import com.sahajsoft.trade.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);

	@Inject
	private OrderRepository repository;

	@Inject
	private OrderProcessingEngine engine;

	@Override
	@Transactional
	public boolean placeOrder(Order order) {
		boolean isSuccess = false;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ie) {
			LOG.info("Delay added to check flow.");
		}
		try {
			this.repository.save(order);
			isSuccess = true;
		} catch (DataAccessException dae) {
			LOG.error("Exception occured while persisting with order id " + order.getStockId() + " Exception "
					+ dae.getMessage());
			isSuccess = false;
		}
		if (isSuccess) {
			this.engine.processOrder(order);
		}
		return isSuccess;
	}

	@Override
	@Transactional
	public List<Order> listOrders() {
		return this.repository.findAll();
	}

	@Override
	@Transactional
	public boolean placeOrders(List<Order> orders) {
		for (Order order : orders) {
			placeOrder(order);
		}
		return true;
	}

	@Override
	public void parseCSVInput() {
		final String filePath = "input.csv";
		CSVUtil.readFile(filePath);
	}

	@Override
	public void generateCSVOutput() {
		final String filePath = "output.csv";
		CSVUtil.writeFile(listOrders(), filePath);
	}

}

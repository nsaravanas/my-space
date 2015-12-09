package com.sahajsoft.trade.engine;

import com.sahajsoft.trade.model.Order;

public interface OrderProcessingEngine {

	void processOrder(Order order);

}

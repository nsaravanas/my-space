package com.sahajsoft.soes.service;

import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import com.sahajsoft.soes.model.Order;

public interface OrderService {

	boolean placeOrder(Order order);

	List<Order> listOrders();

	boolean placeOrders(List<Order> orders);

	boolean parseCSVInput();

	boolean generateCSVOutput();

	Order getOrder(Integer id);

	void processFile(MultipartFile myFile);

	FileSystemResource downloadFile();

}

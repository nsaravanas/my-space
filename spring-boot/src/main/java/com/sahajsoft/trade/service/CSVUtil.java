package com.sahajsoft.trade.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import com.sahajsoft.trade.model.Order;
import com.sahajsoft.trade.model.Side;

public class CSVUtil {

	private static final String STOCKID = "Stock Id";
	private static final String SIDE = "Side";
	private static final String COMPANY = "Company";
	private static final String QUANTITY = "Quantity";
	private static final String REMAININGQUANTITY = "RemainingQuantity";
	private static final String STATUS = "Status";

	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final char COMMA = ',';

	private static final String[] INPUT_FILE_HEADER_MAPPING = { STOCKID, SIDE, COMPANY, QUANTITY };
	private static final String[] OUTPUT_FILE_HEADER_MAPPING = { STOCKID, SIDE, COMPANY, QUANTITY, REMAININGQUANTITY,
			STATUS };

	private static final Logger LOG = Logger.getLogger(CSVUtil.class);

	public static List<Order> readFile(String filePath) {

		List<Order> orders = new ArrayList<>();
		ClassLoader classLoader = CSVUtil.class.getClassLoader();

		FileReader fileReader = null;
		CSVParser csvParser = null;
		CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(INPUT_FILE_HEADER_MAPPING).withDelimiter(COMMA)
				.withRecordSeparator(NEW_LINE_SEPARATOR);

		try {
			fileReader = new FileReader(classLoader.getResource(filePath).getFile());
			csvParser = new CSVParser(fileReader, csvFormat);
			List<CSVRecord> csvRecords = csvParser.getRecords();
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord r = csvRecords.get(i);
				Order order = new Order(new Integer(r.get(STOCKID)), setSide(r.get(SIDE)), r.get(COMPANY),
						new Long(r.get(QUANTITY)));
				orders.add(order);
				System.out.println(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("CSV file read error");
		}
		return orders;
	}

	private static Side setSide(String side) {
		if (side == null)
			throw new IllegalArgumentException("SIDE can't be null");
		else if (side.equalsIgnoreCase("BUY"))
			return Side.BUY;
		else if (side.equalsIgnoreCase("SELL"))
			return Side.SELL;
		else
			throw new IllegalArgumentException("Unknown side, [BUY,SELL] allowed");
	}

	public static void writeFile(List<Order> list, String filePath) {
		FileWriter fileWriter = null;
		CSVPrinter csvPrinter = null;

		ClassLoader classLoader = CSVUtil.class.getClassLoader();

		CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(OUTPUT_FILE_HEADER_MAPPING).withDelimiter(COMMA);
		try {
			fileWriter = new FileWriter(classLoader.getResource(filePath).getPath());
			csvPrinter = new CSVPrinter(fileWriter, csvFormat);
			csvPrinter.print(OUTPUT_FILE_HEADER_MAPPING);
			for (Order o : list) {
				csvPrinter.printRecord(o.getStockId(), o.getSide(), o.getCompany(), o.getRemainingQuantity(),
						o.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("CSV file write error");
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvPrinter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		readFile("input.csv");
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1, Side.SELL, "ABC", 100L));
		writeFile(orders, "output.csv");
	}

}

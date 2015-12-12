package com.sahajsoft.soes.io.util;

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

import com.sahajsoft.soes.model.Order;
import com.sahajsoft.soes.model.Side;

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
		FileReader fileReader = null;
		CSVParser csvParser = null;

		ClassLoader classLoader = CSVUtil.class.getClassLoader();
		CSVFormat csvFormat = CSVFormat.DEFAULT.withSkipHeaderRecord().withHeader(INPUT_FILE_HEADER_MAPPING)
				.withDelimiter(COMMA).withRecordSeparator(NEW_LINE_SEPARATOR);

		try {
			fileReader = new FileReader(classLoader.getResource(filePath).getFile());
			csvParser = new CSVParser(fileReader, csvFormat);
			List<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord r : csvRecords) {
				Order order = new Order(new Integer(r.get(STOCKID)), setSide(r.get(SIDE)), r.get(COMPANY),
						new Long(r.get(QUANTITY)));
				orders.add(order);
			}
		} catch (Exception e) {
			LOG.error("CSV file read error " + e);
		} finally {
			try {
				fileReader.close();
				csvParser.close();
			} catch (IOException ioe) {
				LOG.error("Error while closing resource " + ioe);
			}
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
			throw new IllegalArgumentException("Unknown side, only [BUY,SELL] allowed");
	}

	public static void writeFile(List<Order> list, String filePath) {

		FileWriter fileWriter = null;
		CSVPrinter csvPrinter = null;

		ClassLoader classLoader = CSVUtil.class.getClassLoader();
		CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(OUTPUT_FILE_HEADER_MAPPING).withDelimiter(COMMA);

		try {
			fileWriter = new FileWriter(classLoader.getResource(filePath).getPath());
			csvPrinter = new CSVPrinter(fileWriter, csvFormat);
			for (Order o : list) {
				csvPrinter.printRecord(o.getStockId(), o.getSide(), o.getCompany(), o.getQuantity(),
						o.getRemainingQuantity(), o.getStatus());
			}
		} catch (Exception e) {
			LOG.error("CSV file write error " + e);
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvPrinter.close();
			} catch (IOException ioe) {
				LOG.error("Error while closing resource " + ioe);
			}
		}

	}

}

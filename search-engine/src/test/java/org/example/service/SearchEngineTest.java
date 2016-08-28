package org.example.service;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.example.model.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class SearchEngineTest {

	@Autowired
	private SearchEngine searchEngine;

	@Before
	public void initialize() {
		Page page1 = new Page();
		page1.setName("P1");
		page1.setTags(asList("Ford", "Car", "Review"));

		Page page2 = new Page();
		page2.setName("P2");
		page2.setTags(asList("Review", "Car"));

		Page page3 = new Page();
		page3.setName("P3");
		page3.setTags(asList("Review", "Ford"));

		Page page4 = new Page();
		page4.setName("P4");
		page4.setTags(asList("Toyota", "Car"));

		Page page5 = new Page();
		page5.setName("P5");
		page5.setTags(asList("Honda", "Car"));

		Page page6 = new Page();
		page6.setName("P6");
		page6.setTags(asList("Car"));

		Page page7 = new Page();
		page7.setName("P7");
		page7.setTags(asList("Car", "Ford"));

		String[][] queries = { { "Ford" }, { "Car" }, { "Review" }, { "Ford", "Review" }, { "Ford", "Car" }, { "cooking", "French" } };
		List<List<String>> queriesList = stream(queries).map(query -> stream(query).collect(toList())).collect(toList());
		List<Page> pages = asList(page1, page2, page3, page4, page5, page6);
		this.searchEngine.indexing(pages, queriesList);
	}

	@Test
	public void testOne() {
	}

	@After
	public void destroy() {
		this.searchEngine.getEngineOptimization().getCache().clear();
	}
}

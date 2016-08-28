package org.example.controller;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.example.model.Page;
import org.example.model.search.SearchGetRequest;
import org.example.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Map<String, Object> search(@RequestBody SearchGetRequest searchRequest) {
		long start = System.nanoTime();
		List<Page> searchPages = this.searchService.search(searchRequest.getSearch());
		long end = System.nanoTime();
		Map<String, Object> result = new LinkedHashMap<>();
		double timeTaken = (end - start) / (1000 * 1000);
		result.put("timeTaken_in_mills", timeTaken);
		result.put("pages", searchPages);
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, List<String>> save(@RequestBody List<Page> pages) {
		List<Page> savedPages = this.searchService.save(pages);
		Map<String, List<String>> result = new HashMap<>();
		result.put("saved_pages", savedPages.stream().map(Page::getName).collect(toList()));
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Map<String, Boolean> delete(@RequestBody List<String> pageNames) {
		boolean bool = this.searchService.delete(pageNames);
		Map<String, Boolean> result = new HashMap<>();
		result.put("delete_success", bool);
		return result;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Page> selectAll() {
		return this.searchService.getAll();
	}

	@RequestMapping(value = "/initialize")
	public Map<String, Object> initialize() {
		Page page1 = new Page();
		page1.setName("P1");
		page1.setTags(Arrays.asList("Ford", "Car", "Review"));

		Page page2 = new Page();
		page2.setName("P2");
		page2.setTags(Arrays.asList("Review", "Car"));

		Page page3 = new Page();
		page3.setName("P3");
		page3.setTags(Arrays.asList("Review", "Ford"));

		Page page4 = new Page();
		page4.setName("P4");
		page4.setTags(Arrays.asList("Toyota", "Car"));

		Page page5 = new Page();
		page5.setName("P5");
		page5.setTags(Arrays.asList("Honda", "Car"));

		Page page6 = new Page();
		page6.setName("P6");
		page6.setTags(Arrays.asList("Car"));

		Page page7 = new Page();
		page7.setName("P7");
		page7.setTags(Arrays.asList("Car", "Ford"));

		String[][] queries = { { "Ford" }, { "Car" }, { "Review" }, { "Ford", "Review" }, { "Ford", "Car" }, { "cooking", "French" } };
		List<List<String>> queriesList = new ArrayList<>();
		for (String[] query : queries) {
			queriesList.add(Arrays.asList(query));
		}
		List<Page> pages = Arrays.asList(page1, page2, page3, page4, page5, page6);
		List<Page> savedPages = this.searchService.save(pages);
		Map<String, Object> result = new LinkedHashMap<>();
		result.put("initialize", "ok");
		result.put("pages", savedPages);
		return result;
	}
}

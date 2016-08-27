package org.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.model.Header;
import org.example.model.Page;
import org.example.model.Response;
import org.example.model.SearchResponse;
import org.example.model.delete.DeleteRequest;
import org.example.model.delete.DeleteResponse;
import org.example.model.save.SaveRequest;
import org.example.model.save.SaveResponse;
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

	@RequestMapping("/")
	public SearchResponse search(@RequestBody SearchGetRequest searchRequest) {
		long start = System.nanoTime();
		List<Page> searchPages = this.searchService.search(searchRequest.getSearch());
		long end = System.nanoTime();
		SearchResponse searchResponse = new SearchResponse();
		Response response = new Response();
		Header header = new Header();
		double timeTaken = (end - start) / (1000 * 1000);
		header.setTimeTaken(timeTaken);
		response.setPages(searchPages);
		response.setHeader(header);
		searchResponse.setResponse(response);
		return searchResponse;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public SaveResponse save(@RequestBody SaveRequest saveRequest) {
		return this.searchService.save(saveRequest.getSave().getPages());
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public DeleteResponse delete(@RequestBody DeleteRequest deleteRequest) {
		return this.searchService.delete(deleteRequest.getDelete().getPages());
	}

	@RequestMapping("/get")
	public List<Page> getall() {
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
		return pages;
	}
	// @RequestMapping(value = "/", method = RequestMethod.GET)
	// public IndexResponse index(@RequestBody IndexRequest searchRequest) {
	// return null;
	// }
}

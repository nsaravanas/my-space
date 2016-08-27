package org.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

import org.example.model.Page;
import org.springframework.stereotype.Service;

@Service
public class SearchEngineOptimization {

	private final static Map<String, List<Page>> CACHE = new WeakHashMap<>();

	public Map<String, List<Page>> getCache() {
		return CACHE;
	}

	// Test logic
	public static void main(String[] args) {
		SearchEngineOptimization SEO = new SearchEngineOptimization();
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
		Map<String, List<Page>> indices = SEO.indexing(pages, queriesList);
		indices.forEach((k, v) -> System.out.println(k + "->" + v.stream().map(Page::getName).collect(Collectors.joining(","))));
	}

	private Map<String, List<Page>> indexing(List<Page> pages, List<List<String>> queries) {
		Map<String, List<Page>> indexed = new LinkedHashMap<>();
		for (List<String> queryTags : queries) {
			String queryString = getQueryString(queryTags);
			indexed.put(queryString, indexing(pages, queryTags, queryString));
		}
		return indexed;
	}

	public List<Page> indexing(List<Page> pages, List<String> query, String queryString) {
		List<Page> matchedPages = new ArrayList<>();
		for (Page page : pages) {
			page.setWeight(calculateWeight(query, page.getTags()));
			if (page.getWeight() != 0)
				matchedPages.add(page);
		}
		matchedPages.sort(Comparator.comparingInt(Page::getWeight).reversed());
		addToCache(queryString, matchedPages);
		return matchedPages;
	}

	private void addToCache(String queryString, List<Page> matchedPages) {
		CACHE.put(queryString, matchedPages);
	}

	private static int calculateWeight(List<String> queryTags, List<String> pageTags) {
		int weight = 0;
		int queryWeight = 8;
		for (String queryTag : queryTags) {
			int pageWeight = 8;
			for (String pageTag : pageTags) {
				if (pageTag.equalsIgnoreCase(queryTag)) {
					weight += pageWeight * queryWeight;
				}
				pageWeight--;
			}
			queryWeight--;
		}
		return weight;
	}

	public static String getQueryString(List<String> queryList) {
		return queryList.stream().collect(Collectors.joining("_")).toLowerCase();
	}
}

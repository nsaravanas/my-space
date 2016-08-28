package org.example.controller;

import java.util.List;
import java.util.Map;

import org.example.model.Page;
import org.example.model.search.SearchGetRequest;

public interface SearchController {

	Map<String, Object> search(SearchGetRequest searchRequest);

	Map<String, List<String>> save(List<Page> pages);

	Map<String, Boolean> delete(List<String> pageNames);

	Map<String, Boolean> clear();

	List<Page> selectAll();

	Map<String, Object> initialize();

}

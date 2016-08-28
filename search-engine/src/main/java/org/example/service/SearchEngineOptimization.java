package org.example.service;

import java.util.List;
import java.util.Map;

import org.example.model.Page;

public interface SearchEngineOptimization {

	void addToCache(String queryString, List<Page> matchedPages);

	Map<String, List<Page>> getCache();

}

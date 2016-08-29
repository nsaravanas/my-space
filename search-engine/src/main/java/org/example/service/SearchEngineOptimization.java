package org.example.service;

import java.util.List;
import java.util.Map;

import org.example.model.Page;
import org.example.service.SearchEngineOptimizationImpl.Key;

public interface SearchEngineOptimization {

	void addToCache(String queryString, List<Page> matchedPages);

	Map<Key, List<Page>> getCache();

	List<Page> getFromCache(String queryString);

}

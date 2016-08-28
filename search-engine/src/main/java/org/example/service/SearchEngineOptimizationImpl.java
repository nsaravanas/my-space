package org.example.service;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.example.model.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchEngineOptimizationImpl implements SearchEngineOptimization {

	@Value("${cache.size}")
	private int cacheSize;

	private final static Map<String, List<Page>> CACHE = new WeakHashMap<>();

	@Override
	public Map<String, List<Page>> getCache() {
		return CACHE;
	}

	@Override
	public void addToCache(String queryString, List<Page> matchedPages) {
		// TODO remove old cached items
		CACHE.put(queryString, matchedPages);
	}

}

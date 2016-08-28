package org.example.service;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.example.model.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchEngineOptimization {

	@Value("${cache.size}")
	private int cacheSize;

	private final static Map<String, List<Page>> CACHE = new WeakHashMap<>();

	public Map<String, List<Page>> getCache() {
		return CACHE;
	}

	public void addToCache(String queryString, List<Page> matchedPages) {
		if (CACHE.size() >= cacheSize) { // remove old cached items

		} else {
			CACHE.put(queryString, matchedPages);
		}
	}

}

package org.example.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.example.model.Page;
import org.example.model.QueryResult;
import org.example.model.search.Search;
import org.example.repository.PageRepository;
import org.example.repository.QueryResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	@Autowired
	private SearchEngineOptimization engineOptimization;

	@Autowired
	private SearchEngine searchEngine;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private QueryResultRepository resultRepository;

	@Transactional
	public List<Page> search(Search search) {

		List<String> queryList = Arrays.asList(search.getTags()).stream().map(String::toLowerCase).collect(Collectors.toList());
		String queryString = SearchEngine.getQueryString(queryList);

		// From cache
		if (search.isCache()) {
			List<Page> cacheResult = engineOptimization.getCache().get(queryString);
			if (cacheResult != null && !cacheResult.isEmpty()) {
				return cacheResult;
			}
		}

		// From historic searches
		if (search.isIndex()) {
			QueryResult queryResult = this.resultRepository.findOne(queryString);
			if (queryResult != null) {
				List<Page> queryPages = this.pageRepository.findAll(queryResult.getPageNames());
				if (queryPages != null && !queryPages.isEmpty()) {
					return queryPages;
				}
			}
		}

		// Query all pages
		List<Page> persistentPages = pageRepository.findDistinctPageByTagsIn(queryList);
		List<Page> indexedPages = searchEngine.indexing(persistentPages, queryList, queryString);

		// Persist to DB
		QueryResult result = new QueryResult();
		result.setQuery(queryString);
		result.setPageNames(indexedPages.stream().map(Page::getName).collect(Collectors.toList()));
		resultRepository.save(result);

		return indexedPages;
	}

	public List<Page> save(List<Page> pages) {
		for (Page page : pages) {
			page.setTags(page.getTags().stream().map(String::toLowerCase).collect(Collectors.toList()));
		}
		return this.pageRepository.save(pages);
	}

	public boolean delete(List<String> pages) {
		this.pageRepository.delete(this.pageRepository.findAll(pages));
		return true;
	}

	@Scheduled(fixedRate = 10 * 60 * 1000) // reIndex every 10 minutes
	public void reIndex() {
		List<QueryResult> queryResults = this.resultRepository.findAll();
		for (QueryResult queryResult : queryResults) {
			List<String> tags = Arrays.stream(queryResult.getQuery().split("_")).collect(Collectors.toList());
			List<Page> pages = this.pageRepository.findAll(queryResult.getPageNames());
			this.searchEngine.indexing(pages, tags, queryResult.getQuery());
		}
	}

}

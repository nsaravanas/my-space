package org.example.service;

import static java.util.Comparator.comparing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.example.model.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SearchEngineOptimizationImpl implements SearchEngineOptimization {

	@Value("${cache.size}")
	private int cacheSize;

	private final static Map<Key, List<Page>> CACHE = new WeakHashMap<>();

	@Override
	public Map<Key, List<Page>> getCache() {
		return CACHE;
	}

	@Override
	public void addToCache(String queryString, List<Page> matchedPages) {
		if (CACHE.size() >= cacheSize) {
			removeOldQueriesFromCache();
		}
		CACHE.put(new Key(queryString, LocalDateTime.now()), matchedPages);
	}

	@Override
	public List<Page> getFromCache(String queryString) {
		return CACHE.get(new Key(queryString));
	}

	@Scheduled(fixedDelay = 15 * 60 * 1000) // remove old entries from cache
	public void removeOldQueriesFromCache() {
		CACHE.keySet().stream().sorted(comparing(Key::getLastAccessed)).limit(5).forEach(CACHE::remove);
	}

	final static class Key {

		String query;

		LocalDateTime lastAccessed;

		public Key() {
			super();
		}

		public Key(String query) {
			super();
			this.query = query;
		}

		public Key(String query, LocalDateTime lastAccessed) {
			super();
			this.query = query;
			this.lastAccessed = lastAccessed;
		}

		public String getQuery() {
			return query;
		}

		public LocalDateTime getLastAccessed() {
			return lastAccessed;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((query == null) ? 0 : query.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (query == null) {
				if (other.query != null)
					return false;
			} else if (!query.equals(other.query))
				return false;
			return true;
		}

	}

}

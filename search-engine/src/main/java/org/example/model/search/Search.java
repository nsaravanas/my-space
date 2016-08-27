package org.example.model.search;

public class Search {

	private boolean cache;

	private boolean index;

	private String[] tags;

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}

	public void setIndex(boolean index) {
		this.index = index;
	}

	public boolean isCache() {
		return cache;
	}

	public boolean isIndex() {
		return index;
	}
}

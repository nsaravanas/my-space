package org.example.model.search;

public class Search {

	private boolean cache = true;

	private boolean index = true;

	private String[] tags;

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public boolean isCache() {
		return cache;
	}

	public boolean getCache() {
		return cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}

	public boolean isIndex() {
		return index;
	}

	public boolean getIndex() {
		return index;
	}

	public void setIndex(boolean index) {
		this.index = index;
	}

}

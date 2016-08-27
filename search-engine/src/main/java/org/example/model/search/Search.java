package org.example.model.search;

public class Search {

	private boolean isCacheOk = true;

	private boolean isIndexOk = true;

	private String[] tags;

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public boolean isCacheOk() {
		return isCacheOk;
	}

	public void setCacheOk(boolean isCacheOk) {
		this.isCacheOk = isCacheOk;
	}

	public boolean isIndexOk() {
		return isIndexOk;
	}

	public void setIndexOk(boolean isIndexOk) {
		this.isIndexOk = isIndexOk;
	}

}

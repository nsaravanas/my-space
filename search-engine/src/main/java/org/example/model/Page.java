package org.example.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Entity
@Indexed
public class Page {

	@Id
	private String name;

	@Transient
	private Integer weight;

	@Column
	private String url;

	@OneToOne
	private Stats stats;

	@ElementCollection(fetch = FetchType.EAGER)
	@Field
	@IndexedEmbedded
	private List<String> tags;

	@OneToMany
	private List<Page> pages;

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Stats getStats() {
		return stats;
	}

	public String getUrl() {
		return url;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

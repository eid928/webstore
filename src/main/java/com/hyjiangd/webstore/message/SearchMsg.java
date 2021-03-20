package com.hyjiangd.webstore.message;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SearchMsg<T> {
	
	private long totalSearchResult;
	private List<T> searchResult;
	
	
	public long getTotalSearchResult() {
		return totalSearchResult;
	}
	public void setTotalSearchResult(long totalSearchResult) {
		this.totalSearchResult = totalSearchResult;
	}
	public List<T> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(List<T> searchResult) {
		this.searchResult = searchResult;
	}
}

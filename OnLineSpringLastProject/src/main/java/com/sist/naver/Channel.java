package com.sist.naver;
import java.util.*;

/*
 * jaxb 방식
 * <rss> <channel> <item> <title>,<description>
 */

public class Channel {

	private List<Item> item = new ArrayList<Item>();

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
	
}

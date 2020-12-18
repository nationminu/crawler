package com.souljam.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class CrawlerVO  implements Serializable {
	   
		private static final long serialVersionUID = 1L;
		
		private String title;
		private String description;
		private String date;
		private String content;
		private String link;
		
}

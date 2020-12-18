package com.souljam.crawler.domain;

import java.io.Serializable;

import lombok.Data;
  
@Data
public class ScrapperVO implements Serializable {
   
	private static final long serialVersionUID = 1L;
	
	private String domain;
	private String url;
	private String title;
	private String href;

}

package com.souljam.crawler.scrapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

public class WebCrawler {

	Logger logger = LoggerFactory.getLogger(WebCrawler.class);

	public void scrap() throws Exception { 
		
		List<String> URLs = new ArrayList<String>();
		
		URLs.add("https://medium.com/mobility-insights");
		
		for (String url : URLs) { 
			logger.info(url);
		}
		
		
		logger.info("Running batch ..");
		
		
		
		
	}

}

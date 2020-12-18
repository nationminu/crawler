package com.souljam.crawler.scrapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.souljam.crawler.domain.ScrapperVO;

public class TestCrawler {
	static Logger logger = LoggerFactory.getLogger(TestCrawler.class);

	public static void main(String[] args) throws URISyntaxException {

		logger.info("- crawler start");

		List<String> URLs = new ArrayList<String>();

		URLs.add("https://medium.com/mobility-insights");
		URLs.add("https://medium.com/nextmobility");
		URLs.add("https://www.autodaily.co.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm");
		URLs.add("https://www.autodaily.co.kr/news/articleList.html?sc_section_code=S1N3&view_type=sm");
		URLs.add("https://www.autodaily.co.kr/news/articleList.html?sc_section_code=S1N4&view_type=sm");
		URLs.add("https://www.autodaily.co.kr/news/articleList.html?sc_section_code=S1N5&view_type=sm");
		URLs.add("https://www.autodaily.co.kr/news/articleList.html?sc_section_code=S1N6&view_type=sm");
		URLs.add("https://www.autodaily.co.kr/news/articleList.html?sc_section_code=S1N7&view_type=sm");
		URLs.add("https://www.autodaily.co.kr/news/articleList.html?sc_section_code=S1N2&view_type=sm");
		URLs.add("https://www.intelligenttransport.com/transport-news");
		URLs.add("https://www.intelligenttransport.com/transport-articles");
		URLs.add("https://medium.com/nextmobility");
		URLs.add("https://medium.com/mobility-insights");
		URLs.add("http://jmagazine.joins.com/economist/list/021301");
		URLs.add("https://newatlas.com/transport");

		ScrapperVO svo = new ScrapperVO();

		for (String url : URLs) {

			svo.setDomain(getDomainName(url));
			svo.setUrl(getPath(url));
			Document doc;

			try {

				logger.info("connect to " + url);

				doc = Jsoup.connect(url).timeout(50000).get();

				// Elements elements = doc.select("a[href*=" + url + "/]");
				Elements elements = doc.select("a");
				for (Element element : elements) {

					String link = element.attr("href");
					String title = element.text();

					if (link.equals("") || link.equals("#") || link.equals("/") || link.equals("/rss/")
							|| link.startsWith("mailto") || link.startsWith("tel:"))
						continue;

					svo.setHref(link);
					svo.setTitle(title);

					// logger.info("link, title : " + link + ", " + title);
					logger.info(svo.toString());
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		logger.info("- crawler done ");
	}

	public static String getDomainName(String url) throws URISyntaxException {
		URI uri = new URI(url);
		String domain = uri.getScheme() + "://" + uri.getHost();
		return domain.startsWith("www.") ? domain.substring(4) : domain;
	}

	public static String getPath(String url) throws URISyntaxException {
		URI uri = new URI(url);
		String path = uri.getPath();
		return path;
	}
}

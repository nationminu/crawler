package com.souljam.crawler.configuration;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ConfigurationBuilderEvent;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.event.Event;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuration File Monitor Class
 * @author wonsunlee
 * @since Nov 11, 2019
 */
@Slf4j
@Configurable
public class ReloadConfig {

	private ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder;

	@Value("${crawler.dir}")
	String crawlerDir;

	@Value("${crawler.config:null}")
	String crawlerConfig;

	/**
	 * 3가지 Configuration 파일을 기준으로 변경될 경우 Reoload
	 *  1. 환경변수의 rockPM.config 정
	 *  2. ./conf/rockPM.properties 
	 *  3. resources/rockPM.properties
	 */
	@PostConstruct
	void init() {

		String fileName1 = crawlerConfig;
		String fileName2 = crawlerDir + "/conf/crawler.properties";
		String fileName3 = "src/main/resources/crawler.properties";

		File file = null;
		File file1 = new File(fileName1);
		File file2 = new File(fileName2);
		File file3 = new File(fileName3);

		if (file1.exists()) {
			file = file1;
		} else if (file2.exists()) {
			file = file2;
		} else if (file3.exists()) {
			file = file3;
		}

		builder = new ReloadingFileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
				.configure(new Parameters().fileBased().setFile(file));

		builder.addEventListener(ConfigurationBuilderEvent.CONFIGURATION_REQUEST, new EventListener<Event>() {

			@Override
			public void onEvent(Event event) {
				log.debug("getConfiguration()이 호출 될때마다 이벤트 발생 ");
			}
		});

		PeriodicReloadingTrigger configReloadingTrigger = new PeriodicReloadingTrigger(builder.getReloadingController(), null, 1, TimeUnit.SECONDS);
		configReloadingTrigger.start();
	}

	/**
	 * Configuration 정보 가져오기 
	 * @return configuration 정보 
	 */
	public Configuration getCompositeConfiguration() {

		try {
			return builder.getConfiguration();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
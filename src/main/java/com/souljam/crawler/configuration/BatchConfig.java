package com.souljam.crawler.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import com.souljam.crawler.tasks.SampleTask;
import com.souljam.crawler.tasks.CrawlerTask;
 
@Configuration
@EnableBatchProcessing
public class BatchConfig {
     
    @Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory steps;
     
    @Bean
    public Step stepOne(){
        return steps.get("stepOne")
                .tasklet(new SampleTask())
                .build();
    }
     
    @Bean
    public Step stepTwo(){
        return steps.get("stepTwo")
                .tasklet(new CrawlerTask())
                .build();
    }   
     
    @Bean
    public Job demoJob(){
        return jobs.get("demoJob")
                .incrementer(new RunIdIncrementer())
                .start(stepOne())
                .next(stepTwo())
                .build();
    }
}
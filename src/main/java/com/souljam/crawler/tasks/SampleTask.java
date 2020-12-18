package com.souljam.crawler.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class SampleTask implements Tasklet {


    Logger logger = LoggerFactory.getLogger(SampleTask.class);
    
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception 
    { 
        logger.info("SampleTask start..");
 
        // ... your code

        logger.info("Hello SampleTask.."); 

        logger.info("SampleTask done..");
        
        return RepeatStatus.FINISHED;
    }    

}

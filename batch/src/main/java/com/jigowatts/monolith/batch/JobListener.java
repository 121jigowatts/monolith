package com.jigowatts.monolith.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("JOB START");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("JOB {}.", jobExecution.getStatus().name());
    }

}

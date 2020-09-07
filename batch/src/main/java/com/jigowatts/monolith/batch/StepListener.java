package com.jigowatts.monolith.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("STEP START");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("summary      : {}", stepExecution.getSummary());
        log.info("commit count : {}", stepExecution.getCommitCount());
        log.info("skip count   : {}", stepExecution.getSkipCount());
        log.info("write count  : {}", stepExecution.getWriteCount());
        log.info("read count   : {}", stepExecution.getReadCount());

        if (stepExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("STEP {}.", stepExecution.getStatus().name());
            return ExitStatus.COMPLETED;
        }
        return ExitStatus.FAILED;
    }

}

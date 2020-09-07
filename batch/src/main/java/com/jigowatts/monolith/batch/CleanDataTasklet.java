package com.jigowatts.monolith.batch;

import com.jigowatts.monolith.domain.repository.AddressMapper;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CleanDataTasklet implements Tasklet {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        int result = addressMapper.delete();
        log.info("delete count : {}", String.valueOf(result));
        return RepeatStatus.FINISHED;
    }

}

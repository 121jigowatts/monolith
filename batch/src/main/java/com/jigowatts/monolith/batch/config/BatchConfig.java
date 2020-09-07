package com.jigowatts.monolith.batch.config;

import java.time.LocalDateTime;

import javax.sql.DataSource;

import com.jigowatts.monolith.batch.AddressDataProcessor;
import com.jigowatts.monolith.batch.AddressFieldSetMapper;
import com.jigowatts.monolith.batch.CleanDataTasklet;
import com.jigowatts.monolith.batch.JobListener;
import com.jigowatts.monolith.batch.StepListener;
import com.jigowatts.monolith.batch.domain.model.InputAddress;
import com.jigowatts.monolith.domain.model.Address;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.jsr.JsrJobParametersConverter;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
// import org.springframework.core.io.UrlResource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {
    private final JobBuilderFactory jobBUilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SqlSessionFactory sqlSessionFactory;
    private final DataSource dataSource;

    private static final String WILL_BE_INJECTED = null;

    @Bean
    public Tasklet cleanTask() {
        return new CleanDataTasklet();
    }

    @Bean
    public Step cleanStep() {
        return stepBuilderFactory.get("cleanStep").tasklet(cleanTask()).build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<InputAddress> reader(@Value("#{jobParameters['inputFile']}") String filePath)
            throws UnexpectedInputException, ParseException {
        // String path = "";

        FlatFileItemReader<InputAddress> reader = new FlatFileItemReader<>();
        // reader.setResource(new UrlResource(path));
        reader.setResource(new FileSystemResource(filePath));
        reader.setLinesToSkip(1);

        DefaultLineMapper<InputAddress> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
        lineMapper.setFieldSetMapper(new AddressFieldSetMapper());
        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    @StepScope
    public ItemProcessor<InputAddress, Address> processor() {
        LocalDateTime processDateTime = LocalDateTime.now();
        return new AddressDataProcessor(processDateTime);
    }

    @Bean
    public MyBatisBatchItemWriter<Address> writer() {
        return new MyBatisBatchItemWriterBuilder<Address>().sqlSessionFactory(sqlSessionFactory)
                .statementId("com.jigowatts.monolith.domain.repository.AddressMapper.save").build();
    }

    @Bean
    public StepListener stepListener() {
        return new StepListener();
    }

    @Bean
    public Step dataLoadStep() {
        return stepBuilderFactory.get("dataLoadStep").<InputAddress, Address>chunk(1000).reader(reader(WILL_BE_INJECTED))
                .processor(processor()).writer(writer()).listener(stepListener()).build();
    }

    @Bean
    public JobListener jobListener() {
        return new JobListener();
    }

    @Bean
    public Job job() {
        return jobBUilderFactory.get("job").listener(jobListener()).start(cleanStep()).next(dataLoadStep()).build();
    }

    @Bean
    public JsrJobParametersConverter jobParametersConverter() {
        return new JsrJobParametersConverter(dataSource);
    }

}

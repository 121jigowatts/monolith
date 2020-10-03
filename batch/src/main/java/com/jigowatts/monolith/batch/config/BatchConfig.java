package com.jigowatts.monolith.batch.config;

import java.net.MalformedURLException;
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
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.UrlResource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@PropertySource("classpath:batch.properties")
public class BatchConfig {
    private final JobBuilderFactory jobBUilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SqlSessionFactory sqlSessionFactory;
    private final DataSource dataSource;

    @Bean
    public BatchContext context() {
        return new BatchContext();
    }

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
    public FlatFileItemReader<InputAddress> reader() throws MalformedURLException {

        String url = context().getUrl();
        log.info("-----> Download from {}", url);

        FlatFileItemReader<InputAddress> reader = new FlatFileItemReader<>();
        reader.setResource(new UrlResource(url));
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
    public Step dataLoadStep() throws MalformedURLException {
        return stepBuilderFactory.get("dataLoadStep").<InputAddress, Address>chunk(1000).reader(reader())
                .processor(processor()).writer(writer()).listener(stepListener()).build();
    }

    @Bean
    public JobListener jobListener() {
        return new JobListener();
    }

    @Bean
    public Job job() throws MalformedURLException {
        return jobBUilderFactory.get("job").listener(jobListener()).start(cleanStep()).next(dataLoadStep()).build();
    }

    @Bean
    public JsrJobParametersConverter jobParametersConverter() {
        return new JsrJobParametersConverter(dataSource);
    }

}

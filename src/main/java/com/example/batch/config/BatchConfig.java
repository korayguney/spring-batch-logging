package com.example.batch.config;

import com.example.batch.listener.ItemFailureLoggerListener;
import com.example.batch.listener.JobListener;
import com.example.batch.steps.Processor;
import com.example.batch.steps.Reader;
import com.example.batch.steps.Writer;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Autowired
    private StepBuilderFactory sbf;

    @Autowired
    private JobBuilderFactory jbf;

    @Bean
    public Job job(){
        return jbf.get("job1")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return sbf.get("Step1")
                .<String, String>chunk(3)
                .reader(reader())
                .writer(writer())
                .processor(processor())
                .listener((ItemProcessListener<? super String, ? super String>) new ItemFailureLoggerListener())
                .build();
    }


    @Bean
    public Reader reader() {
        return new Reader();
    }

    @Bean
    public Processor processor() {
        return new Processor();
    }

    @Bean
    public Writer writer() {
        return new Writer();
    }

    @Bean
    public JobListener listener() {
        return new JobListener();
    }

}

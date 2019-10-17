package com.manager.parkinglot.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilder;

    @Autowired
    private StepBuilderFactory stepBuilder;

    @Bean
    public CopySeoulParkingLotInfoTasklet copySeoulParkingLotInfoTasklet() {
        return new CopySeoulParkingLotInfoTasklet();
    }

    @Bean
    public TaskletStep copySeoulParkingLotInfoStep(CopySeoulParkingLotInfoTasklet copySeoulParkingLotInfoTasklet) {
        return stepBuilder.get("copySeoulParkingLotInfoStep").tasklet(copySeoulParkingLotInfoTasklet).build();
    }

    @Bean
    public Job copySeoulParkingLotInfoJob(Step copySeoulParkingLotInfoStep) {
        return jobBuilder.get("copySeoulParkingLotInfoJob")
                .incrementer(new RunIdIncrementer())
                .start(copySeoulParkingLotInfoStep)
                .build();
    }
}

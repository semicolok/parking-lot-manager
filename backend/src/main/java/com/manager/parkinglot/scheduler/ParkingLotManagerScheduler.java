package com.manager.parkinglot.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ParkingLotManagerScheduler {

    private final JobLauncher jobLauncher;
    private final Job copySeoulParkingLotInfoJob;

    public ParkingLotManagerScheduler(JobLauncher jobLauncher, Job copySeoulParkingLotInfoJob) {
        this.jobLauncher = jobLauncher;
        this.copySeoulParkingLotInfoJob = copySeoulParkingLotInfoJob;
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void copySeoulParkingLotInfoJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        final JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters();

        jobLauncher.run(copySeoulParkingLotInfoJob, jobParameters);
    }
}

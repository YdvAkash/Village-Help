package Clarify.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronJobService {
    @Scheduled(cron = "* * * * * ?")
    public void runEveryMinute(){
//        System.out.println("Running cron job every minute");
    }
}

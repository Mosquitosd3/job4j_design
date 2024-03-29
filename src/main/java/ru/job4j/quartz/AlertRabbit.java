package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build();
            SimpleScheduleBuilder times = simpleSchedule().withIntervalInSeconds(seconds()).repeatForever();
            Trigger trigger = newTrigger().startNow().withSchedule(times).build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private static int seconds() {
        Properties properties = new Properties();
        ClassLoader loader = AlertRabbit.class.getClassLoader();
        try (InputStream io = loader.getResourceAsStream("rabbit.properties")) {
            properties.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(properties.getProperty("rabbit.interval"));
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }
}

package com.admin.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 * Created by cyc on 2016/11/18.
 */
@Service
public class QuartzTaskDemo {

    public void doSomething(){
        System.out.println("QuartzTaskDemo.doSomething.........");
    }
}

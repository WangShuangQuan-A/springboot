package com.bootdo.common.queue;

import com.bootdo.common.dao.LogDao;
import com.bootdo.common.domain.LogDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志消费线程
 *
 * @author wangshuangquan
 * @create 2017-11-28 14:07
 */
@Component
public class LogQueueConsumer implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(LogQueueConsumer.class);

    private static final int DEFAULT_BATCH_SIZE = 64;

    @Autowired
    private LogQueue logQueue;

    @Autowired
    private LogDao logDao;

    private boolean active = true;

    private Thread thread;

    @PostConstruct
    public void init(){
        thread = new Thread(this);
        thread.start();
    }

    @PreDestroy
    private void close() {
        this.active = false;
    }

    @Override
    public void run() {
        while(active) {
            execute();
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void execute() {
        List<LogDO> logDOList = new ArrayList<>();

        int size = 0;
        try {
            while(size < DEFAULT_BATCH_SIZE) {
                LogDO logDO = logQueue.poll();
                if (logDO == null) {
                    break;
                }
                logDOList.add(logDO);
                size ++;
            }
        } catch (InterruptedException ex) {
            logger.error(ex.getMessage());
        }

        if(! logDOList.isEmpty()){
            logDao.batchInsert(logDOList);
        }else {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

}

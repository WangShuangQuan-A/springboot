package com.bootdo.common.queue;

import com.bootdo.common.domain.LogDO;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 日志的存放队列
 *
 * @author wangshuangquan
 * @create 2017-11-28 11:16
 */
@Component
public class LogQueue {

    private BlockingQueue<LogDO> blockingQueue = new LinkedBlockingQueue<>();

    public void add(LogDO logDO) {
        blockingQueue.add(logDO);
    }

    public LogDO poll() throws InterruptedException {
        return blockingQueue.poll(1, TimeUnit.SECONDS);
    }
}

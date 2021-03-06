package com.frank.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author frank
 * @version 1.0
 * @date 2018/1/29 0029 下午 9:16
 */
@Slf4j
public class Producer implements Runnable {

    private BlockingQueue<String> queue;
    private volatile boolean runSwitch = true;
    private AtomicInteger count = new AtomicInteger();
    /**
     * 随机数默认上限
     */
    private static final int UP = 300;
    /**
     * 随机数默认下限
     */
    private static final int DOWN = 1;

    /**
     * 随机数默认下限
     */
    private static final String UNDERLINE = "_";

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log.info("[Producer] 启动生产者线程...");
        int sleepMillis;
        String data;
        while (runSwitch) {
            sleepMillis = RandomUtils.nextInt(DOWN, UP);
            data = new StringBuilder(String.valueOf(count.incrementAndGet())).append(UNDERLINE).append(UUID.randomUUID()).toString();
            try {
                log.info("[Producer]正在生产数据, data : {} 将放入队列,costTime={}", data, sleepMillis);
                Thread.sleep(sleepMillis);
                if (!queue.offer(data, 160, TimeUnit.MILLISECONDS)) {
                    log.info("[Producer]向队列放入数据失败!!!!!!!!!!!!!!!, data = {} ", data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.info("[Producer]InterruptedException, data = {} e={}", data, ExceptionUtils.getStackTrace(e));
            } finally {
                log.info("[Producer] 停止生产,退出生产者线程", data);
            }

        }

    }

    /**
     * 关闭循环开关
     */
    public void stop() {
        runSwitch = false;
    }
}

package org.swindle.shortlink.project.mq.consumer;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.swindle.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import org.swindle.shortlink.project.service.ShortLinkService;

import java.util.concurrent.Executors;

import static org.swindle.shortlink.project.common.constant.RedisKeyConstant.DELAY_QUEUE_STATS_KEY;

@Component
@RequiredArgsConstructor
public class DelayShortLinkStatsConsumer implements InitializingBean {

    private final StringRedisTemplate stringRedisTemplate;
    private final RedissonClient redissonClient;
    private ShortLinkService shortLinkService;

    public void onMessage(){
        Executors.newSingleThreadExecutor(
                runnable->{
                    Thread thread = new Thread(runnable);
                    thread.setName("delay_short-link_stats_consumer");
                    thread.setDaemon(Boolean.TRUE);
                    return thread;
                })
                .execute(()->{
                    RBlockingDeque<ShortLinkStatsRecordDTO> blockingDeque = redissonClient.getBlockingDeque(DELAY_QUEUE_STATS_KEY);
                    RDelayedQueue<ShortLinkStatsRecordDTO> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
                    for(;;){
                        try{
                            ShortLinkStatsRecordDTO statsRecord = delayedQueue.poll();
                            if (statsRecord != null) {
                                shortLinkService.shortLinkStats(null, null, statsRecord);
                                continue;}
                        } catch (Throwable ignored) {
                        }
                    }
                });
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}

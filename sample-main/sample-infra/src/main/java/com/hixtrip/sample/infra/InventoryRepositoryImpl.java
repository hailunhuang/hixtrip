package com.hixtrip.sample.infra;

import com.hixtrip.sample.domain.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * infra层是domain定义的接口具体的实现
 */
@Component
public class InventoryRepositoryImpl implements InventoryRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 创建一个锁对象
     */
    private final Lock lock = new ReentrantLock();

    @Override
    public boolean inventoryDeduction(String skuId, Long occupiedQuantity) {

        // 没有库存直接返回
        if (this.getInventory(skuId) <= 0L) {
            return false;
        }

        try {
            // 3秒获取不到锁默认库存扣减失败
            long lockTimeOutNum = 3L;
            if (lock.tryLock(lockTimeOutNum, TimeUnit.SECONDS)) {
                try {
                    // 拿到锁后获取最新的库存
                    Long stock = this.getInventory(skuId);
                    // 剩余库存足够本次扣减
                    if (stock >= occupiedQuantity) {
                        redisTemplate.opsForValue().set(skuId, stock - occupiedQuantity);
                        return true;
                    }
                } finally {
                    lock.unlock();
                }
            }
        } catch (Exception e) {
            // 日志记录...
        }
        return false;
    }

    @Override
    public Long getInventory(String skuId) {
        // 获取缓存里面的库存数量
        Object stock = redisTemplate.opsForValue().get(skuId);
        return null != stock ? Long.parseLong(String.valueOf(stock)) : 0L;
    }
}

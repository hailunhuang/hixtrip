package com.hixtrip.sample.domain.inventory.repository;

/**
 *
 */
public interface InventoryRepository {

    /**
     * 库存扣减
     *
     * @param skuId            skuId
     * @param occupiedQuantity 占用库存
     */
    boolean inventoryDeduction(String skuId, Long occupiedQuantity);

    /**
     * 获取指定商品的库存数量
     *
     * @param skuId skuId
     * @return {@link Long}
     */
    Long getInventory(String skuId);
}

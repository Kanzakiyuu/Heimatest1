package com.itheima.health.dao;

import com.itheima.health.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Authour: chenming
 * @Date： 2020/9/25 17:58
 */
public interface OrderSettingDao {
    List<OrderSetting> getOrderSettingByMonth(Map map);

    OrderSetting findByOrderDate(Date orderDate);

    void updateNumber(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);
}

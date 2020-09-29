package com.itheima.health.service;

import com.itheima.health.HealthException;
import com.itheima.health.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Authour: chenming
 * @Date： 2020/9/25 17:44
 */
public interface OrderSettingService {
    List<Map<String,Integer>> getOrderSettingByMonth(String month);

    void editNumberByDate(OrderSetting orderSetting) throws HealthException;
}

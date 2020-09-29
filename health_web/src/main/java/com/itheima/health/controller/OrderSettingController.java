package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.HealthException;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderSettingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Authour: chenming
 * @Date： 2020/9/25 17:41
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String month) {
        // 调用服务端查询
        List<Map<String,Integer>> data = orderSettingService.getOrderSettingByMonth(month);
        return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS,data);
    }

    //基于日历的预约设置
    @PostMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        // 调用服务更新
        try {
            orderSettingService.editNumberByDate(orderSetting);
        } catch (HealthException e) {
            e.printStackTrace();
        }
        return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
    }
}

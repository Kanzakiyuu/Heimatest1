package com.itheima.health.service;

import com.itheima.health.HealthException;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @Authour: chenming
 * @Date： 2020/9/18 20:24
 */
public interface CheckItemService {
    List<CheckItem> findAll();

    void add(CheckItem checkItem);

    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    CheckItem findById(int id);

    void update(CheckItem checkitem);

    void deleteById(int id) throws HealthException;
}

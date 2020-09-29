package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @Authour: chenming
 * @Date： 2020/9/18 20:37
 */
public interface CheckItemDao {
    List<CheckItem> findAll();

    void add(CheckItem checkItem);

    Page<CheckItem> findByCondition(String queryString);

    CheckItem findById(int id);

    void update(CheckItem checkitem);

    int findCountByCheckItemId(int id);

    void deleteById(int id);

}

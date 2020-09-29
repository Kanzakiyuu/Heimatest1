package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;

import java.util.List;

/**
 * @Authour: chenming
 * @Date： 2020/9/22 18:21
 */
public interface SetmealService {
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);

    Setmeal findById(int id);

    List<Integer> findCheckGroupIdsBySetmealId(int id);

    void update(Setmeal setmeal, Integer[] checkgroupIds);

    void deleteById(int id);

    List<String> findImgs();

    //手机端查询所有的套餐
    List<Setmeal> findAll();

    Setmeal findDetailById(Integer id);
}

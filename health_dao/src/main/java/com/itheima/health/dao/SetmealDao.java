package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Authour: chenming
 * @Date： 2020/9/22 18:26
 */
public interface SetmealDao {
    //添加套餐
    void add(Setmeal setmeal);

    //添加套餐与检查组的关系
    void addSetmealCheckgroup(@Param("setmealId") Integer setmealId, @Param("checkgroupId") Integer checkgroupId);

    //条件查询
    Page<Setmeal> findByCondition(String queryString);

    //通过id查询套餐信息
    Setmeal findById(int id);

    //查询选中的检查组id集合
    List<Integer> findCheckGroupIdsBySetmealId(int id);


    //先更新套餐
    void update(Setmeal setmeal);

    //删除套餐与检查组的关系
    void deleteSetmealCheckGroup(Integer id);

    //删除套餐
    void deleteById(int id);

    //通过套餐的id统计订单的个数
    int findCountBySetmealId(int id);

    //获取所有套餐的图片
    List<String> findImgs();

    List<Setmeal> findAll();

    Setmeal findDetailById(Integer id);
}

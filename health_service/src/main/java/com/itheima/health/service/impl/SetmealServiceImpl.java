package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.HealthException;
import com.itheima.health.dao.SetmealDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.CheckItemService;
import com.itheima.health.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Authour: chenming
 * @Date： 2020/9/22 18:22
 */
@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //先添加套餐
        setmealDao.add(setmeal);
        //获取新增的套餐的id
        Integer setmealId = setmeal.getId();
        //遍历选中的检查组id,添加套餐与检查组的关系
        if(null != checkgroupIds){
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealCheckgroup(setmealId,checkgroupId);
            }
        }
    }

    //分页查询
    @Override
    public PageResult<Setmeal> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 有查询条件，拼接% 模糊查询
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        Page<Setmeal> page = setmealDao.findByCondition(queryPageBean.getQueryString());

        return new PageResult<Setmeal>(page.getTotal(),page.getResult());
    }

    //通过id查询套餐信息
    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    //查询选中的检查组id集合
    @Override
    public List<Integer> findCheckGroupIdsBySetmealId(int id) {
        return setmealDao.findCheckGroupIdsBySetmealId(id);
    }

    //更新套餐
    @Override
    @Transactional
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        // 先更新套餐
        setmealDao.update(setmeal);
        // 删除旧关系
        setmealDao.deleteSetmealCheckGroup(setmeal.getId());
        // 添加新关系
        if(null != checkgroupIds){
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealCheckgroup(setmeal.getId(), checkgroupId);
            }
        }
    }

    //删除套餐
    @Override
    @Transactional
    public void deleteById(int id) throws HealthException {
        // 判断 是否被订单使用
        int count = setmealDao.findCountBySetmealId(id);
        // 使用了则报错
        if(count > 0) {
            throw new HealthException("该套餐已经被订单使用了，不能删除!");
        }
        // 没使用，先删除套餐与检查组的关系
        setmealDao.deleteSetmealCheckGroup(id);
        // 再删除套餐
        setmealDao.deleteById(id);
    }

    //查出数据库中的所有图片
    @Override
    public List<String> findImgs() {
        return setmealDao.findImgs();
    }

    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    @Override
    public Setmeal findDetailById(Integer id) {
        return setmealDao.findDetailById(id);
    }
}

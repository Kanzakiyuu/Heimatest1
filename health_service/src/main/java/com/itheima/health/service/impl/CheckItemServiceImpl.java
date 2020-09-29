package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.HealthException;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @Authour: chenming
 * @Date： 2020/9/18 20:23
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        //页码    页码大小
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        // 判断是否有查询条件，有则要拼接%  模糊查询
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            // 模糊查询
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        // 紧接着的查询语句会被分页, 从线程中获取threadlocal 页码与大小, total放入threadlocal Page
        Page<CheckItem> page = checkItemDao.findByCondition(queryPageBean.getQueryString());
        // 防止数据丢失Page属性用的是基础数据类型，没有实现序列化
        // web与service代码解耦
        PageResult<CheckItem> pageResult = new PageResult<>(page.getTotal(),page.getResult());
        return pageResult;
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost());
    }

    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }

    @Override
    public void update(CheckItem checkitem) {
        checkItemDao.update(checkitem);
    }

    @Override
    public void deleteById(int id) throws HealthException {
        // 判断是否被检查组使用了
        int count = checkItemDao.findCountByCheckItemId(id);
        // 被用了   就要报错
        if(count > 0){
            throw new HealthException("该检查项被检查组使用了，不能删除");
        }
        // 没被用   可以删除
        checkItemDao.deleteById(id);
    }
}

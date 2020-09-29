package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.HealthException;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Authour: chenming
 * @Date： 2020/9/19 19:39
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    @Transactional
    public boolean add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //新家检查组的影响行数
        int add = checkGroupDao.add(checkGroup);
        // 获取新增检查组id
        Integer checkGroupId = checkGroup.getId();
        //绑定检查项的总影响数
        int a = 0;
        if (checkGroupId != null) {
            for (Integer checkitemId : checkitemIds) {
                int b = checkGroupDao.addCheckGroupCheckItem(checkGroupId, checkitemId);
                a += b;
            }
        }
        if (a !=checkitemIds.length) {
            return false;
        }
        return add > 0;
    }

    //分页查询
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 有条件，则要模糊查询
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            // 拼接 %
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        // 条件查询
        Page<CheckGroup> page = checkGroupDao.findPage(queryPageBean.getQueryString());
        PageResult<CheckGroup> pageResult = new PageResult<CheckGroup>(page.getTotal(), page.getResult());
        return pageResult;
    }

    //ID查询
    @Override
    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }

    //通过检查组id查询选中的检查项id集合
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    //修改检查组
    @Override
    @Transactional
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 更新检查组信息
        checkGroupDao.update(checkGroup);
        // 删除旧关系
        checkGroupDao.deleteCheckGroupCheckItem(checkGroup.getId());
        // 添加新关系
        if(checkitemIds != null){
            for (Integer checkitemId : checkitemIds) {
                //添加检查组与检查项的关系
                checkGroupDao.addCheckGroupCheckItem(checkGroup.getId(),checkitemId);
            }
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        // 判断是被套餐使用了，返回影响的行数
        int count = checkGroupDao.findSetmealCountByCheckGroupId(id);
        // count>0使用了
        if(count > 0){
            throw new HealthException("访检查组被套餐使用了，无法删除！");
        }

        // 未被使用，先删除检查组与检查项关系
        checkGroupDao.deleteCheckGroupCheckItem(id);
        // 再删除检查组
        checkGroupDao.deleteById(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }
}

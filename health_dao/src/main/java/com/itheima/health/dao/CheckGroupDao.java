package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Authour: chenming
 * @Date： 2020/9/19 19:44
 */
public interface CheckGroupDao {
    int add(CheckGroup checkGroup);

    int addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);

    Page<CheckGroup> findPage(String queryString);

    CheckGroup findById(int id);

    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    void update(CheckGroup checkGroup);

    void deleteCheckGroupCheckItem(Integer id);

    int findSetmealCountByCheckGroupId(int id);

    void deleteById(int id);

    List<CheckGroup> findAll();
}

package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Authour: chenming
 * @Date： 2020/9/18 20:19
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;


    @GetMapping("/findAll")
    public Result findAll(){
        // 调用服务查询所有的检查项
        List<CheckItem> list = checkItemService.findAll();
        // 封装返回的结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        // 调用服务添加
        checkItemService.add(checkItem);
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }



    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        // 调用服务分页查询
        PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);
        // 返回结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
    }


    @GetMapping("/findById")
    public Result findById(int id){
        // 调用服务通过id查询检查项信息
        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }


    @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkitem){
        // 调用服务修改检查项，不报错就是成功
        checkItemService.update(checkitem);
        // 返回操作的结果
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }


    @PostMapping("/deleteById")
    public Result deleteById(int id){
        // 调用服务删除
        checkItemService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
}

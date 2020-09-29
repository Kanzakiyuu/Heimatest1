package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.CollectionUtils;
import com.itheima.health.utils.QiNiuUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Authour: chenming
 * @Date： 2020/9/28 17:49
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {

    @Reference
    private SetmealService setmealService;

    @GetMapping("/getSetmeal")
    public Result getSetmeal() {
        List<Setmeal> list = setmealService.findAll();
        if (CollectionUtils.isNotEmpty(list)) {
            //套餐有图片的话，拼接图片地址
            for (Setmeal setmeal : list) {
                setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
            }
            return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS, list);
        } else {
            return new Result(false, MessageConstant.GET_SETMEAL_LIST_FAIL);
        }

    }

    @GetMapping("/findDetailById")
    public Result findById(Integer id) {
        Setmeal setmeal = setmealService.findDetailById(id);
        if (setmeal != null) {
            setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
        } else {
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}

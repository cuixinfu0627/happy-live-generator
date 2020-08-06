package com.happy.live.passport.controller.wall;

import java.util.Arrays;
import java.util.Map;

import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.passport.entity.WpCircleEntity;
import com.happy.live.passport.service.WpCircleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.happy.live.common.mvc.view.R;



/**
 * 壁纸圈子
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-07-02 14:11:20
 */
@RestController
@RequestMapping("wall/circle")
public class WpCircleController {
    @Autowired
    private WpCircleService wpCircleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:circle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpCircleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:circle:info")
    public R info(@PathVariable("id") Integer id){
		WpCircleEntity wpCircle = wpCircleService.getById(id);

        return R.ok().put("wpCircle", wpCircle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:circle:save")
    public R save(@RequestBody WpCircleEntity wpCircle){
		wpCircleService.save(wpCircle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:circle:update")
    public R update(@RequestBody WpCircleEntity wpCircle){
		wpCircleService.updateById(wpCircle);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:circle:delete")
    public R delete(@RequestBody Integer[] ids){
		wpCircleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

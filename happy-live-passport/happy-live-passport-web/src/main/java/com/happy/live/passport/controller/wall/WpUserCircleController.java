package com.happy.live.passport.controller.wall;

import java.util.Arrays;
import java.util.Map;

import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.passport.entity.WpUserCircleEntity;
import com.happy.live.passport.service.WpUserCircleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.happy.live.common.mvc.view.R;



/**
 * 用户圈子
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-07-02 14:11:20
 */
@RestController
@RequestMapping("wall/usercircle")
public class WpUserCircleController {
    @Autowired
    private WpUserCircleService wpUserCircleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:usercircle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpUserCircleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:usercircle:info")
    public R info(@PathVariable("id") Integer id){
		WpUserCircleEntity wpUserCircle = wpUserCircleService.getById(id);

        return R.ok().put("wpUserCircle", wpUserCircle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:usercircle:save")
    public R save(@RequestBody WpUserCircleEntity wpUserCircle){
		wpUserCircleService.save(wpUserCircle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:usercircle:update")
    public R update(@RequestBody WpUserCircleEntity wpUserCircle){
		wpUserCircleService.updateById(wpUserCircle);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:usercircle:delete")
    public R delete(@RequestBody Integer[] ids){
		wpUserCircleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

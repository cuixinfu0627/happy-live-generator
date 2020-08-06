package com.happy.live.passport.controller.wall;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.WpThemeEntity;
import com.happy.live.passport.service.WpThemeService;
import com.happy.live.common.mvc.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 18:40:01
 */
@RestController
@RequestMapping("wall/theme")
public class WpThemeController {
    @Autowired
    private WpThemeService wpThemeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:theme:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpThemeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:theme:info")
    public R info(@PathVariable("id") Integer id){
		WpThemeEntity wpTheme = wpThemeService.getById(id);

        return R.ok().put("wpTheme", wpTheme);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:theme:save")
    public R save(@RequestBody WpThemeEntity wpTheme){
		wpThemeService.save(wpTheme);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:theme:update")
    public R update(@RequestBody WpThemeEntity wpTheme){
		wpThemeService.updateById(wpTheme);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:theme:delete")
    public R delete(@RequestBody Integer[] ids){
		wpThemeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

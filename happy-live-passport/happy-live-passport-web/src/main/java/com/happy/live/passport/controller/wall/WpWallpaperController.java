package com.happy.live.passport.controller.wall;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.WpWallpaperEntity;
import com.happy.live.passport.service.WpWallpaperService;
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
@RequestMapping("wall/wallpaper")
public class WpWallpaperController {
    @Autowired
    private WpWallpaperService wpWallpaperService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:wallpaper:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpWallpaperService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:wallpaper:info")
    public R info(@PathVariable("id") Integer id){
		WpWallpaperEntity wpWallpaper = wpWallpaperService.getById(id);

        return R.ok().put("wpWallpaper", wpWallpaper);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:wallpaper:save")
    public R save(@RequestBody WpWallpaperEntity wpWallpaper){
		wpWallpaperService.save(wpWallpaper);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:wallpaper:update")
    public R update(@RequestBody WpWallpaperEntity wpWallpaper){
		wpWallpaperService.updateById(wpWallpaper);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:wallpaper:delete")
    public R delete(@RequestBody Integer[] ids){
		wpWallpaperService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

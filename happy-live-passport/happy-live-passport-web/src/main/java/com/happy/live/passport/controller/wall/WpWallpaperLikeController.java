package com.happy.live.passport.controller.wall;

import java.util.Arrays;
import java.util.Map;

import com.happy.live.passport.entity.WpWallpaperLikeEntity;
import com.happy.live.passport.service.WpWallpaperLikeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.view.R;


/**
 * 壁纸点赞
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
@RestController
@RequestMapping("wall/like")
public class WpWallpaperLikeController {
    @Autowired
    private WpWallpaperLikeService wpWallpaperLikeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:like:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpWallpaperLikeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:like:info")
    public R info(@PathVariable("id") Integer id){
		WpWallpaperLikeEntity wpWallpaperLike = wpWallpaperLikeService.getById(id);

        return R.ok().put("wpWallpaperLike", wpWallpaperLike);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:like:save")
    public R save(@RequestBody WpWallpaperLikeEntity wpWallpaperLike){
		wpWallpaperLikeService.save(wpWallpaperLike);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:like:update")
    public R update(@RequestBody WpWallpaperLikeEntity wpWallpaperLike){
		wpWallpaperLikeService.updateById(wpWallpaperLike);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:like:delete")
    public R delete(@RequestBody Integer[] ids){
		wpWallpaperLikeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

package com.happy.live.passport.controller.wall;

import java.util.Arrays;
import java.util.Map;

import com.happy.live.passport.entity.WpWallpaperCommentEntity;
import com.happy.live.passport.service.WpWallpaperCommentService;
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
 * 壁纸评论
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
@RestController
@RequestMapping("wall/comment")
public class WpWallpaperCommentController {
    @Autowired
    private WpWallpaperCommentService wpWallpaperCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:comment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpWallpaperCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:comment:info")
    public R info(@PathVariable("id") Integer id){
		WpWallpaperCommentEntity wpWallpaperComment = wpWallpaperCommentService.getById(id);

        return R.ok().put("wpWallpaperComment", wpWallpaperComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:comment:save")
    public R save(@RequestBody WpWallpaperCommentEntity wpWallpaperComment){
		wpWallpaperCommentService.save(wpWallpaperComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:comment:update")
    public R update(@RequestBody WpWallpaperCommentEntity wpWallpaperComment){
		wpWallpaperCommentService.updateById(wpWallpaperComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:comment:delete")
    public R delete(@RequestBody Integer[] ids){
		wpWallpaperCommentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

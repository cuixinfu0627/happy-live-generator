package com.happy.live.passport.controller.wall;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.WpCarouselEntity;
import com.happy.live.passport.service.WpCarouselService;
import com.happy.live.common.mvc.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 轮播图
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 18:40:07
 */
@RestController
@RequestMapping("wall/carousel")
public class WpCarouselController {
    @Autowired
    private WpCarouselService wpCarouselService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:carousel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpCarouselService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:carousel:info")
    public R info(@PathVariable("id") Integer id){
		WpCarouselEntity wpCarousel = wpCarouselService.getById(id);

        return R.ok().put("wpCarousel", wpCarousel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:carousel:save")
    public R save(@RequestBody WpCarouselEntity wpCarousel){
		wpCarouselService.save(wpCarousel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:carousel:update")
    public R update(@RequestBody WpCarouselEntity wpCarousel){
		wpCarouselService.updateById(wpCarousel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:carousel:delete")
    public R delete(@RequestBody Integer[] ids){
		wpCarouselService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

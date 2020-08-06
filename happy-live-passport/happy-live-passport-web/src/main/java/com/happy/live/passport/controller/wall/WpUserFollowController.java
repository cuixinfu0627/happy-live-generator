package com.happy.live.passport.controller.wall;

import java.util.Arrays;
import java.util.Map;

import com.happy.live.passport.entity.WpUserFollowEntity;
import com.happy.live.passport.service.WpUserFollowService;
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
 * 用户关注
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
@RestController
@RequestMapping("wall/follow")
public class WpUserFollowController {
    @Autowired
    private WpUserFollowService wpUserFollowService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:follow:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpUserFollowService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:follow:info")
    public R info(@PathVariable("id") Integer id){
		WpUserFollowEntity wpUserFollow = wpUserFollowService.getById(id);

        return R.ok().put("wpUserFollow", wpUserFollow);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:follow:save")
    public R save(@RequestBody WpUserFollowEntity wpUserFollow){
		wpUserFollowService.save(wpUserFollow);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:follow:update")
    public R update(@RequestBody WpUserFollowEntity wpUserFollow){
		wpUserFollowService.updateById(wpUserFollow);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:follow:delete")
    public R delete(@RequestBody Integer[] ids){
		wpUserFollowService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

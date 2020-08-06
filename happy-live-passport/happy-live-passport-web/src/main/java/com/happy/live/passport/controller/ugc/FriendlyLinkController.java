package com.happy.live.passport.controller.ugc;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.FriendlyLinkEntity;
import com.happy.live.passport.service.FriendlyLinkService;
import com.happy.live.common.mvc.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 友情链接表
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 09:20:40
 */
@RestController
@RequestMapping("ugc/friendlylink")
public class FriendlyLinkController {
    @Autowired
    private FriendlyLinkService friendlyLinkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ugc:friendlylink:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = friendlyLinkService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ugc:friendlylink:info")
    public R info(@PathVariable("id") Integer id){
		FriendlyLinkEntity friendlyLink = friendlyLinkService.getById(id);

        return R.ok().put("friendlyLink", friendlyLink);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ugc:friendlylink:save")
    public R save(@RequestBody FriendlyLinkEntity friendlyLink){
		friendlyLinkService.save(friendlyLink);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ugc:friendlylink:update")
    public R update(@RequestBody FriendlyLinkEntity friendlyLink){
		friendlyLinkService.updateById(friendlyLink);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ugc:friendlylink:delete")
    public R delete(@RequestBody Integer[] ids){
		friendlyLinkService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

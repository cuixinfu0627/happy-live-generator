package com.happy.live.passport.controller.sys;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.SysMessageEntity;
import com.happy.live.passport.service.SysMessageService;
import com.happy.live.common.mvc.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 消息表
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 17:45:41
 */
@RestController
@RequestMapping("sys/message")
public class SysMessageController {
    @Autowired
    private SysMessageService sysMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:message:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysMessageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:message:info")
    public R info(@PathVariable("id") Long id){
		SysMessageEntity sysMessage = sysMessageService.getById(id);

        return R.ok().put("sysMessage", sysMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:message:save")
    public R save(@RequestBody SysMessageEntity sysMessage){
		sysMessageService.save(sysMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:message:update")
    public R update(@RequestBody SysMessageEntity sysMessage){
		sysMessageService.updateById(sysMessage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:message:delete")
    public R delete(@RequestBody Long[] ids){
		sysMessageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

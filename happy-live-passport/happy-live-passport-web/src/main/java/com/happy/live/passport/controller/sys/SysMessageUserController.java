package com.happy.live.passport.controller.sys;

import java.util.Arrays;
import java.util.Map;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.SysMessageUserEntity;
import com.happy.live.passport.service.SysMessageUserService;
import com.happy.live.common.mvc.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户消息表
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 17:45:41
 */
@RestController
@RequestMapping("sys/message-user")
public class SysMessageUserController {
    @Autowired
    private SysMessageUserService sysMessageUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:message-user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysMessageUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:message-user:info")
    public R info(@PathVariable("id") Long id){
		SysMessageUserEntity sysMessageUser = sysMessageUserService.getById(id);

        return R.ok().put("sysMessageUser", sysMessageUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:message-user:save")
    public R save(@RequestBody SysMessageUserEntity sysMessageUser){
		sysMessageUserService.save(sysMessageUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:message-user:update")
    public R update(@RequestBody SysMessageUserEntity sysMessageUser){
		sysMessageUserService.updateById(sysMessageUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:message-user:delete")
    public R delete(@RequestBody Long[] ids){
		sysMessageUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

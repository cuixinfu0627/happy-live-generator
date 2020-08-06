package com.happy.live.passport.controller.sys;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.SysLoginLogEntity;
import com.happy.live.passport.service.SysLoginLogService;
import com.happy.live.common.mvc.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 后台登录日志表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-31 14:14:15
 */
@RestController
@RequestMapping("sys/loginlog")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:loginlog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysLoginLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:loginlog:info")
    public R info(@PathVariable("id") Long id){
		SysLoginLogEntity sysLoginLog = sysLoginLogService.getById(id);

        return R.ok().put("sysLoginLog", sysLoginLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:loginlog:save")
    public R save(@RequestBody SysLoginLogEntity sysLoginLog){
		sysLoginLogService.save(sysLoginLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:loginlog:update")
    public R update(@RequestBody SysLoginLogEntity sysLoginLog){
		sysLoginLogService.updateById(sysLoginLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:loginlog:delete")
    public R delete(@RequestBody Long[] ids){
		sysLoginLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

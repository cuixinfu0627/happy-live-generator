package com.happy.live.passport.controller.sys;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.ThirdUserEntity;
import com.happy.live.passport.service.ThirdUserService;
import com.happy.live.common.mvc.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 第三方账号
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 10:18:34
 */
@RestController
@RequestMapping("sys/thirduser")
public class ThirdUserController {
    @Autowired
    private ThirdUserService thirdUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thirduser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thirdUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thirduser:info")
    public R info(@PathVariable("id") Long id){
		ThirdUserEntity thirdUser = thirdUserService.getById(id);

        return R.ok().put("thirdUser", thirdUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thirduser:save")
    public R save(@RequestBody ThirdUserEntity thirdUser){
		thirdUserService.save(thirdUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thirduser:update")
    public R update(@RequestBody ThirdUserEntity thirdUser){
		thirdUserService.updateById(thirdUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thirduser:delete")
    public R delete(@RequestBody Long[] ids){
		thirdUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

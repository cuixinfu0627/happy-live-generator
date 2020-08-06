package com.happy.live.passport.controller.ugc;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.AboutUsEntity;
import com.happy.live.passport.service.AboutUsService;
import com.happy.live.common.mvc.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 关于我们
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 09:20:40
 */
@RestController
@RequestMapping("ugc/aboutus")
public class AboutUsController {
    @Autowired
    private AboutUsService aboutUsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ugc:aboutus:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = aboutUsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ugc:aboutus:info")
    public R info(@PathVariable("id") Integer id){
		AboutUsEntity aboutUs = aboutUsService.getById(id);

        return R.ok().put("aboutUs", aboutUs);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ugc:aboutus:save")
    public R save(@RequestBody AboutUsEntity aboutUs){
		aboutUsService.save(aboutUs);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ugc:aboutus:update")
    public R update(@RequestBody AboutUsEntity aboutUs){
		aboutUsService.updateById(aboutUs);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ugc:aboutus:delete")
    public R delete(@RequestBody Integer[] ids){
		aboutUsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

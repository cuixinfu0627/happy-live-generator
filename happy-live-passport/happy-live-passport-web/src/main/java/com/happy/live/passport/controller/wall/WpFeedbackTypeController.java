package com.happy.live.passport.controller.wall;

import java.util.Arrays;
import java.util.Map;

import com.happy.live.passport.entity.WpFeedbackTypeEntity;
import com.happy.live.passport.service.WpFeedbackTypeService;
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
 * 反馈意见类型
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
@RestController
@RequestMapping("wall/feedback-type")
public class WpFeedbackTypeController {
    @Autowired
    private WpFeedbackTypeService wpFeedbackTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:feedback-type:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpFeedbackTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:feedback-type:info")
    public R info(@PathVariable("id") Long id){
		WpFeedbackTypeEntity wpFeedbackType = wpFeedbackTypeService.getById(id);

        return R.ok().put("wpFeedbackType", wpFeedbackType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:feedback-type:save")
    public R save(@RequestBody WpFeedbackTypeEntity wpFeedbackType){
		wpFeedbackTypeService.save(wpFeedbackType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:feedback-type:update")
    public R update(@RequestBody WpFeedbackTypeEntity wpFeedbackType){
		wpFeedbackTypeService.updateById(wpFeedbackType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:feedback-type:delete")
    public R delete(@RequestBody Long[] ids){
		wpFeedbackTypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

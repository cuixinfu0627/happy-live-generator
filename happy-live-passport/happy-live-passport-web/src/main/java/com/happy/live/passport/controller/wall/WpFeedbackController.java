package com.happy.live.passport.controller.wall;

import java.util.Arrays;
import java.util.Map;

import com.happy.live.passport.entity.WpFeedbackEntity;
import com.happy.live.passport.service.WpFeedbackService;
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
 * 反馈意见
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
@RestController
@RequestMapping("wall/feedback")
public class WpFeedbackController {
    @Autowired
    private WpFeedbackService wpFeedbackService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:feedback:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpFeedbackService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:feedback:info")
    public R info(@PathVariable("id") Integer id){
		WpFeedbackEntity wpFeedback = wpFeedbackService.getById(id);

        return R.ok().put("wpFeedback", wpFeedback);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:feedback:save")
    public R save(@RequestBody WpFeedbackEntity wpFeedback){
		wpFeedbackService.save(wpFeedback);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:feedback:update")
    public R update(@RequestBody WpFeedbackEntity wpFeedback){
		wpFeedbackService.updateById(wpFeedback);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:feedback:delete")
    public R delete(@RequestBody Integer[] ids){
		wpFeedbackService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

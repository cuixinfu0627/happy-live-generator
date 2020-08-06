package com.happy.live.passport.controller.wall;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.WpClassifyEntity;
import com.happy.live.passport.service.WpClassifyService;
import com.happy.live.common.mvc.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 18:40:01
 */
@RestController
@RequestMapping("wall/classify")
public class WpClassifyController {
    @Autowired
    private WpClassifyService wpClassifyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:classify:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpClassifyService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/list2")
    @RequiresPermissions("wall:classify:list")
    public List<WpClassifyEntity> list2(){
        List<WpClassifyEntity> classifyEntityList = wpClassifyService.list();
        for(WpClassifyEntity wpClassifyEntity : classifyEntityList){
            WpClassifyEntity parentClassifyEntity = wpClassifyService.getById(wpClassifyEntity.getParentId());
            if(parentClassifyEntity != null){
                wpClassifyEntity.setParentName(parentClassifyEntity.getClassifyName());
            }
        }
        return classifyEntityList;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:classify:info")
    public R info(@PathVariable("id") Integer id){
		WpClassifyEntity wpClassify = wpClassifyService.getById(id);

        return R.ok().put("wpClassify", wpClassify);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:classify:save")
    public R save(@RequestBody WpClassifyEntity wpClassify){
		wpClassifyService.save(wpClassify);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:classify:update")
    public R update(@RequestBody WpClassifyEntity wpClassify){
		wpClassifyService.updateById(wpClassify);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:classify:delete")
    public R delete(@RequestBody Integer[] ids){
		wpClassifyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

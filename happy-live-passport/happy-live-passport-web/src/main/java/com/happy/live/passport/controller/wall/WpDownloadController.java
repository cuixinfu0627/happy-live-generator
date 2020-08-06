package com.happy.live.passport.controller.wall;

import java.util.Arrays;
import java.util.Map;

import com.happy.live.passport.entity.WpDownloadEntity;
import com.happy.live.passport.service.WpDownloadService;
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
 * 用户下载壁纸
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
@RestController
@RequestMapping("wall/download")
public class WpDownloadController {
    @Autowired
    private WpDownloadService wpDownloadService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wall:download:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wpDownloadService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wall:download:info")
    public R info(@PathVariable("id") Integer id){
		WpDownloadEntity wpDownload = wpDownloadService.getById(id);

        return R.ok().put("wpDownload", wpDownload);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wall:download:save")
    public R save(@RequestBody WpDownloadEntity wpDownload){
		wpDownloadService.save(wpDownload);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wall:download:update")
    public R update(@RequestBody WpDownloadEntity wpDownload){
		wpDownloadService.updateById(wpDownload);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wall:download:delete")
    public R delete(@RequestBody Integer[] ids){
		wpDownloadService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

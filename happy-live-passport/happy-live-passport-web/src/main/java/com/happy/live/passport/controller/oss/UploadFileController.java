package com.happy.live.passport.controller.oss;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.UploadFileEntity;
import com.happy.live.passport.service.UploadFileService;
import com.happy.live.common.mvc.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 上传附件
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 11:22:53
 */
@RestController
@RequestMapping("oss/uploadfile")
public class UploadFileController {
    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("oss:uploadfile:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = uploadFileService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("oss:uploadfile:info")
    public R info(@PathVariable("id") Long id){
		UploadFileEntity uploadFile = uploadFileService.getById(id);

        return R.ok().put("uploadFile", uploadFile);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("oss:uploadfile:save")
    public R save(@RequestBody UploadFileEntity uploadFile){
		uploadFileService.save(uploadFile);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("oss:uploadfile:update")
    public R update(@RequestBody UploadFileEntity uploadFile){
		uploadFileService.updateById(uploadFile);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("oss:uploadfile:delete")
    public R delete(@RequestBody Long[] ids){
		uploadFileService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}

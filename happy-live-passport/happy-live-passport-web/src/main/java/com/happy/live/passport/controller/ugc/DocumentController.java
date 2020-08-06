package com.happy.live.passport.controller.ugc;

import com.happy.live.common.mvc.view.R;
import com.happy.live.passport.entity.DocumentEntity;
import com.happy.live.passport.service.DocumentService;
import com.happy.live.common.mvc.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 文章
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 09:20:40
 */
@RestController
@RequestMapping("ugc/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ugc:document:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = documentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ugc:document:info")
    public R info(@PathVariable("id") Integer id){
		DocumentEntity document = documentService.getById(id);

        return R.ok().put("document", document);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ugc:document:save")
    public R save(@RequestBody DocumentEntity document){
		documentService.save(document);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ugc:document:update")
    public R update(@RequestBody DocumentEntity document){
		documentService.updateById(document);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ugc:document:delete")
    public R delete(@RequestBody Integer[] ids){
		documentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.UploadFileEntity;
import com.happy.live.common.mvc.utils.PageUtils;

import java.util.Map;

/**
 * 上传附件
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 11:22:53
 */
public interface UploadFileService extends IService<UploadFileEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


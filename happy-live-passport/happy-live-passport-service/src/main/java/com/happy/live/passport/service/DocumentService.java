package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.DocumentEntity;
import com.happy.live.common.mvc.utils.PageUtils;

import java.util.Map;

/**
 * 文章
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 09:20:40
 */
public interface DocumentService extends IService<DocumentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


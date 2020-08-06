package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.AboutUsEntity;
import com.happy.live.common.mvc.utils.PageUtils;

import java.util.Map;

/**
 * 关于我们
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 09:20:40
 */
public interface AboutUsService extends IService<AboutUsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


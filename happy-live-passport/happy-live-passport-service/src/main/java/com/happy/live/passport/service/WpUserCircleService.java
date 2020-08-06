package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.passport.entity.WpUserCircleEntity;

import java.util.Map;

/**
 * 用户圈子
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-07-02 14:11:20
 */
public interface WpUserCircleService extends IService<WpUserCircleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


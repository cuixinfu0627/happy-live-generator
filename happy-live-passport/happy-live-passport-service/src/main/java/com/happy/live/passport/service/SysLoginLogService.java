package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.SysLoginLogEntity;
import com.happy.live.common.mvc.utils.PageUtils;

import java.util.Map;

/**
 * 后台登录日志表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-31 14:14:15
 */
public interface SysLoginLogService extends IService<SysLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


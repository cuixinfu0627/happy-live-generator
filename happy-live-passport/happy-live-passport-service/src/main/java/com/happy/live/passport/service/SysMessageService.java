package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.SysMessageEntity;
import com.happy.live.common.mvc.utils.PageUtils;

import java.util.Map;

/**
 * 消息表
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 17:45:41
 */
public interface SysMessageService extends IService<SysMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.FriendlyLinkEntity;
import com.happy.live.common.mvc.utils.PageUtils;

import java.util.Map;

/**
 * 友情链接表
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 09:20:40
 */
public interface FriendlyLinkService extends IService<FriendlyLinkEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


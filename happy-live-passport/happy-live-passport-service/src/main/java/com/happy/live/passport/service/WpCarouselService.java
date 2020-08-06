package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.WpCarouselEntity;
import com.happy.live.common.mvc.utils.PageUtils;
import java.util.Map;

/**
 * 轮播图
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 18:40:07
 */
public interface WpCarouselService extends IService<WpCarouselEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


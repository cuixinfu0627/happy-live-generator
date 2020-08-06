package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.WpWallpaperLikeEntity;
import com.happy.live.common.mvc.utils.PageUtils;
import java.util.Map;

/**
 * 壁纸点赞
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
public interface WpWallpaperLikeService extends IService<WpWallpaperLikeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


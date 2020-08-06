package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.WpDownloadEntity;
import com.happy.live.common.mvc.utils.PageUtils;
import java.util.Map;

/**
 * 用户下载壁纸
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
public interface WpDownloadService extends IService<WpDownloadEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


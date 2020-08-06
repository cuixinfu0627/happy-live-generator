package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.WpClassifyEntity;
import com.happy.live.common.mvc.utils.PageUtils;
import java.util.Map;

/**
 * 
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 18:40:01
 */
public interface WpClassifyService extends IService<WpClassifyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

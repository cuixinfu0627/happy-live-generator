package com.happy.live.passport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.live.passport.entity.WpFeedbackTypeEntity;
import com.happy.live.common.mvc.utils.PageUtils;
import java.util.Map;

/**
 * 反馈意见类型
 *
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
public interface WpFeedbackTypeService extends IService<WpFeedbackTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

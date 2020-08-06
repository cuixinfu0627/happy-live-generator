package com.happy.live.passport.service.impl;

import com.happy.live.passport.dao.WpFeedbackDao;
import com.happy.live.passport.entity.WpFeedbackEntity;
import com.happy.live.passport.service.WpFeedbackService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;



@Service("wpFeedbackService")
public class WpFeedbackServiceImpl extends ServiceImpl<WpFeedbackDao, WpFeedbackEntity> implements WpFeedbackService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpFeedbackEntity> page = this.page(
                new Query<WpFeedbackEntity>().getPage(params),
                new QueryWrapper<WpFeedbackEntity>()
        );

        return new PageUtils(page);
    }

}
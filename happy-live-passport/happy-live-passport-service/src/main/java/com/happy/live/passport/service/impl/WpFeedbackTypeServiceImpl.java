package com.happy.live.passport.service.impl;

import com.happy.live.passport.dao.WpFeedbackTypeDao;
import com.happy.live.passport.entity.WpFeedbackTypeEntity;
import com.happy.live.passport.service.WpFeedbackTypeService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;



@Service("wpFeedbackTypeService")
public class WpFeedbackTypeServiceImpl extends ServiceImpl<WpFeedbackTypeDao, WpFeedbackTypeEntity> implements WpFeedbackTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpFeedbackTypeEntity> page = this.page(
                new Query<WpFeedbackTypeEntity>().getPage(params),
                new QueryWrapper<WpFeedbackTypeEntity>()
        );

        return new PageUtils(page);
    }

}
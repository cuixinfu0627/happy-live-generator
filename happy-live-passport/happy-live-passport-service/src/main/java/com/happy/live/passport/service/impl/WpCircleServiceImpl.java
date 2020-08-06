package com.happy.live.passport.service.impl;

import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import com.happy.live.passport.dao.WpCircleDao;
import com.happy.live.passport.entity.WpCircleEntity;
import com.happy.live.passport.service.WpCircleService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("wpCircleService")
public class WpCircleServiceImpl extends ServiceImpl<WpCircleDao, WpCircleEntity> implements WpCircleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpCircleEntity> page = this.page(
                new Query<WpCircleEntity>().getPage(params),
                new QueryWrapper<WpCircleEntity>()
        );

        return new PageUtils(page);
    }

}
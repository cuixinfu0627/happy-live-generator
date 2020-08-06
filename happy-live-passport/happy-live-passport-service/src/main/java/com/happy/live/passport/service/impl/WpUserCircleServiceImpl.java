package com.happy.live.passport.service.impl;

import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import com.happy.live.passport.dao.WpUserCircleDao;
import com.happy.live.passport.entity.WpUserCircleEntity;
import com.happy.live.passport.service.WpUserCircleService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("wpUserCircleService")
public class WpUserCircleServiceImpl extends ServiceImpl<WpUserCircleDao, WpUserCircleEntity> implements WpUserCircleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpUserCircleEntity> page = this.page(
                new Query<WpUserCircleEntity>().getPage(params),
                new QueryWrapper<WpUserCircleEntity>()
        );

        return new PageUtils(page);
    }

}
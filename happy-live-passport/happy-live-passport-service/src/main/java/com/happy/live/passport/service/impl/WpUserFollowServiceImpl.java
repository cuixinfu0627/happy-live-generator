package com.happy.live.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.passport.dao.WpUserFollowDao;
import com.happy.live.passport.entity.WpUserFollowEntity;
import com.happy.live.passport.service.WpUserFollowService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("wpUserFollowService")
public class WpUserFollowServiceImpl extends ServiceImpl<WpUserFollowDao, WpUserFollowEntity> implements WpUserFollowService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpUserFollowEntity> page = this.page(
                new Query<WpUserFollowEntity>().getPage(params),
                new QueryWrapper<WpUserFollowEntity>()
        );

        return new PageUtils(page);
    }

}
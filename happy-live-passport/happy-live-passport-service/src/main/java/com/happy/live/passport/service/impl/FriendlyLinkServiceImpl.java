package com.happy.live.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.passport.dao.FriendlyLinkDao;
import com.happy.live.passport.entity.FriendlyLinkEntity;
import com.happy.live.passport.service.FriendlyLinkService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("friendlyLinkService")
public class FriendlyLinkServiceImpl extends ServiceImpl<FriendlyLinkDao, FriendlyLinkEntity> implements FriendlyLinkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FriendlyLinkEntity> page = this.page(
                new Query<FriendlyLinkEntity>().getPage(params),
                new QueryWrapper<FriendlyLinkEntity>()
        );

        return new PageUtils(page);
    }

}
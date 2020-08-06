package com.happy.live.passport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.passport.dao.AboutUsDao;
import com.happy.live.passport.entity.AboutUsEntity;
import com.happy.live.passport.service.AboutUsService;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("aboutUsService")
public class AboutUsServiceImpl extends ServiceImpl<AboutUsDao, AboutUsEntity> implements AboutUsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AboutUsEntity> page = this.page(
                new Query<AboutUsEntity>().getPage(params),
                new QueryWrapper<AboutUsEntity>()
        );

        return new PageUtils(page);
    }

}
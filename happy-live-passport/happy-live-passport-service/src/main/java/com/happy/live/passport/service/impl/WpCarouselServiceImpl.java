package com.happy.live.passport.service.impl;

import com.happy.live.passport.dao.WpCarouselDao;
import com.happy.live.passport.entity.WpCarouselEntity;
import com.happy.live.passport.service.WpCarouselService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;



@Service("wpCarouselService")
public class WpCarouselServiceImpl extends ServiceImpl<WpCarouselDao, WpCarouselEntity> implements WpCarouselService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpCarouselEntity> page = this.page(
                new Query<WpCarouselEntity>().getPage(params),
                new QueryWrapper<WpCarouselEntity>()
        );

        return new PageUtils(page);
    }

}
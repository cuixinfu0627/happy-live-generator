package com.happy.live.passport.service.impl;

import com.happy.live.passport.dao.WpThemeDao;
import com.happy.live.passport.entity.WpThemeEntity;
import com.happy.live.passport.service.WpThemeService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;


@Service("wpThemeService")
public class WpThemeServiceImpl extends ServiceImpl<WpThemeDao, WpThemeEntity> implements WpThemeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpThemeEntity> page = this.page(
                new Query<WpThemeEntity>().getPage(params),
                new QueryWrapper<WpThemeEntity>()
        );

        return new PageUtils(page);
    }

}
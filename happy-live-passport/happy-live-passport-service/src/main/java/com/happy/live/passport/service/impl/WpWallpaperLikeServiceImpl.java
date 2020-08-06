package com.happy.live.passport.service.impl;

import com.happy.live.passport.dao.WpWallpaperLikeDao;
import com.happy.live.passport.entity.WpWallpaperLikeEntity;
import com.happy.live.passport.service.WpWallpaperLikeService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;



@Service("wpWallpaperLikeService")
public class WpWallpaperLikeServiceImpl extends ServiceImpl<WpWallpaperLikeDao, WpWallpaperLikeEntity> implements WpWallpaperLikeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpWallpaperLikeEntity> page = this.page(
                new Query<WpWallpaperLikeEntity>().getPage(params),
                new QueryWrapper<WpWallpaperLikeEntity>()
        );

        return new PageUtils(page);
    }

}
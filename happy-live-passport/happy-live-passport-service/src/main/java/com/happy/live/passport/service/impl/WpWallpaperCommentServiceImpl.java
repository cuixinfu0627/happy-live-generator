package com.happy.live.passport.service.impl;

import com.happy.live.passport.dao.WpWallpaperCommentDao;
import com.happy.live.passport.entity.WpWallpaperCommentEntity;
import com.happy.live.passport.service.WpWallpaperCommentService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.live.common.mvc.utils.PageUtils;
import com.happy.live.common.mvc.utils.Query;


@Service("wpWallpaperCommentService")
public class WpWallpaperCommentServiceImpl extends ServiceImpl<WpWallpaperCommentDao, WpWallpaperCommentEntity> implements WpWallpaperCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WpWallpaperCommentEntity> page = this.page(
                new Query<WpWallpaperCommentEntity>().getPage(params),
                new QueryWrapper<WpWallpaperCommentEntity>()
        );

        return new PageUtils(page);
    }

}